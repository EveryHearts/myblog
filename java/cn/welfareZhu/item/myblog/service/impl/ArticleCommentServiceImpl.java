package cn.welfareZhu.item.myblog.service.impl;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.dao.ArticleCommentDao;
import cn.welfareZhu.item.myblog.dao.UserSearchDao;
import cn.welfareZhu.item.myblog.dto.ArticleCommentDTO;
import cn.welfareZhu.item.myblog.model.ArticleComment;
import cn.welfareZhu.item.myblog.model.UserSearch;
import cn.welfareZhu.item.myblog.service.ArticleCommentService;
import cn.welfareZhu.item.myblog.util.QueryPageMap;
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
 * @name 文章评论Service
 * @date 2019-09-03
 * **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class ArticleCommentServiceImpl implements ArticleCommentService {
    @Resource
    private UserSearchDao userSearchDao;
    @Resource
    private ArticleCommentDao articleCommentDao;

    @Override
    public ArticleCommentDTO queryArticleCommentByCommentId(Integer commentId) {
        ArticleComment articleComment=articleCommentDao.selectArticleCommentByCommentId(commentId);
        if (articleComment==null){
            return null;
        }
        ArticleCommentDTO comment=new ArticleCommentDTO();
        comment.setArticleCommentId(articleComment.getArticleCommentId());
        comment.setArticleId(articleComment.getArticleId());
        comment.setContent(articleComment.getContent());
        comment.setCommentType(articleComment.getCommentType());
        comment.setCreateDate(articleComment.getCreateDate());
        UserSearch user=userSearchDao.selectUserSearchByUserId(articleComment.getUserId());
        comment.setUserId(user.getUserId());
        comment.setUserNickname(user.getUserNickname());
        comment.setUserViaSrc(user.getUserViaSrc());
        if (articleComment.getCommentType()==COMMENT){
            return comment;
        }
        UserSearch client=userSearchDao.selectUserSearchByUserId(articleComment.getClientId());
        comment.setClientId(client.getUserId());
        comment.setClientNickname(client.getUserNickname());
        comment.setUserViaSrc(client.getUserViaSrc());
        return comment;
    }

    @Override
    public QueryPageMap<ArticleCommentDTO> queryArticleCommentByArticleIdAndStatusType(Integer articleId, Integer status, Integer type, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArticleComment> articleComments=articleCommentDao.selectCommentByArticleIdAndStatusType(articleId,status,type);
        PageInfo pageInfo=new PageInfo(articleComments);
        if (articleComments.size()<=0){
            return null;
        }
        List<ArticleCommentDTO> comments=new ArrayList<>();
        for (ArticleComment articleComment:articleComments){
            ArticleCommentDTO comment=new ArticleCommentDTO();
            comment.setArticleCommentId(articleComment.getArticleCommentId());
            comment.setArticleId(articleComment.getArticleId());
            comment.setContent(articleComment.getContent());
            comment.setCommentType(articleComment.getCommentType());
            comment.setCreateDate(articleComment.getCreateDate());
            UserSearch user=userSearchDao.selectUserSearchByUserId(articleComment.getUserId());
            comment.setUserId(user.getUserId());
            comment.setUserNickname(user.getUserNickname());
            comment.setUserViaSrc(user.getUserViaSrc());
            if (articleComment.getCommentType()==COMMENT){
                comments.add(comment);
                continue;
            }
            UserSearch client=userSearchDao.selectUserSearchByUserId(articleComment.getClientId());
            comment.setClientId(client.getUserId());
            comment.setClientNickname(client.getUserNickname());
            comment.setUserViaSrc(client.getUserViaSrc());
            comments.add(comment);
        }
        QueryPageMap<ArticleCommentDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(comments);
        return queryPageMap;
    }

    @Override
    public ArticleComment addNewArticleComment(ArticleComment articleComment) {
        if (articleComment==null){
            return null;
        }
        try{
            int count=articleCommentDao.insertArticleComment(articleComment);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 添加新的文章评论失败！ 文章ID："+articleComment.getArticleId()+" 内容："+articleComment.getContent());
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return null;
        }
        return articleComment;
    }

    @Override
    public boolean modifyCommentStatus(Integer commentId, Integer status) {
        try{
            int count=articleCommentDao.updateCommentStatus(commentId,status);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改文章评论状态失败！ 评论ID："+commentId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }
}
