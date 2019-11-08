package cn.welfareZhu.item.myblog.dto;

import java.util.Date;

public class FileDTO {
    private int fileId;
    private int userId;
    private String userViaSrc;
    private String userNickname;
    private String fileName;
    private int fileAuth;
    private int fileStatus;
    private int likeNum;
    private int fileType;
    private String filePath;
    private Date createDate;

    public int getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(int fileStatus) {
        this.fileStatus = fileStatus;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileAuth() {
        return fileAuth;
    }

    public void setFileAuth(int fileAuth) {
        this.fileAuth = fileAuth;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
