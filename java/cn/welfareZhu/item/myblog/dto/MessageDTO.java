package cn.welfareZhu.item.myblog.dto;

import java.util.Date;

public class MessageDTO {
    private int messageId;
    private int userId;
    private String userViaSrc;
    private String userNickname;
    private int visitorId;
    private String visitorViaSrc;
    private String visitorNickname;
    private int type;
    private String content;
    private Date createDate;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
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

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorViaSrc() {
        return visitorViaSrc;
    }

    public void setVisitorViaSrc(String visitorViaSrc) {
        this.visitorViaSrc = visitorViaSrc;
    }

    public String getVisitorNickname() {
        return visitorNickname;
    }

    public void setVisitorNickname(String visitorNickname) {
        this.visitorNickname = visitorNickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
