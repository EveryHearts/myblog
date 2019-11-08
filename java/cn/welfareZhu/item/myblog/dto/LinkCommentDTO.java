package cn.welfareZhu.item.myblog.dto;

import java.util.Date;

public class LinkCommentDTO {
    private int linkCommentId;
    private int linkId;
    private String content;
    private int userId;
    private String userViaSrc;
    private String userNickname;
    private int clientId;
    private String clientViaSrc;
    private String clientNickname;
    private int commentType;
    private Date createDate;

    public int getLinkCommentId() {
        return linkCommentId;
    }

    public void setLinkCommentId(int linkCommentId) {
        this.linkCommentId = linkCommentId;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserViaSrc() {
        return userViaSrc;
    }

    public void setUserViaSrc(String userViaSrc) {
        this.userViaSrc = userViaSrc;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientViaSrc() {
        return clientViaSrc;
    }

    public void setClientViaSrc(String clientViaSrc) {
        this.clientViaSrc = clientViaSrc;
    }

    public String getClientNickname() {
        return clientNickname;
    }

    public void setClientNickname(String clientNickname) {
        this.clientNickname = clientNickname;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
