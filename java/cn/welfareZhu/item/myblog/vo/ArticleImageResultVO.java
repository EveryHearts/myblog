package cn.welfareZhu.item.myblog.vo;

import java.io.Serializable;

public class ArticleImageResultVO implements Serializable {
    private int code;
    private String msg;
    private ImageDataVO data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ImageDataVO getData() {
        return data;
    }

    public void setData(ImageDataVO data) {
        this.data = data;
    }
}
