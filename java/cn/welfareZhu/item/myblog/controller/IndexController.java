package cn.welfareZhu.item.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 朱剑飞
 * @name 通用Controller
 * @date 2019-09-15
 * **/
@Controller
@RequestMapping("/home")
public class IndexController {
    //跳转到首页
    @RequestMapping("/homePage")
    public String gotoHomePage(){
        return "home";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
