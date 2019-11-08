package cn.welfareZhu.item.myblog.service;

import cn.welfareZhu.item.myblog.dto.ArticleCommentDTO;
import cn.welfareZhu.item.myblog.model.ArticleComment;
import cn.welfareZhu.item.myblog.util.QueryPageMap;

public interface ArticleCommentService {
    //按照评论ID查询
    ArticleCommentDTO queryArticleCommentByCommentId(Integer commentId);
    //按照文章ID以及评论状态、类型查询
    QueryPageMap<ArticleCommentDTO> queryArticleCommentByArticleIdAndStatusType(Integer articleId,Integer status,Integer type,int pageNum,int pageSize);
    //添加新的文章评论
    ArticleComment addNewArticleComment(ArticleComment articleComment);
    //按照评论ID修改评论状态
    boolean modifyCommentStatus(Integer commentId,Integer status);
}
