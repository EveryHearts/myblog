package cn.welfareZhu.item.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author 朱剑飞
 * @name 页面组件Controller
 * @date 20119-09-11
 * **/
@Controller
@RequestMapping("/component")
public class ComponentController {
    //导航栏页面组件
    @GetMapping("/nav")
    public String getNavigator(){
        return "/component/navigator";
    }

}
