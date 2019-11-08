package cn.welfareZhu.item.myblog.dto;

import java.util.Date;

public class NotifyDTO {
    private int notifyId;
    private int originUserId;
    private String originUserViaSrc;
    private String originUserNickname;
    private int originId;
    private String content;
    private int notifyType;
    private Date createDate;

    public int getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(int notifyId) {
        this.notifyId = notifyId;
    }

    public int getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(int originUserId) {
        this.originUserId = originUserId;
    }

    public String getOriginUserViaSrc() {
        return originUserViaSrc;
    }

    public void setOriginUserViaSrc(String originUserViaSrc) {
        this.originUserViaSrc = originUserViaSrc;
    }

    public String getOriginUserNickname() {
        return originUserNickname;
    }

    public void setOriginUserNickname(String originUserNickname) {
        this.originUserNickname = originUserNickname;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(int notifyType) {
        this.notifyType = notifyType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
