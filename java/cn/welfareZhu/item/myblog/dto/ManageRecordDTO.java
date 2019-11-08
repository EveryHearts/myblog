package cn.welfareZhu.item.myblog.dto;

import java.util.Date;

public class ManageRecordDTO {
    private int recordId;
    private int managerId;
    private String managerNickname;
    private String managerViaSrc;
    private int recordStatus;
    private int processResult;
    private int originId;
    private int originType;
    private int originUserId;
    private String originUserNickname;
    private String originUserViaSrc;
    private String content;
    private Date createDate;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getManagerNickname() {
        return managerNickname;
    }

    public void setManagerNickname(String managerNickname) {
        this.managerNickname = managerNickname;
    }

    public String getManagerViaSrc() {
        return managerViaSrc;
    }

    public void setManagerViaSrc(String managerViaSrc) {
        this.managerViaSrc = managerViaSrc;
    }

    public int getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(int recordStatus) {
        this.recordStatus = recordStatus;
    }

    public int getProcessResult() {
        return processResult;
    }

    public void setProcessResult(int processResult) {
        this.processResult = processResult;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public int getOriginType() {
        return originType;
    }

    public void setOriginType(int originType) {
        this.originType = originType;
    }

    public int getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(int originUserId) {
        this.originUserId = originUserId;
    }

    public String getOriginUserNickname() {
        return originUserNickname;
    }

    public void setOriginUserNickname(String originUserNickname) {
        this.originUserNickname = originUserNickname;
    }

    public String getOriginUserViaSrc() {
        return originUserViaSrc;
    }

    public void setOriginUserViaSrc(String originUserViaSrc) {
        this.originUserViaSrc = originUserViaSrc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
