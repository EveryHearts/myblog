package cn.welfareZhu.item.myblog.service.impl;

import cn.welfareZhu.item.myblog.dao.ArticleImgDao;
import cn.welfareZhu.item.myblog.dao.ArticleInfoDao;
import cn.welfareZhu.item.myblog.dao.LabelInfoDao;
import cn.welfareZhu.item.myblog.dao.UserSearchDao;
import cn.welfareZhu.item.myblog.dto.ArticleDTO;
import cn.welfareZhu.item.myblog.model.ArticleImg;
import cn.welfareZhu.item.myblog.model.ArticleInfo;
import cn.welfareZhu.item.myblog.model.LabelInfo;
import cn.welfareZhu.item.myblog.model.UserSearch;
import cn.welfareZhu.item.myblog.service.ArticleService;
import cn.welfareZhu.item.myblog.util.QueryPageMap;
import cn.welfareZhu.item.myblog.util.QueryResult;
import cn.welfareZhu.item.myblog.util.QuerySQLException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 朱剑飞
 * @name 文章Service
 * @date 2019-09-03
 * **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private UserSearchDao userSearchDao;
    @Resource
    private LabelInfoDao labelInfoDao;
    @Resource
    private ArticleInfoDao articleInfoDao;
    @Resource
    private ArticleImgDao articleImgDao;

    @Override
    public ArticleDTO queryArticleByArticleId(Integer articleId) {
        ArticleInfo articleInfo=articleInfoDao.selectArticleInfoByArticleId(articleId);
        if (articleInfo==null){
            return null;
        }
        ArticleDTO article=new ArticleDTO();
        article.setArticleId(articleInfo.getArticleId());
        article.setTitle(articleInfo.getTitle());
        article.setContent(articleInfo.getContent());
        article.setArticleAuth(articleInfo.getArticleAuth());
        article.setCreateDate(articleInfo.getCreateDate());
        article.setLikeNum(articleInfo.getLikeNum());
        LabelInfo labelInfo=labelInfoDao.selectLabelByLabelId(articleInfo.getLabelId());
        article.setLabelValue(labelInfo.getLabelValue());
        UserSearch user=userSearchDao.selectUserSearchByUserId(articleInfo.getUserId());
        article.setUserId(user.getUserId());
        article.setUserNickname(user.getUserNickname());
        article.setUserViaSrc(user.getUserViaSrc());
        return article;
    }

    @Override
    public QueryPageMap<ArticleDTO> queryArticleByAuthAndStatus(Integer auth, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArticleInfo> articleInfos=articleInfoDao.selectArticleInfoByAS(auth,status);
        PageInfo pageInfo=new PageInfo(articleInfos);
        if (articleInfos.size()<=0){
            return null;
        }
        List<ArticleDTO> articles=new ArrayList<>();
        for (ArticleInfo articleInfo:articleInfos){
            ArticleDTO article=new ArticleDTO();
            article.setArticleId(articleInfo.getArticleId());
            article.setTitle(articleInfo.getTitle());
            article.setContent(articleInfo.getContent());
            article.setArticleAuth(articleInfo.getArticleAuth());
            article.setCreateDate(articleInfo.getCreateDate());
            article.setLikeNum(articleInfo.getLikeNum());
            LabelInfo labelInfo=labelInfoDao.selectLabelByLabelId(articleInfo.getLabelId());
            article.setLabelValue(labelInfo.getLabelValue());
            UserSearch user=userSearchDao.selectUserSearchByUserId(articleInfo.getUserId());
            article.setUserId(user.getUserId());
            article.setUserNickname(user.getUserNickname());
            article.setUserViaSrc(user.getUserViaSrc());
            articles.add(article);
        }
        QueryPageMap<ArticleDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(articles);
        return queryPageMap;
    }

    @Override
    public QueryPageMap<ArticleDTO> queryArticleByASAndTitle(Integer auth, Integer status, String title, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArticleInfo> articleInfos=articleInfoDao.selectArticleInfoByAST(auth,status,title);
        PageInfo pageInfo=new PageInfo(articleInfos);
        if (articleInfos.size()<=0){
            return null;
        }
        List<ArticleDTO> articles=new ArrayList<>();
        for (ArticleInfo articleInfo:articleInfos){
            ArticleDTO article=new ArticleDTO();
            article.setArticleId(articleInfo.getArticleId());
            article.setTitle(articleInfo.getTitle());
            article.setContent(articleInfo.getContent());
            article.setArticleAuth(articleInfo.getArticleAuth());
            article.setCreateDate(articleInfo.getCreateDate());
            article.setLikeNum(articleInfo.getLikeNum());
            LabelInfo labelInfo=labelInfoDao.selectLabelByLabelId(articleInfo.getLabelId());
            article.setLabelValue(labelInfo.getLabelValue());
            UserSearch user=userSearchDao.selectUserSearchByUserId(articleInfo.getUserId());
            article.setUserId(user.getUserId());
            article.setUserNickname(user.getUserNickname());
            article.setUserViaSrc(user.getUserViaSrc());
            articles.add(article);
        }
        QueryPageMap<ArticleDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(articles);
        return queryPageMap;
    }

    @Override
    public QueryPageMap<ArticleDTO> queryArticleByASAndLabel(Integer auth, Integer status, Integer labelId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArticleInfo> articleInfos=articleInfoDao.selectArticleInfoByASL(auth,status,labelId);
        PageInfo pageInfo=new PageInfo(articleInfos);
        if (articleInfos.size()<=0){
            return null;
        }
        LabelInfo labelInfo=labelInfoDao.selectLabelByLabelId(labelId);
        List<ArticleDTO> articles=new ArrayList<>();
        for (ArticleInfo articleInfo:articleInfos){
            ArticleDTO article=new ArticleDTO();
            article.setArticleId(articleInfo.getArticleId());
            article.setTitle(articleInfo.getTitle());
            article.setContent(articleInfo.getContent());
            article.setArticleAuth(articleInfo.getArticleAuth());
            article.setCreateDate(articleInfo.getCreateDate());
            article.setLikeNum(articleInfo.getLikeNum());
            article.setLabelValue(labelInfo.getLabelValue());
            UserSearch user=userSearchDao.selectUserSearchByUserId(articleInfo.getUserId());
            article.setUserId(user.getUserId());
            article.setUserNickname(user.getUserNickname());
            article.setUserViaSrc(user.getUserViaSrc());
            articles.add(article);
        }
        QueryPageMap<ArticleDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(articles);
        return queryPageMap;
    }

    @Override
    public QueryPageMap<ArticleDTO> queryArticleByUserIdAndAuth(Integer userId, Integer auth, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArticleInfo> articleInfos=articleInfoDao.selectArticleByUserIdAndAuth(userId,auth);
        PageInfo pageInfo=new PageInfo(articleInfos);
        if (articleInfos.size()<=0){
            return null;
        }
        UserSearch user=userSearchDao.selectUserSearchByUserId(userId);
        List<ArticleDTO> articles=new ArrayList<>();
        for (ArticleInfo articleInfo:articleInfos){
            ArticleDTO article=new ArticleDTO();
            article.setArticleId(articleInfo.getArticleId());
            article.setTitle(articleInfo.getTitle());
            article.setContent(articleInfo.getContent());
            article.setArticleAuth(articleInfo.getArticleAuth());
            article.setCreateDate(articleInfo.getCreateDate());
            article.setLikeNum(articleInfo.getLikeNum());
            LabelInfo labelInfo=labelInfoDao.selectLabelByLabelId(articleInfo.getLabelId());
            article.setLabelValue(labelInfo.getLabelValue());
            article.setUserId(user.getUserId());
            article.setUserNickname(user.getUserNickname());
            article.setUserViaSrc(user.getUserViaSrc());
            articles.add(article);
        }
        QueryPageMap<ArticleDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(articles);
        return queryPageMap;
    }

    @Override
    public QueryPageMap<ArticleDTO> queryArticleByUserIdAndStatus(Integer userId, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArticleInfo> articleInfos=articleInfoDao.selectArticleByUserIdAndStatus(userId,status);
        PageInfo pageInfo=new PageInfo(articleInfos);
        if (articleInfos.size()<=0){
            return null;
        }
        UserSearch user=userSearchDao.selectUserSearchByUserId(userId);
        List<ArticleDTO> articles=new ArrayList<>();
        for (ArticleInfo articleInfo:articleInfos){
            ArticleDTO article=new ArticleDTO();
            article.setArticleId(articleInfo.getArticleId());
            article.setTitle(articleInfo.getTitle());
            article.setContent(articleInfo.getContent());
            article.setArticleAuth(articleInfo.getArticleAuth());
            article.setCreateDate(articleInfo.getCreateDate());
            article.setLikeNum(articleInfo.getLikeNum());
            LabelInfo labelInfo=labelInfoDao.selectLabelByLabelId(articleInfo.getLabelId());
            article.setLabelValue(labelInfo.getLabelValue());
            article.setUserId(user.getUserId());
            article.setUserNickname(user.getUserNickname());
            article.setUserViaSrc(user.getUserViaSrc());
            articles.add(article);
        }
        QueryPageMap<ArticleDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(articles);
        return queryPageMap;
    }

    @Override
    public QueryPageMap<ArticleDTO> queryArticleByUserIdAndAuthAndStatus(Integer userId, Integer auth, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArticleInfo> articleInfos=articleInfoDao.selectArticleByUserIdAndAuthAndStatus(userId,auth,status);
        PageInfo pageInfo=new PageInfo(articleInfos);
        if (articleInfos.size()<=0){
            return null;
        }
        UserSearch user=userSearchDao.selectUserSearchByUserId(userId);
        List<ArticleDTO> articles=new ArrayList<>();
        for (ArticleInfo articleInfo:articleInfos){
            ArticleDTO article=new ArticleDTO();
            article.setArticleId(articleInfo.getArticleId());
            article.setTitle(articleInfo.getTitle());
            article.setContent(articleInfo.getContent());
            article.setArticleAuth(articleInfo.getArticleAuth());
            article.setCreateDate(articleInfo.getCreateDate());
            article.setLikeNum(articleInfo.getLikeNum());
            LabelInfo labelInfo=labelInfoDao.selectLabelByLabelId(articleInfo.getLabelId());
            article.setLabelValue(labelInfo.getLabelValue());
            article.setUserId(user.getUserId());
            article.setUserNickname(user.getUserNickname());
            article.setUserViaSrc(user.getUserViaSrc());
            articles.add(article);
        }
        QueryPageMap<ArticleDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(articles);
        return queryPageMap;
    }

    @Override
    public QueryResult queryArticleUAS(Integer articleId) {
        return articleInfoDao.selectArticleUAS(articleId);
    }

    @Override
    public ArticleInfo addNewArticle(ArticleInfo articleInfo) {
        if (articleInfo==null){
            return null;
        }
        try{
            int count=articleInfoDao.insertArticleInfo(articleInfo);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 添加文章失败！ 用户ID："+articleInfo.getUserId());
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return null;
        }
        return articleInfo;
    }

    @Override
    public boolean modifyArticleStatus(Integer articleId, Integer status) {
        try{
            int count=articleInfoDao.updateArticleStatus(articleId,status);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改文章状态失败！ 文章ID："+articleId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyArticleAuth(Integer articleId, Integer auth) {
        try{
            int count=articleInfoDao.updateArticleAuth(articleId,auth);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改文章权限失败！ 文章ID："+articleId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean increaseArticleLikeNum(Integer articleId) {
        try{
            int count=articleInfoDao.increaseArticleLikeNum(articleId);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 增加文章点赞数失败！ 文章ID："+articleId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public ArticleImg queryArticleImgByImgId(Integer imgId) {
        return articleImgDao.selectArticleImgByImgId(imgId);
    }

    @Override
    public List<ArticleImg> queryArticleImgByArticleId(Integer articleId) {
        List<ArticleImg> articleImgs=articleImgDao.selectArticleImgByArticleId(articleId);
        if (articleImgs.size()<=0){
            return null;
        }
        return articleImgs;
    }

    @Override
    public boolean addNewArticleImg(ArticleImg articleImg) {
        if (articleImg==null){
            return false;
        }
        try{
            int count=articleImgDao.insertArticleImg(articleImg);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 添加文章图片失败！ 文章ID："+articleImg.getArticleId());
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyArticleImgStatus(Integer articleId, Integer status) {
        try{
            int count=articleImgDao.updateArticleImgStatus(articleId,status);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改文章图片状态失败！ 文章ID："+articleId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }
}
