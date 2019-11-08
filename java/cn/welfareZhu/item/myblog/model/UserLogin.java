package cn.welfareZhu.item.myblog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

/**
 * @author 朱剑飞
 * @name 用户登录表
 * @date 2019-8-12
 * **/
public class UserLogin implements Serializable {
    @NotNull
    private Integer userId;
    private Integer userAuth;
    @NotNull(message = "您的密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$",message = "您输入的密码不合法")
    private String userPassword;
    @NotNull(message = "您的安全码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$",message = "您输入的安全码不合法")
    private String userSecurityCode;
    @NotNull(message = "账号不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,16}$",message = "您输入的账号不合法")
    private String userAccount;
    private Integer userStatus;
    private Date createDate;
    private Date modifyDate;
    private UserSearch userSearch;

    public UserSearch getUserSearch() {
        return userSearch;
    }

    public void setUserSearch(UserSearch userSearch) {
        this.userSearch = userSearch;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(Integer userAuth) {
        this.userAuth = userAuth;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserSecurityCode() {
        return userSecurityCode;
    }

    public void setUserSecurityCode(String userSecurityCode) {
        this.userSecurityCode = userSecurityCode;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
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
