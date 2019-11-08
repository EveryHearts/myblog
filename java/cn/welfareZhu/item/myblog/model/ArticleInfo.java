package cn.welfareZhu.item.myblog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 朱剑飞
 * @name 文章信息表
 * @date 2019-8-12
 * **/
public class ArticleInfo implements Serializable {
    @NotNull
    private Integer articleId;
    @NotNull
    private Integer userId;
    @NotNull(message = "文章标题不能为空")
    @Pattern(regexp = "^([\\u4E00-\\u9FA5A-Za-z0-9_]|[\\（\\）\\《\\》\\——\\；\\，\\。\\“\\”\\<\\>\\！]){1,15}$",message = "您的文章标题不合法")
    private String title;
    @NotNull(message = "文章内容不能为空")
    private String content;
    @NotNull
    private Integer labelId;
    private Integer likeNum;
    private Integer articleAuth;
    private Integer articleStatus;
    @NotNull
    private Date createDate;
    private Date modifyDate;
    private List<ArticleImg> articleImgs;
    private List<ArticleComment> articleComments;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getArticleAuth() {
        return articleAuth;
    }

    public void setArticleAuth(Integer articleAuth) {
        this.articleAuth = articleAuth;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
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

    public List<ArticleImg> getArticleImgs() {
        return articleImgs;
    }

    public void setArticleImgs(List<ArticleImg> articleImgs) {
        this.articleImgs = articleImgs;
    }

    public List<ArticleComment> getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(List<ArticleComment> articleComments) {
        this.articleComments = articleComments;
    }
}
