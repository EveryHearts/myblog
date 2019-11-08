package cn.welfareZhu.item.myblog.dto;

import java.util.Date;

public class UserSearchDTO {
    private int userId;
    private String userNickname;
    private String viaSrc;
    private int isTourist;
    private int userAuth;
    private int userStatus;
    private Date createDate;


    public int getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(int userAuth) {
        this.userAuth = userAuth;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getViaSrc() {
        return viaSrc;
    }

    public void setViaSrc(String viaSrc) {
        this.viaSrc = viaSrc;
    }

    public int getIsTourist() {
        return isTourist;
    }

    public void setIsTourist(int isTourist) {
        this.isTourist = isTourist;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
