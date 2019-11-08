package cn.welfareZhu.item.myblog.service;

import cn.welfareZhu.item.myblog.dto.FileCommentDTO;
import cn.welfareZhu.item.myblog.model.FileComment;
import cn.welfareZhu.item.myblog.util.QueryPageMap;

public interface FileCommentService {
    //按照评论ID查询
    FileCommentDTO queryFileCommentByCommentId(Integer commentId);
    //按照文件ID以及评论状态和类型查询
    QueryPageMap<FileCommentDTO> queryCommentByFileIdAndST(Integer fileId,Integer status,Integer type,int pageNum,int pageSize);
    //添加新的文件评论
    FileComment addNewFileComment(FileComment fileComment);
    //按照评论ID修改评论状态
    boolean modifyCommentStatus(Integer commentId,Integer status);
}
