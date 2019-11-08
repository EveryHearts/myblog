package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.LinkComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱剑飞
 * @name 链接评论DAO
 * @date 2019-8-13
 * **/
@Repository
public interface LinkCommentDao {
    //按照评论ID查询
    LinkComment selectCommentByCommentId(Integer commentId);
    //按照链接ID以及评论状态类型查询
    List<LinkComment> selectCommentByLinkIdAndST(@Param("linkId") Integer linkId,@Param("status") Integer status,@Param("type") Integer type);
    //插入新的链接评论
    int insertLinkComment(LinkComment linkComment);
    //按照评论ID更新评论状态
    int updateCommentStatus(@Param("commentId") Integer commentId,@Param("status") Integer status);
}
