package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.FileComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱剑飞
 * @name 文件评论DAO
 * @date 2019-8-13
 * **/
@Repository
public interface FileCommentDao {
    //按照评论ID查询
    FileComment selectFileCommentByCommentId(Integer commentId);
    //按照文件ID、评论状态类型查询
    List<FileComment> selectCommentByFileIdAndST(@Param("fileId") Integer fileId,@Param("status") Integer status,@Param("type") Integer type);
    //插入新的文件评论
    int insertFileComment(FileComment fileComment);
    //按照评论ID更新评论状态
    int updateCommentStatus(@Param("commentId") Integer commentId,@Param("status") Integer status);
}
