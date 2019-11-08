package cn.welfareZhu.item.myblog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 朱剑飞
 * @name 通知信息表
 * @date 2019-8-13
 * **/
public class NotifyInfo implements Serializable {
    @Null
    private Integer notifyId;
    private Integer userId;  //通知接收者
    private Integer originUserId;  //通知制造者
    private Integer originId;
    @NotNull
    private String content;
    private Integer notifyType;
    private Integer notifyAuth;
    private Integer notifyStatus;
    @NotNull
    private Date createDate;
    private Date modifyDate;

    public Integer getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Integer notifyId) {
        this.notifyId = notifyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(Integer originUserId) {
        this.originUserId = originUserId;
    }

    public Integer getOriginId() {
        return originId;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }

    public Integer getNotifyAuth() {
        return notifyAuth;
    }

    public void setNotifyAuth(Integer notifyAuth) {
        this.notifyAuth = notifyAuth;
    }

    public Integer getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(Integer notifyStatus) {
        this.notifyStatus = notifyStatus;
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
