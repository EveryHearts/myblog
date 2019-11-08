package cn.welfareZhu.item.myblog.vo;

/**
 * @author 朱剑飞
 * @name 用户注册VO
 * @date 2019-08-26
 * **/
public class UserRegister {
    private String userNickname;
    private String account;
    private String password;
    private String securityCode;
    private String userIp;

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }
}
