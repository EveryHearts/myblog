package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.ArticleInfo;
import cn.welfareZhu.item.myblog.util.QueryResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱剑飞
 * @name 文章信息DAO
 * @date 2019-8-13
 * **/
@Repository
public interface ArticleInfoDao {
    //按照文章ID查询
    ArticleInfo selectArticleInfoByArticleId(Integer articleId);
    //按照文章权限、状态查询
    List<ArticleInfo> selectArticleInfoByAS(@Param("auth") Integer auth,@Param("status") Integer status);
    //按照文章权限、状态、匹配标题查询
    List<ArticleInfo> selectArticleInfoByAST(@Param("auth") Integer auth,@Param("status") Integer status,@Param("title") String title);
    //按照文章权限、状态、标签查询
    List<ArticleInfo> selectArticleInfoByASL(@Param("auth") Integer auth,@Param("status") Integer status,@Param("labelId") Integer labelId);
    //按照用户ID、权限查询
    List<ArticleInfo> selectArticleByUserIdAndAuth(@Param("userId") Integer userId,@Param("auth") Integer auth);
    //按照用户ID、状态查询
    List<ArticleInfo> selectArticleByUserIdAndStatus(@Param("userId") Integer userId,@Param("status") Integer status);
    //按照用户ID以及权限、状态查询
    List<ArticleInfo> selectArticleByUserIdAndAuthAndStatus(@Param("userId") Integer userId,@Param("auth") Integer auth,@Param("status") Integer status);
    //按照文章ID查询文章用户ID、权限、状态
    QueryResult selectArticleUAS(Integer articleId);
    //插入新的文章
    int insertArticleInfo(ArticleInfo articleInfo);
    //按照文章ID更新文章状态
    int updateArticleStatus(@Param("articleId") Integer articleId,@Param("status") Integer status);
    //按照文章ID更新文章权限
    int updateArticleAuth(@Param("articleId") Integer articleId,@Param("auth") Integer auth);
    //按照文章ID增加其点赞量
    int increaseArticleLikeNum(Integer articleId);
}
