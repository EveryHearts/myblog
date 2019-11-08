package cn.welfareZhu.item.myblog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 朱剑飞
 * @name 文章图片表
 * @date 2019-8-12
 * **/
public class ArticleImg implements Serializable {
    @Null
    private Integer articleImgId;
    @NotNull
    private Integer articleId;
    @NotNull
    private String imgSrc;
    @NotNull
    private byte[] imgByte;
    @NotNull
    private Integer imgType;
    private Integer imgStatus;
    @NotNull
    private Date createDate;
    private Date modifyDate;

    public Integer getArticleImgId() {
        return articleImgId;
    }

    public void setArticleImgId(Integer articleImgId) {
        this.articleImgId = articleImgId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public byte[] getImgByte() {
        return imgByte;
    }

    public void setImgByte(byte[] imgByte) {
        this.imgByte = imgByte;
    }

    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }

    public Integer getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(Integer imgStatus) {
        this.imgStatus = imgStatus;
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
