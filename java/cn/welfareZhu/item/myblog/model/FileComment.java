package cn.welfareZhu.item.myblog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 朱剑飞
 * @name 文件评论表
 * @date 2019-8-13
 * **/
public class FileComment implements Serializable {
    @Null
    private Integer fileCommentId;
    @NotNull
    private Integer fileId;
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

    public Integer getFileCommentId() {
        return fileCommentId;
    }

    public void setFileCommentId(Integer fileCommentId) {
        this.fileCommentId = fileCommentId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
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
}
