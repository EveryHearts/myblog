package cn.welfareZhu.item.myblog.vo;

/**
 * @author 朱剑飞
 * @name 评论回复VO
 * @date 2019-10-06
 * **/
public class ReplyVO {
    private int commentId;  //评论ID
    private int clientId;  //接收者ID
    private int mainObjectId;  //源对象ID
    private String content;  //评论回复内容

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getMainObjectId() {
        return mainObjectId;
    }

    public void setMainObjectId(int mainObjectId) {
        this.mainObjectId = mainObjectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
