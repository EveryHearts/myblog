package cn.welfareZhu.item.myblog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 朱剑飞
 * @name 收藏链接信息表
 * @date 2019-8-13
 * **/
public class LinkInfo implements Serializable {
    @Null
    private Integer linkId;
    @NotNull
    private Integer userId;
    @NotNull(message = "您输入的链接不能为空")
    @Pattern(regexp = "(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\\\+&amp;%\\$#_]*)?",message = "您输入的链接不合法")
    private String linkSrc;
    @NotNull(message = "链接收藏标题不能为空")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]{1,20}$",message = "您输入的链接标题不合法")
    private String title;
    @NotNull(message = "收藏链接描述不能为空")
    private String description;
    @NotNull
    private Integer labelId;
    private Integer likeNum;
    private Integer linkStatus;
    @NotNull
    private Date createDate;
    private Date modifyDate;
    private List<LinkComment> linkComments;

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLinkSrc() {
        return linkSrc;
    }

    public void setLinkSrc(String linkSrc) {
        this.linkSrc = linkSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(Integer linkStatus) {
        this.linkStatus = linkStatus;
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

    public List<LinkComment> getLinkComments() {
        return linkComments;
    }

    public void setLinkComments(List<LinkComment> linkComments) {
        this.linkComments = linkComments;
    }
}
