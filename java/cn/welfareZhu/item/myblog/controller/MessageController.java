package cn.welfareZhu.item.myblog.controller;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.dto.MessageDTO;
import cn.welfareZhu.item.myblog.dto.UserSearchDTO;
import cn.welfareZhu.item.myblog.model.Message;
import cn.welfareZhu.item.myblog.service.MessageService;
import cn.welfareZhu.item.myblog.service.UserService;
import cn.welfareZhu.item.myblog.util.QueryPageMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author 朱剑飞
 * @name 留言Controller
 * @date 2019-10-11
 * **/
@Controller
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;
    @Resource
    private UserService userService;

    //跳转到用户留言页面
    @GetMapping("/browseMessage")
    public String gotoMessagePage(@RequestParam("userId") int userId, @RequestParam("pageNo") int pageNo, Model model){
        UserSearchDTO user=userService.queryUserByUserId(userId);
        if (user==null){
            model.addAttribute(MODEL_MESSAGE,"你提供的用户ID不存在");
            return "home";
        }
        QueryPageMap<MessageDTO> messages=messageService.queryMessageByUserId(userId,pageNo,PAGE_SIZE);
        model.addAttribute(NORMAL_PART,messages);
        model.addAttribute(ACCESS_USER,user);
        return "message/messageBrowser";
    }

    //添加用户留言
    @PostMapping("/uploadMessage")
    @ResponseBody
    public int addMessage(Message message,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return 1;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        if (user.getUserStatus()!=NORMAL){
            return -1;
        }
        if (message.getMessageType()==MESSAGE||message.getMessageType()==VISITOR_REPLY){
            message.setVisitorId(user.getUserId());
        }
        if (message.getMessageType()==USER_REPLY){
            message.setUserId(user.getUserId());
        }
        message.setMessageStatus(NORMAL);
        message.setCreateDate(new Date());
        messageService.addNewMessage(message);
        return 0;
    }

    //删除用户留言
    @GetMapping("/deleteMessage")
    @ResponseBody
    public int deleteMessage(@RequestParam("messageId") int messageId,HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return IS_NOT_OK;
        }
        MessageDTO messageDTO=messageService.queryMessageByMessageId(messageId);
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        if (user.getUserId()!=messageDTO.getUserId()){
            return IS_NOT_OK;
        }
        messageService.modifyMessageStatus(messageId,DELETED);
        return IS_OK;
    }
}
