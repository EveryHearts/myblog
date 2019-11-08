package cn.welfareZhu.item.myblog.service.impl;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.dao.FileCommentDao;
import cn.welfareZhu.item.myblog.dao.UserSearchDao;
import cn.welfareZhu.item.myblog.dto.FileCommentDTO;
import cn.welfareZhu.item.myblog.model.FileComment;
import cn.welfareZhu.item.myblog.model.UserSearch;
import cn.welfareZhu.item.myblog.service.FileCommentService;
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
 * @name 文件评论Service
 * @date 2019-09-02
 * **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class FileCommentServiceImpl implements FileCommentService {
    @Resource
    private UserSearchDao userSearchDao;
    @Resource
    private FileCommentDao fileCommentDao;

    @Override
    public FileCommentDTO queryFileCommentByCommentId(Integer commentId) {
        FileComment fileComment=fileCommentDao.selectFileCommentByCommentId(commentId);
        if (fileComment==null){
            return null;
        }
        FileCommentDTO comment=new FileCommentDTO();
        comment.setFileCommentId(commentId);
        comment.setFileId(fileComment.getFileId());
        comment.setContent(fileComment.getContent());
        comment.setCommentType(fileComment.getCommentType());
        comment.setCreateDate(fileComment.getCreateDate());
        UserSearch user=userSearchDao.selectUserSearchByUserId(fileComment.getUserId());
        comment.setUserId(fileComment.getUserId());
        comment.setUserNickname(user.getUserNickname());
        comment.setUserViaSrc(user.getUserViaSrc());
        if (fileComment.getCommentType()==COMMENT){
            return comment;
        }
        UserSearch client=userSearchDao.selectUserSearchByUserId(fileComment.getClientId());
        comment.setClientId(fileComment.getClientId());
        comment.setClientNickname(client.getUserNickname());
        comment.setClientViaSrc(client.getUserViaSrc());
        return comment;
    }

    @Override
    public QueryPageMap<FileCommentDTO> queryCommentByFileIdAndST(Integer fileId, Integer status, Integer type, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<FileComment> fileComments=fileCommentDao.selectCommentByFileIdAndST(fileId,status,type);
        PageInfo pageInfo=new PageInfo(fileComments);
        if (fileComments.size()<=0){
            return null;
        }
        List<FileCommentDTO> comments=new ArrayList<>();
        for (FileComment fileComment:fileComments){
            FileCommentDTO comment=new FileCommentDTO();
            comment.setFileCommentId(fileComment.getFileCommentId());
            comment.setFileId(fileComment.getFileId());
            comment.setContent(fileComment.getContent());
            comment.setCommentType(fileComment.getCommentType());
            comment.setCreateDate(fileComment.getCreateDate());
            UserSearch user=userSearchDao.selectUserSearchByUserId(fileComment.getUserId());
            comment.setUserId(fileComment.getUserId());
            comment.setUserNickname(user.getUserNickname());
            comment.setUserViaSrc(user.getUserViaSrc());
            if (fileComment.getCommentType()==COMMENT){
                comments.add(comment);
                continue;
            }
            UserSearch client=userSearchDao.selectUserSearchByUserId(fileComment.getClientId());
            comment.setClientId(fileComment.getClientId());
            comment.setClientNickname(client.getUserNickname());
            comment.setClientViaSrc(client.getUserViaSrc());
            comments.add(comment);
        }
        QueryPageMap<FileCommentDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(comments);
        return queryPageMap;
    }

    @Override
    public FileComment addNewFileComment(FileComment fileComment) {
        if (fileComment==null){
            return null;
        }
        try{
            int count=fileCommentDao.insertFileComment(fileComment);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 添加文件评论失败！ 用户ID："+fileComment.getUserId()+" 评论内容："+fileComment.getContent());
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return null;
        }
        return fileComment;
    }

    @Override
    public boolean modifyCommentStatus(Integer commentId, Integer status) {
        try{
            int count=fileCommentDao.updateCommentStatus(commentId,status);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改文件评论状态失败！ 评论ID："+commentId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }
}
