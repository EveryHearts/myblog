package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.ArticleImg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱剑飞
 * @name 文章图片DAO
 * @date 2019-8-13
 * **/
@Repository
public interface ArticleImgDao {
    //按照图片ID查询
    ArticleImg selectArticleImgByImgId(Integer imgId);
    //按照文章ID查询
    List<ArticleImg> selectArticleImgByArticleId(Integer articleId);
    //插入新的图片
    int insertArticleImg(ArticleImg articleImg);
    //按照文章ID更新图片状态
    int updateArticleImgStatus(@Param("articleId") Integer articleId,@Param("status") Integer status);
}
