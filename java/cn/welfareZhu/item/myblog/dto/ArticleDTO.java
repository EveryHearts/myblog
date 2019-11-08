package cn.welfareZhu.item.myblog.dto;

import java.util.Date;

public class ArticleDTO {
    private int articleId;
    private int userId;
    private String userViaSrc;
    private String userNickname;
    private String title;
    private String content;
    private String labelValue;
    private int likeNum;
    private int articleAuth;
    private Date createDate;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getArticleAuth() {
        return articleAuth;
    }

    public void setArticleAuth(int articleAuth) {
        this.articleAuth = articleAuth;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
