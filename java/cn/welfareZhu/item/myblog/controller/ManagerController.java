package cn.welfareZhu.item.myblog.controller;

import cn.welfareZhu.item.myblog.service.ManageRecordService;
import cn.welfareZhu.item.myblog.service.NotifyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

/**
 * @author 朱剑飞
 * @name 管理层Controller
 * @date 2019-09-22
 * **/
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Resource
    private NotifyService notifyService;
    @Resource
    private ManageRecordService manageRecordService;
}
