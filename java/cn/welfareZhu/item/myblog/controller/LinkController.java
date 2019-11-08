package cn.welfareZhu.item.myblog.controller;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.dto.LinkDTO;
import cn.welfareZhu.item.myblog.dto.UserSearchDTO;
import cn.welfareZhu.item.myblog.model.LinkInfo;
import cn.welfareZhu.item.myblog.mq.LinkLikeKeyProducer;
import cn.welfareZhu.item.myblog.service.LinkCommentService;
import cn.welfareZhu.item.myblog.service.LinkService;
import cn.welfareZhu.item.myblog.service.UserService;
import cn.welfareZhu.item.myblog.util.QueryPageMap;
import cn.welfareZhu.item.myblog.vo.LinkLikeMoodVO;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author 朱剑飞
 * @name 收藏链接Controller
 * @date 2019-10-08
 * **/
@Controller
@RequestMapping("/link")
public class LinkController {
    @Resource
    private LinkService linkService;
    @Resource
    private LinkCommentService linkCommentService;
    @Resource
    private UserService userService;
    @Resource
    private LinkLikeKeyProducer linkLikeKeyProducer;
    @Resource
    private RedisTemplate redisTemplate;

    private static Destination destination=new ActiveMQQueue("cn.welfareZhu.item.myblog.linkLike");

    //跳转到编辑上传页面
    @GetMapping("/linkEditorPage")
    public String gotoLinkEditorPage(Model model,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            model.addAttribute(MODEL_MESSAGE,"请登录之后再次尝试此操作");
            return "home";
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        if (user.getUserStatus()!=NORMAL){
            model.addAttribute(MODEL_MESSAGE,"你的账号尚未经过审核，请联系管理员");
            return "home";
        }
        return "link/editorUpload";
    }

    //添加收藏链接
    @PostMapping("/uploadLink")
    @ResponseBody
    public int addLink(LinkInfo linkInfo, HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return 1;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        if (user.getUserStatus()!=NORMAL){
            return -1;
        }
        linkInfo.setUserId(user.getUserId());
        linkInfo.setLikeNum(0);
        linkInfo.setLinkStatus(TO_AUDIT);
        linkInfo.setCreateDate(new Date());
        linkService.addNewLink(linkInfo);
        return 0;
    }

    //浏览用户收藏链接
    @GetMapping("/browseUserLinkList")
    public String browserUserLink(@RequestParam("userId") int userId,@RequestParam("pageNo") int pageNo,Model model,HttpSession session){
        int uid;
        boolean isHost=false;
        UserSearchDTO user;
        if (userId!=0){
            uid=userId;
            user=userService.queryUserByUserId(uid);
        }else {
            if ((int)session.getAttribute(LOGIN_STATUS)==USER){
                user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
                uid=user.getUserId();
            }else{
                model.addAttribute(MODEL_MESSAGE,"请先制定用户ID再进行此请求");
                return "home";
            }
        }
        if((int)session.getAttribute(LOGIN_STATUS)==USER){
            UserSearchDTO userSearchDTO=(UserSearchDTO)session.getAttribute(LOGIN_USER);
            if (userSearchDTO.getUserId()==uid){
                isHost=true;
            }
        }
        if (isHost){
            QueryPageMap<LinkDTO> normalPart=linkService.queryLinkByUserIdAndStatus(uid,NORMAL,pageNo,PAGE_SIZE);
            QueryPageMap<LinkDTO> toAuditPart=linkService.queryLinkByUserIdAndStatus(uid,TO_AUDIT,pageNo,PAGE_SIZE);
            model.addAttribute(NORMAL_PART,normalPart);
            model.addAttribute(TO_AUDIT_PART,toAuditPart);
        }else {
            QueryPageMap<LinkDTO> normalPart=linkService.queryLinkByUserIdAndStatus(uid,NORMAL,pageNo,PAGE_SIZE);
            model.addAttribute(NORMAL_PART,normalPart);
        }
        model.addAttribute(ACCESS_USER,user);
        return "link/userLinkList";
    }

    //浏览收藏链接
    @GetMapping("/browsePublicLinkList")
    public String browserPublicLink(@RequestParam("pageNo") int pageNo,Model model,HttpSession session){
        QueryPageMap<LinkDTO> normelPart=linkService.queryLinkByStatus(NORMAL,pageNo,PAGE_SIZE);
        model.addAttribute(NORMAL_PART,normelPart);
        return "link/publicLinkList";
    }

    //删除收藏链接
    @GetMapping("/deleteLink")
    @ResponseBody
    public int deleteLink(@RequestParam("linkId") int linkId,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return IS_NOT_OK;
        }
        LinkDTO link=linkService.queryLinkByLinkId(linkId);
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        if (link==null||link.getUserId()!=user.getUserId()){
            return IS_NOT_OK;
        }
        linkService.modifyLinkStatus(linkId,DELETED);
        return IS_OK;
    }

    //链接点赞
    @GetMapping("/praiseLink")
    @ResponseBody
    public int praiseLink(@RequestParam("linkId") int linkId,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return LOGIN_STATUS_ERROR;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        LinkLikeMoodVO linkLikeMoodVO=new LinkLikeMoodVO();
        linkLikeMoodVO.setLikeKeyId(linkId);
        linkLikeMoodVO.setLikeUserId(user.getUserId());
        boolean isMember=redisTemplate.opsForSet().isMember(linkLikeMoodVO.getLikeKey(),linkLikeMoodVO.getLikeUserId());
        if (isMember){
            return ALREADY_LIKE;
        }
        linkLikeKeyProducer.sendMessage(destination,linkLikeMoodVO);
        return LIKED;
    }
}
