package cn.welfareZhu.item.myblog.vo;

import java.util.List;

/**
 * @author 朱剑飞
 * @name 评论回复Map
 * @date 2019-10-06
 * **/
public class CommentReplyMap<T> {
    private T comment;
    private List<T> replies;

    public T getComment() {
        return comment;
    }

    public void setComment(T comment) {
        this.comment = comment;
    }

    public List<T> getReplies() {
        return replies;
    }

    public void setReplies(List<T> replies) {
        this.replies = replies;
    }
}
