package cn.welfareZhu.item.myblog.model;

import org.omg.CORBA.INTERNAL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 朱剑飞
 * @name 收藏链接评论表
 * @date 2019-8-13
 * **/
public class LinkComment implements Serializable {
    @Null
    private Integer linkCommentId;
    @NotNull
    private Integer linkId;
    @NotNull(message = "评论内容不能为空")
    private String content;
    @NotNull
    private Integer userId;  //评论/回复发起者
    private Integer clientId;  //评论/回复接收者
    private Integer commentType;
    private Integer commentStatus;
    @NotNull
    private Date createDate;
    private Date modifyDate;

    public Integer getLinkCommentId() {
        return linkCommentId;
    }

    public void setLinkCommentId(Integer linkCommentId) {
        this.linkCommentId = linkCommentId;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }
}
