package cn.welfareZhu.item.myblog.aop;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.model.*;
import cn.welfareZhu.item.myblog.service.NotifyService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 朱剑飞
 * @name 添加通知切面类
 * @date 2019-09-22
 **/
@Aspect
@Component
public class AddNotifyAspectBean {
    @Resource
    private NotifyService notifyService;

    //添加用户注册审核通知
    @AfterReturning(value = "execution(public int cn.welfareZhu.item.myblog.service.UserService.registerUserAccount(*))"
            , returning = "userId")
    public void addAccountAuditNotify(Object userId) {
        if ((int) userId > 0) {
            NotifyInfo notifyInfo = new NotifyInfo();
            notifyInfo.setUserId((int) userId);
            notifyInfo.setNotifyType(TIER_ACCOUNT_AUDIT);
            notifyInfo.setNotifyAuth(AUTH_MANAGE);
            notifyInfo.setNotifyStatus(WAIT_PROCESS);
            notifyInfo.setContent("新用户注册须审核");
            notifyInfo.setCreateDate(new Date());
            notifyService.addNewNotify(notifyInfo);
        }
    }

    //添加文章审核通知
    @AfterReturning(value = "execution(public * cn.welfareZhu.item.myblog.service.ArticleService.addNewArticle(..))"
            , returning = "article")
    public void addArticleAuditNotify(ArticleInfo article) {
        if (article == null) {
            return;
        }
        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setUserId(article.getUserId());
        notifyInfo.setOriginId(article.getArticleId());
        notifyInfo.setNotifyType(TIER_ARTICLE_AUDIT);
        notifyInfo.setNotifyAuth(AUTH_MANAGE);
        notifyInfo.setNotifyStatus(WAIT_PROCESS);
        notifyInfo.setContent(article.getTitle());
        notifyInfo.setCreateDate(new Date());
        notifyService.addNewNotify(notifyInfo);
    }

    //添加文章评论通知
    @AfterReturning(value = "execution(public * cn.welfareZhu.item.myblog.service.ArticleCommentService.addNewArticleComment(..))"
            , returning = "articleComment")
    public void addArticleCommentNotify(ArticleComment articleComment) {
        if (articleComment == null) {
            return;
        }
        if (articleComment.getUserId() == articleComment.getClientId()) {
            return;
        }
        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setUserId(articleComment.getClientId());
        notifyInfo.setOriginUserId(articleComment.getUserId());
        notifyInfo.setOriginId(articleComment.getArticleCommentId());
        if (articleComment.getCommentType() == COMMENT) {
            notifyInfo.setNotifyType(TIER_ARTICLE_COMMENT);
        }
        if (articleComment.getCommentType() == REPLY) {
            notifyInfo.setNotifyType(TIER_ARTICLE_REPLY);
        }
        notifyInfo.setNotifyAuth(AUTH_SINGLE);
        notifyInfo.setNotifyStatus(WAIT_PROCESS);
        notifyInfo.setContent(articleComment.getContent());
        notifyInfo.setCreateDate(new Date());
        notifyService.addNewNotify(notifyInfo);
    }

    //添加收藏链接审核通知
    @AfterReturning(value = "execution(public * cn.welfareZhu.item.myblog.service.LinkService.addNewLink(..))"
            , returning = "linkInfo")
    public void addLinkAuditNotify(LinkInfo linkInfo) {
        if (linkInfo == null) {
            return;
        }
        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setUserId(linkInfo.getUserId());
        notifyInfo.setOriginId(linkInfo.getLinkId());
        notifyInfo.setNotifyType(TIER_LINK_AUDIT);
        notifyInfo.setNotifyAuth(AUTH_MANAGE);
        notifyInfo.setNotifyStatus(WAIT_PROCESS);
        notifyInfo.setContent(linkInfo.getTitle());
        notifyInfo.setCreateDate(new Date());
        notifyService.addNewNotify(notifyInfo);
    }

    //添加收藏链接评论通知
    @AfterReturning(value = "execution(public * cn.welfareZhu.item.myblog.service.LinkCommentService.addNewLinkComment(..))"
            , returning = "linkComment")
    public void addLinkCommentNotify(LinkComment linkComment) {
        if (linkComment == null) {
            return;
        }
        if (linkComment.getUserId() == linkComment.getClientId()) {
            return;
        }
        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setUserId(linkComment.getClientId());
        notifyInfo.setOriginUserId(linkComment.getUserId());
        notifyInfo.setOriginId(linkComment.getLinkCommentId());
        if (linkComment.getCommentType() == COMMENT) {
            notifyInfo.setNotifyType(TIER_LINK_COMMENT);
        }
        if (linkComment.getCommentType() == REPLY) {
            notifyInfo.setNotifyType(TIER_LINK_REPLY);
        }
        notifyInfo.setNotifyAuth(AUTH_SINGLE);
        notifyInfo.setNotifyStatus(WAIT_PROCESS);
        notifyInfo.setContent(linkComment.getContent());
        notifyInfo.setCreateDate(new Date());
        notifyService.addNewNotify(notifyInfo);
    }

    //添加文件审核通知
    @AfterReturning(value = "execution(public * cn.welfareZhu.item.myblog.service.FileService.addNewFile(..))"
            , returning = "fileInfo")
    public void addFileAuditNotify(FileInfo fileInfo) {
        if (fileInfo == null) {
            return;
        }
        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setUserId(fileInfo.getUserId());
        notifyInfo.setOriginId(fileInfo.getFileId());
        notifyInfo.setNotifyType(TIER_FILE_AUDIT);
        notifyInfo.setNotifyAuth(AUTH_MANAGE);
        notifyInfo.setNotifyStatus(WAIT_PROCESS);
        notifyInfo.setContent(fileInfo.getFileName());
        notifyInfo.setCreateDate(new Date());
        notifyService.addNewNotify(notifyInfo);
    }

    //添加文件评论通知
    @AfterReturning(value = "execution(public * cn.welfareZhu.item.myblog.service.FileCommentService.addNewFileComment(..))"
            , returning = "fileComment")
    public void addFileCommentNotify(FileComment fileComment) {
        if (fileComment == null) {
            return;
        }
        if (fileComment.getUserId() == fileComment.getClientId()) {
            return;
        }
        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setUserId(fileComment.getClientId());
        notifyInfo.setOriginUserId(fileComment.getUserId());
        notifyInfo.setOriginId(fileComment.getFileCommentId());
        if (fileComment.getCommentType() == COMMENT) {
            notifyInfo.setNotifyType(TIER_FILE_COMMENT);
        }
        if (fileComment.getCommentType() == REPLY) {
            notifyInfo.setNotifyType(TIER_FILE_REPLY);
        }
        notifyInfo.setNotifyAuth(AUTH_SINGLE);
        notifyInfo.setNotifyStatus(WAIT_PROCESS);
        notifyInfo.setContent(fileComment.getContent());
        notifyInfo.setCreateDate(new Date());
        notifyService.addNewNotify(notifyInfo);
    }

    //添加留言通知
    @AfterReturning(value = "execution(public * cn.welfareZhu.item.myblog.service.MessageService.addNewMessage(..))"
            , returning = "message")
    public void addMessageNotify(Message message) {
        if (message == null) {
            return;
        }
        if (message.getUserId() == message.getVisitorId()) {
            return;
        }
        NotifyInfo notifyInfo = new NotifyInfo();
        if (message.getMessageType() == USER_REPLY) {
            notifyInfo.setUserId(message.getVisitorId());
            notifyInfo.setOriginUserId(message.getUserId());
        }
        if (message.getMessageType() == MESSAGE || message.getMessageType() == VISITOR_REPLY) {
            notifyInfo.setUserId(message.getUserId());
            notifyInfo.setOriginUserId(message.getVisitorId());
        }
        notifyInfo.setOriginId(message.getMessageId());
        if (message.getMessageType() == MESSAGE) {
            notifyInfo.setNotifyType(TIER_MESSAGE);
        }
        if (message.getMessageType() == USER_REPLY || message.getMessageType() == VISITOR_REPLY) {
            notifyInfo.setNotifyType(TIER_MESSAGE_REPLY);
        }
        notifyInfo.setNotifyAuth(AUTH_SINGLE);
        notifyInfo.setNotifyStatus(WAIT_PROCESS);
        notifyInfo.setContent(message.getContent());
        notifyInfo.setCreateDate(new Date());
        notifyService.addNewNotify(notifyInfo);
    }
}
