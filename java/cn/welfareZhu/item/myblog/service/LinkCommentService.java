package cn.welfareZhu.item.myblog.service;

import cn.welfareZhu.item.myblog.dto.LinkCommentDTO;
import cn.welfareZhu.item.myblog.model.LinkComment;
import cn.welfareZhu.item.myblog.util.QueryPageMap;

public interface LinkCommentService {
    //按照链接评论ID查询
    LinkCommentDTO queryLinkCommentByCommentId(Integer commentId);
    //按照链接ID以及评论的状态、类型进行查询
    QueryPageMap<LinkCommentDTO> queryCommentBuLinkIdAndST(Integer linkId,Integer status,Integer type,int pageNum,int pageSize);
    //添加新的链接评论
    LinkComment addNewLinkComment(LinkComment linkComment);
    //按照评论ID修改评论状态
    boolean modifyCommentStatus(Integer commentId,Integer status);
}
