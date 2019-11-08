package cn.welfareZhu.item.myblog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 朱剑飞
 * @name 用户检索表
 * @date 2019-8-12
 * **/
public class UserSearch implements Serializable {
    @Null
    private Integer userId;
    @NotNull(message = "昵称不能为空")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]{1,15}$",message = "昵称不合法，请输入15位以内的中英文或者数字")
    private String userNickname;
    private String userViaSrc;
    private byte[] userViaByte;
    private Integer isTourist;
    private Integer userStatus;
    private String userIp;
    private Date createDate;
    private Date modifyDate;
    private UserInfo userInfo;
    private UserLogin userLogin;
    private List<NotifyInfo> notifies;
    private List<Message> messages;
    private List<ArticleInfo> articles;
    private List<LinkInfo> links;
    private List<FileInfo> files;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public List<NotifyInfo> getNotifies() {
        return notifies;
    }

    public void setNotifies(List<NotifyInfo> notifies) {
        this.notifies = notifies;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<ArticleInfo> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleInfo> articles) {
        this.articles = articles;
    }

    public List<LinkInfo> getLinks() {
        return links;
    }

    public void setLinks(List<LinkInfo> links) {
        this.links = links;
    }

    public List<FileInfo> getFiles() {
        return files;
    }

    public void setFiles(List<FileInfo> files) {
        this.files = files;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserViaSrc() {
        return userViaSrc;
    }

    public void setUserViaSrc(String userViaSrc) {
        this.userViaSrc = userViaSrc;
    }

    public byte[] getUserViaByte() {
        return userViaByte;
    }

    public void setUserViaByte(byte[] userViaByte) {
        this.userViaByte = userViaByte;
    }

    public Integer getIsTourist() {
        return isTourist;
    }

    public void setIsTourist(Integer isTourist) {
        this.isTourist = isTourist;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
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
