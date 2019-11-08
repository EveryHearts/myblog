package cn.welfareZhu.item.myblog.controller;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.dto.FileDTO;
import cn.welfareZhu.item.myblog.dto.UserSearchDTO;
import cn.welfareZhu.item.myblog.model.FileByte;
import cn.welfareZhu.item.myblog.model.FileInfo;
import cn.welfareZhu.item.myblog.mq.FileByteProducer;
import cn.welfareZhu.item.myblog.service.FileService;
import cn.welfareZhu.item.myblog.service.UserService;
import cn.welfareZhu.item.myblog.util.QueryPageMap;
import com.sun.deploy.net.HttpResponse;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 朱剑飞
 * @name 文件上传Controller
 * @date 2019-10-14
 * **/
@Controller
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private FileByteProducer fileByteProducer;

    private static Destination destination=new ActiveMQQueue("cn.welfareZhu.item.myblog.fileByte");

    //跳转文件上传页面
    @GetMapping("/uploadPage")
    public String gotoFileUploadPage(Model model,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            model.addAttribute(MODEL_MESSAGE,"你还未登录，请登录后再尝试此操作");
            return "home";
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        if (user==null||user.getUserStatus()!=NORMAL){
            model.addAttribute(MODEL_MESSAGE,"你的账号状态异常，请联系管理员");
            return "home";
        }
        return "file/fileUpload";
    }

    //文件上传请求处理
    @PostMapping("/fileUpload")
    @ResponseBody
    public int uploadFile(@RequestParam("files") MultipartFile[] files, HttpSession session) throws IOException {
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return 1;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        if (user==null||user.getUserStatus()!=NORMAL){
            return -1;
        }
        if (files.length<=0){
            return 0;
        }
        for (MultipartFile file:files){
            FileInfo fileInfo=new FileInfo();
            fileInfo.setFileName(file.getOriginalFilename());
            fileInfo.setFileAuth(IS_PRIVATE);
            fileInfo.setUserId(user.getUserId());
            fileInfo.setFileStatus(TO_AUDIT);
            fileInfo.setFileType(converseExtends(FilenameUtils.getExtension(file.getOriginalFilename())));
            fileInfo.setCreateDate(new Date());
            fileInfo.setLikeNum(0);
            fileInfo.setFilePath("暂无描述");
            fileService.addNewFile(fileInfo);
            InputStream inputStream=file.getInputStream();
            byte[] fileByte=new byte[(int)file.getSize()];
            inputStream.read(fileByte);
            FileByte fileBytes=new FileByte();
            fileBytes.setFileId(fileInfo.getFileId());
            fileBytes.setFileByte(fileByte);
            fileByteProducer.sendMessage(destination,fileBytes);
        }
        return 0;
    }

    //用户文件列表
    @GetMapping("/browseUserFileList")
    public String userFileList(@RequestParam("userId") int id,@RequestParam("pageNo") int pageNo,Model model,HttpSession session){
        int userId;
        UserSearchDTO userSearch;
        if (id==0){
            if ((int)session.getAttribute(LOGIN_STATUS)==USER){
                UserSearchDTO userSearchDTO=(UserSearchDTO)session.getAttribute(LOGIN_USER);
                userId=userSearchDTO.getUserId();
                userSearch=userSearchDTO;
            }else {
                model.addAttribute(MODEL_MESSAGE,"请先选择你要查看的用户");
                return "home";
            }
        }else {
            userId=id;
            userSearch=userService.queryUserByUserId(userId);
        }
        boolean isHost=false;
        if ((int)session.getAttribute(LOGIN_STATUS)==USER){
            UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
            if (userId==user.getUserId()){
                isHost=true;
            }
        }
        if (isHost){
            QueryPageMap<FileDTO> normalFileMap=fileService.queryFileByUserIdAndStatus(userId,NORMAL,pageNo,PAGE_SIZE);
            QueryPageMap<FileDTO> toAuditFileMap=fileService.queryFileByUserIdAndStatus(userId,TO_AUDIT,pageNo,PAGE_SIZE);
            model.addAttribute(NORMAL_PART,normalFileMap);
            model.addAttribute(TO_AUDIT_PART,toAuditFileMap);
        }else {
            QueryPageMap<FileDTO> normalPublicFileMap=fileService.queryFileByUserIdAndAuthAndStatus(userId,IS_PUBLIC,NORMAL,pageNo,PAGE_SIZE);
            model.addAttribute(NORMAL_PART,normalPublicFileMap);
        }
        model.addAttribute(ACCESS_USER,userSearch);
        return "file/userFileList";
    }

    //公共文章列表
    @GetMapping("/browsePublicFileList")
    public String publicFileList(@RequestParam("pageNo") int pageNo,Model model){
        QueryPageMap<FileDTO> normalPartMap=fileService.queryFileByAuthAndStatus(IS_PUBLIC,NORMAL,pageNo,PAGE_SIZE);
        model.addAttribute(NORMAL_PART,normalPartMap);
        return "file/publicFileList";
    }

    //修改文件描述（Path）
    @GetMapping("/modifyFileDescription")
    @ResponseBody
    public int modifyFileDescription(@RequestParam("description") String description,@RequestParam("fileId") int fileId,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return 1;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        FileDTO fileDTO=fileService.queryFileByFileId(fileId);
        if (fileDTO==null||user.getUserId()!=fileDTO.getUserId()){
            return -1;
        }
        fileService.modifyPathByFileId(fileId,description);
        return 0;
    }

    //修改文件权限
    @GetMapping("/modifyFileAuth")
    @ResponseBody
    public int modifyFileAuth(@RequestParam("fileId") int fileId,@RequestParam("auth") int auth,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return 1;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        FileDTO fileDTO=fileService.queryFileByFileId(fileId);
        if (fileDTO==null||user.getUserId()!=fileDTO.getUserId()){
            return -1;
        }
        fileService.modifyAuthByFileId(fileId,auth);
        return 0;
    }

    //删除文件
    @GetMapping("/deleteFile")
    @ResponseBody
    public int deleteFile(@RequestParam("fileId") int fileId,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return 1;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        FileDTO fileDTO=fileService.queryFileByFileId(fileId);
        if (fileDTO==null||user.getUserId()!=fileDTO.getUserId()){
            return -1;
        }
        fileService.modifyStatusByFileId(fileId,DELETED);
        return 0;
    }

    //下载文件
    @GetMapping("/downloadFile")
    public void downloadFile(@RequestParam("fileId") int fileId, HttpServletResponse response, HttpSession session) throws IOException{
        FileDTO file=fileService.queryFileByFileId(fileId);
        if (file==null){
            return;
        }
        if((int)session.getAttribute(LOGIN_STATUS)!=USER&&file.getFileAuth()==IS_PRIVATE){
            return;
        }
        if ((int)session.getAttribute(LOGIN_STATUS)==USER){
            UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
            if (file.getFileAuth()==IS_PRIVATE&&file.getUserId()!=user.getUserId()){
                return;
            }
        }
        FileByte fileByte=fileService.queryFileByteByFileId(fileId);
        byte[] bytes=fileByte.getFileByte();
        InputStream inputStream=new BufferedInputStream(new ByteArrayInputStream(bytes));
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + file.getFileName());
        response.addHeader("Content-Length",String.valueOf(bytes.length));
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = inputStream.read()) != -1){
            outputStream.write(len);
            outputStream.flush();
        }
        outputStream.close();
    }


    private int converseExtends(String ext){
        if (ext.equalsIgnoreCase("jpg")){
            return JPG_FILE;
        }
        if (ext.equalsIgnoreCase("png")){
            return PNG_FILE;
        }
        if (ext.equalsIgnoreCase("gif")){
            return GIF_FILE;
        }
        if (ext.equalsIgnoreCase("zip")){
            return ZIP_FILE;
        }
        if (ext.equalsIgnoreCase("rar")){
            return RAR_FILE;
        }
        if (ext.equalsIgnoreCase("txt")){
            return TXT_FILE;
        }
        if (ext.equalsIgnoreCase("docx")){
            return DOCX_FILE;
        }
        if (ext.equalsIgnoreCase("xlsx")){
            return XLS_FILE;
        }
        if (ext.equalsIgnoreCase("pdf")){
            return PDF_FILE;
        }
        if (ext.equalsIgnoreCase("mp3")){
            return MP3_FILE;
        }
        if (ext.equalsIgnoreCase("ogg")){
            return OGG_FILE;
        }
        return 404;
    }
}
