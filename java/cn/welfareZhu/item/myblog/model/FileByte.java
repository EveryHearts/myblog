package cn.welfareZhu.item.myblog.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 * @author 朱剑飞
 * @name 文件数据流表
 * @date 2019-8-13
 * **/
public class FileByte implements Serializable {
    @NotNull
    private Integer fileId;
    @NotNull(message = "文件数据流写入为空")
    private byte[] fileByte;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }
}
