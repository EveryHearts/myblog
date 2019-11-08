package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.ArticleComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 朱剑飞
 * @name 文章评论DAO
 * @date 2019-8-13
 * **/
@Repository
public interface ArticleCommentDao {
    //按照评论ID查询
    ArticleComment selectArticleCommentByCommentId(Integer commentId);
    //按照文章ID和评论状态、类型查询
    List<ArticleComment> selectCommentByArticleIdAndStatusType(@Param("articleId") Integer articleId,@Param("status") Integer status,@Param("type") Integer type);
    //插入新的评论
    int insertArticleComment(ArticleComment articleComment);
    //按照评论ID更新评论状态
    int updateCommentStatus(@Param("commentId") Integer commentId,@Param("status") Integer status);
}
