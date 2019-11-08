package cn.welfareZhu.item.myblog.service.impl;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.dao.LinkCommentDao;
import cn.welfareZhu.item.myblog.dao.UserSearchDao;
import cn.welfareZhu.item.myblog.dto.LinkCommentDTO;
import cn.welfareZhu.item.myblog.model.LinkComment;
import cn.welfareZhu.item.myblog.model.UserSearch;
import cn.welfareZhu.item.myblog.service.LinkCommentService;
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
 * @name 链接评论Service
 * @date 2019-09-02
 * **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class LinkCommentServiceImpl implements LinkCommentService {
    @Resource
    private LinkCommentDao linkCommentDao;
    @Resource
    private UserSearchDao userSearchDao;

    @Override
    public LinkCommentDTO queryLinkCommentByCommentId(Integer commentId) {
        LinkComment linkComment=linkCommentDao.selectCommentByCommentId(commentId);
        if (linkComment==null){
            return null;
        }
        LinkCommentDTO comment = new LinkCommentDTO();
        comment.setLinkId(linkComment.getLinkId());
        comment.setLinkCommentId(linkComment.getLinkCommentId());
        comment.setContent(linkComment.getContent());
        comment.setCommentType(linkComment.getCommentType());
        comment.setCreateDate(linkComment.getCreateDate());
        UserSearch user=userSearchDao.selectUserSearchByUserId(linkComment.getUserId());
        comment.setUserId(linkComment.getUserId());
        comment.setUserNickname(user.getUserNickname());
        comment.setUserViaSrc(user.getUserViaSrc());
        if (linkComment.getCommentType()==COMMENT){
            return comment;
        }
        UserSearch client=userSearchDao.selectUserSearchByUserId(linkComment.getClientId());
        comment.setClientId(linkComment.getClientId());
        comment.setClientNickname(client.getUserNickname());
        comment.setClientViaSrc(client.getUserViaSrc());
        return comment;
    }

    @Override
    public QueryPageMap<LinkCommentDTO> queryCommentBuLinkIdAndST(Integer linkId, Integer status, Integer type,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<LinkComment> linkComments=linkCommentDao.selectCommentByLinkIdAndST(linkId,status,type);
        PageInfo pageInfo=new PageInfo(linkComments);
        if (linkComments.size()<=0){
            return null;
        }
        List<LinkCommentDTO> comments=new ArrayList<>();
        for (LinkComment linkComment:linkComments){
            LinkCommentDTO comment = new LinkCommentDTO();
            comment.setLinkId(linkComment.getLinkId());
            comment.setLinkCommentId(linkComment.getLinkCommentId());
            comment.setContent(linkComment.getContent());
            comment.setCommentType(linkComment.getCommentType());
            comment.setCreateDate(linkComment.getCreateDate());
            UserSearch user=userSearchDao.selectUserSearchByUserId(linkComment.getUserId());
            comment.setUserId(linkComment.getUserId());
            comment.setUserNickname(user.getUserNickname());
            comment.setUserViaSrc(user.getUserViaSrc());
            if (linkComment.getCommentType()==COMMENT){
                comments.add(comment);
                continue;
            }
            UserSearch client=userSearchDao.selectUserSearchByUserId(linkComment.getClientId());
            comment.setClientId(linkComment.getClientId());
            comment.setClientNickname(client.getUserNickname());
            comment.setClientViaSrc(client.getUserViaSrc());
            comments.add(comment);
        }
        QueryPageMap<LinkCommentDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(comments);
        return queryPageMap;
    }

    @Override
    public LinkComment addNewLinkComment(LinkComment linkComment) {
        if (linkComment==null){
            return null;
        }
        try{
            int count=linkCommentDao.insertLinkComment(linkComment);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 添加链接评论失败！ 用户ID："+linkComment.getUserId()+" 评论内容："+linkComment.getContent());
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return null;
        }
        return linkComment;
    }

    @Override
    public boolean modifyCommentStatus(Integer commentId, Integer status) {
        try{
            int count=linkCommentDao.updateCommentStatus(commentId,status);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改链接评论状态失败！ 评论ID："+commentId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }
}
