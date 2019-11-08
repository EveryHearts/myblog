package cn.welfareZhu.item.myblog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 朱剑飞
 * @name 文件信息表
 * @date 2019-8-13
 * **/
public class FileInfo implements Serializable {
    @Null
    private Integer fileId;
    @NotNull
    private Integer userId;
    @NotNull(message = "文件名不能为空")
    private String fileName;
    private Integer fileAuth;
    private Integer likeNum;
    @NotNull(message = "文件类型错误")
    private Integer fileType;
    @NotNull(message = "文件地址不能为空")
    private String filePath;  //文件描述
    private Integer fileStatus;
    @NotNull
    private Date createDate;
    private Date modifyDate;
    private FileByte fileByte;
    private List<FileComment> fileComments;

    public FileByte getFileByte() {
        return fileByte;
    }

    public void setFileByte(FileByte fileByte) {
        this.fileByte = fileByte;
    }

    public List<FileComment> getFileComments() {
        return fileComments;
    }

    public void setFileComments(List<FileComment> fileComments) {
        this.fileComments = fileComments;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileAuth() {
        return fileAuth;
    }

    public void setFileAuth(Integer fileAuth) {
        this.fileAuth = fileAuth;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(Integer fileStatus) {
        this.fileStatus = fileStatus;
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
