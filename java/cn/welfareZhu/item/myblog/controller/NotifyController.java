package cn.welfareZhu.item.myblog.controller;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.dto.UserSearchDTO;
import cn.welfareZhu.item.myblog.service.NotifyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 朱剑飞
 * @name 通知Controller
 * @date 2019-09-24
 * **/
@Controller
@RequestMapping("/notifies")
public class NotifyController {

    @Resource
    private NotifyService notifyService;

    //查询登陆用户通知数量
    @GetMapping("/loginUserNotifyNum")
    @ResponseBody
    public int queryLoginUserNotifyNum(HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return QUERY_FAIL;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        return notifyService.queryCountForNotifyByUserId(user.getUserId(),WAIT_PROCESS);
    }

    //查询管理员通知数量
    @GetMapping("/managerNotifyNum")
    @ResponseBody
    public int queryManagerNotifyNum(HttpSession session){
        if ((int)session.getAttribute(LOGIN_STATUS)!=USER){
            return QUERY_FAIL;
        }
        UserSearchDTO user=(UserSearchDTO)session.getAttribute(LOGIN_USER);
        if (user.getUserAuth()!=MANAGER&&user.getUserAuth()!=HOST){
            return -1;
        }
        return notifyService.queryCountForNotifyByAuthAndStatus(AUTH_MANAGE,WAIT_PROCESS);
    }
}
