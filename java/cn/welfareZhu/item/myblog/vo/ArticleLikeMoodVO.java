package cn.welfareZhu.item.myblog.vo;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import java.io.Serializable;

/**
 * @author 朱剑飞
 * @name 文章点赞VO
 * @date 2019-10-05
 * **/
public class ArticleLikeMoodVO implements Serializable {
    private int likeKeyId;
    private int likeUserId;

    public int getLikeKeyId() {
        return likeKeyId;
    }

    public void setLikeKeyId(int likeKeyId) {
        this.likeKeyId = likeKeyId;
    }

    public int getLikeUserId() {
        return likeUserId;
    }

    public void setLikeUserId(int likeUserId) {
        this.likeUserId = likeUserId;
    }

    public String getLikeKey(){
        return ART_LIKE_KEY_PREFFIX+likeKeyId;
    }
}
