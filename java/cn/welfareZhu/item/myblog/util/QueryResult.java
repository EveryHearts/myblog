package cn.welfareZhu.item.myblog.util;

import java.io.Serializable;

public class QueryResult implements Serializable {
    private Integer userId;
    private Integer authValue;
    private Integer statusValue;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAuthValue() {
        return authValue;
    }

    public void setAuthValue(Integer authValue) {
        this.authValue = authValue;
    }

    public Integer getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
    }
}
