package cn.welfareZhu.item.myblog.controller;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.dto.UserSearchDTO;
import cn.welfareZhu.item.myblog.model.UserInfo;
import cn.welfareZhu.item.myblog.model.UserSearch;
import cn.welfareZhu.item.myblog.service.UserService;
import cn.welfareZhu.item.myblog.vo.UserRegister;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 朱剑飞
 * @name UserController
 * @date 2019-09-14
 * **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    //跳转到注册页面
    @RequestMapping("/registerPage")
    public String gotoRegisterPage(HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)==USER){
            return "home";
        }
        return "user/register";
    }

    //检查注册账号是否存在
    @PostMapping("/checkAccount")
    @ResponseBody
    public int checkAccountIsExist(@RequestParam String account){
        boolean isExist=userService.queryAccountIsExist(account);
        if (isExist){
            return IS_NOT_OK;
        }
        return IS_OK;
    }

    //跳转到登陆页面
    @RequestMapping("/loginPage")
    public String gotoLoginPage(HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)==USER){
            return "home";
        }
        return "user/login";
    }
    //登录账号
    @PostMapping("/loginAccount")
    @ResponseBody
    public int loginAccount(@RequestParam String account, @RequestParam String password, HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)==USER||account==null){
            return LOGIN_FAIL;
        }
        UserSearchDTO user=userService.queryUserByAccountAndPassword(account,password);
        if (user==null){
            return LOGIN_FAIL;
        }
        session.setAttribute(LOGIN_USER,user);
        session.setAttribute(LOGIN_STATUS,USER);
        return LOGIN_SUCC;
    }

    //注册账号
    @PostMapping("/registerAccount")
    public String registerAccount(UserRegister user, Model model, HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)==USER){
            return "home";
        }
        if (user==null){
            model.addAttribute(MODEL_MESSAGE,"注册信息为空，请重新尝试");
            return "user/register";
        }
        int userId=userService.registerUserAccount(user);
        if (userId<=0){
            model.addAttribute(MODEL_MESSAGE,"对不起，由于某种原因注册失败");
            return "user/register";
        }
        UserSearchDTO loginUser=userService.queryUserByUserId(userId);
        if (loginUser==null){
            model.addAttribute(MODEL_MESSAGE,"服务器访问失败，请尝试用你注册的账号进行登陆操作");
            return "user/register";
        }
        session.setAttribute(LOGIN_USER,loginUser);
        session.setAttribute(LOGIN_STATUS,USER);
        return "home";
    }

    //浏览登陆用户个人信息
    @GetMapping("/browseLoginUserInfo")
    public String browseLoginUserInfo(Model model,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            model.addAttribute(MODEL_MESSAGE,"你还未登陆，请登陆后查看");
            return "home";
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        int userId=user.getUserId();
        UserInfo userInfo=userService.queryUserInfoByUserId(userId);
        if (userInfo==null){
            model.addAttribute(MODEL_MESSAGE,"查询用户信息失败");
            return "home";
        }
        model.addAttribute(USER_SEARCH,user);;
        model.addAttribute(USER_INFO,userInfo);
        return "user/userInfo";
    }

    //浏览其他用户个人信息
    @GetMapping("/browseUserInfo")
    public String browseUserInfo(@RequestParam int userId, Model model){
        UserInfo userInfo=userService.queryUserInfoByUserId(userId);
        if (userInfo==null){
            model.addAttribute(MODEL_MESSAGE,"查询用户信息失败");
            return "home";
        }
        UserSearchDTO user=userService.queryUserByUserId(userId);
        if (user==null){
            model.addAttribute(MODEL_MESSAGE,"查询用户信息出现错误");
            return "home";
        }
        model.addAttribute(USER_SEARCH,user);;
        model.addAttribute(USER_INFO,userInfo);
        return "user/userInfo";
    }

    //跳转用户信息修改页面
    @GetMapping("/modifyUserInfo")
    public String gotoUserInfoModify(Model model,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            model.addAttribute(MODEL_MESSAGE,"你还未登陆无法进行信息修改");
            return "home";
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        int userId=user.getUserId();
        UserInfo userInfo=userService.queryUserInfoByUserId(userId);
        if (userInfo==null){
            model.addAttribute(MODEL_MESSAGE,"获取用户信息失败");
            return "home";
        }
        model.addAttribute(USER_SEARCH,user);
        model.addAttribute(USER_INFO,userInfo);
        return "user/userInfoModify";
    }

    //提交信息修改
    @PostMapping("/submitModifyUserInfo")
    public String submitModifyUserInfo(UserInfo userInfo,Model model,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            model.addAttribute(MODEL_MESSAGE,"你还未登陆无法进行信息修改");
            return "home";
        }
        if (userInfo==null){
            model.addAttribute(MODEL_MESSAGE,"提交信息修改失败");
            return "home";
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        userInfo.setUserId(user.getUserId());
        System.out.println(userInfo.getGender());
        boolean isOK=userService.modifyUserInfo(userInfo);
        if (!isOK){
            model.addAttribute(MODEL_MESSAGE,"信息更新提交失败");
            return "error";
        }
        return "redirect:../user/browseLoginUserInfo";
    }

    //上传用户头像
    @PostMapping("/uploadPortrait")
    @ResponseBody
    public int modifyUserPortrait(@RequestParam MultipartFile portrait, HttpServletRequest request, HttpSession session)throws IOException {
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return IS_NOT_OK;
        }
        if (portrait==null||portrait.getSize()<=0){
            return IS_NOT_OK;
        }
        UserSearchDTO user=(UserSearchDTO) session.getAttribute(LOGIN_USER);
        String portraitFolder=session.getServletContext().getRealPath("/")
                +SYS_FOLDER+File.separator
                +USER_FOLDER_PREFFIX+user.getUserId() +File.separator
                +PORTRAIT_FOLDER;
        File baseDir=new File(portraitFolder);
        if (!baseDir.exists()&&!baseDir.isDirectory()){
            if (!baseDir.mkdirs()){
                return IS_NOT_OK;
            }
        }
        String webRootUrl=(String)session.getAttribute(WEB_ROOT_URL);  //项目根URL
        String basePath=baseDir+File.separator;  //用户头像资源文件夹绝对路径
        //重命名文件
        String newFileName= FilenameUtils.getBaseName(portrait.getOriginalFilename())+
                System.currentTimeMillis()+"."+
                FilenameUtils.getExtension(portrait.getOriginalFilename());
        System.out.println(newFileName);
        String newFilePath=basePath+newFileName;
        String newFileURL=webRootUrl+
                "/"+SYS_FOLDER+
                "/"+USER_FOLDER_PREFFIX+user.getUserId()+
                "/"+PORTRAIT_FOLDER+
                "/"+newFileName;
        //读取文件数据流并写入字节数组
        byte[] fileByte=new byte[(int)portrait.getSize()];
        InputStream inputStream=portrait.getInputStream();
        inputStream.read(fileByte);
        //写入项目路径下
        FileUtils.writeByteArrayToFile(new File(newFilePath),fileByte,false);
        //存入数据库中
        UserSearch userSearch=new UserSearch();
        userSearch.setUserId(user.getUserId());
        userSearch.setUserViaSrc(newFileURL);
        userSearch.setUserViaByte(fileByte);
        boolean res=userService.modifyUserSearch(userSearch);
        user.setViaSrc(newFileURL);
        if (!res){
            return IS_NOT_OK;
        }
        return IS_OK;
    }

    //查询用户状态
    @GetMapping("/queryUserStatus")
    @ResponseBody
    public int checkUserStatus(@RequestParam int userId){
        return userService.queryUserStatusByUserId(userId);
    }

    //注销登陆
    @GetMapping("/logoutAccount")
    public String logout(HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return "home";
        }
        session.removeAttribute(LOGIN_USER);
        session.setAttribute(LOGIN_STATUS,TOURIST);
        return "home";
    }
}
