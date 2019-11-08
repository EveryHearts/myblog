package cn.welfareZhu.item.myblog.service;

import cn.welfareZhu.item.myblog.dto.ArticleDTO;
import cn.welfareZhu.item.myblog.model.ArticleImg;
import cn.welfareZhu.item.myblog.model.ArticleInfo;
import cn.welfareZhu.item.myblog.util.QueryPageMap;
import cn.welfareZhu.item.myblog.util.QueryResult;

import java.util.List;

public interface ArticleService {
    //按照文章ID查询
    ArticleDTO queryArticleByArticleId(Integer articleId);
    //按照文章权限、状态查询
    QueryPageMap<ArticleDTO> queryArticleByAuthAndStatus(Integer auth,Integer status,int pageNum,int pageSize);
    //按照文章权限、状态以及匹配标题查询
    QueryPageMap<ArticleDTO> queryArticleByASAndTitle(Integer auth,Integer status,String title,int pageNum,int pageSize);
    //按照文章权限、状态以及标签查询
    QueryPageMap<ArticleDTO> queryArticleByASAndLabel(Integer auth,Integer status,Integer labelId,int pageNum,int pageSize);
    //按照用户ID、权限查询
    QueryPageMap<ArticleDTO> queryArticleByUserIdAndAuth(Integer userId,Integer auth,int pageNum,int pageSize);
    //按照用户ID、状态查询
    QueryPageMap<ArticleDTO> queryArticleByUserIdAndStatus(Integer userId,Integer status,int pageNum,int pageSize);
    //按照用户ID以及权限、状态查询
    QueryPageMap<ArticleDTO> queryArticleByUserIdAndAuthAndStatus(Integer userId,Integer auth,Integer status,int pageNum,int pageSize);
    //按照文章ID查询文章的用户ID、权限以及状态
    QueryResult queryArticleUAS(Integer articleId);
    //添加新的文章
    ArticleInfo addNewArticle(ArticleInfo articleInfo);
    //按照文章ID修改文章状态
    boolean modifyArticleStatus(Integer articleId,Integer status);
    //按照文章ID修改文章权限
    boolean modifyArticleAuth(Integer articleId,Integer auth);
    //按照文章ID增加点赞数量
    boolean increaseArticleLikeNum(Integer articleId);

    //文章图片
    //按照文章图片ID查询图片
    ArticleImg queryArticleImgByImgId(Integer imgId);
    //按照文章ID查询图片
    List<ArticleImg> queryArticleImgByArticleId(Integer articleId);
    //添加新的文章图片
    boolean addNewArticleImg(ArticleImg articleImg);
    //按照文章ID修改图片状态
    boolean modifyArticleImgStatus(Integer articleId,Integer status);
}
