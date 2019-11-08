package cn.welfareZhu.item.myblog.service;

import cn.welfareZhu.item.myblog.dto.LinkDTO;
import cn.welfareZhu.item.myblog.model.LinkInfo;
import cn.welfareZhu.item.myblog.util.QueryPageMap;

public interface LinkService {
    //按照链接ID查询
    LinkDTO queryLinkByLinkId(Integer linkId);
    //按照链接状态查询
    QueryPageMap<LinkDTO> queryLinkByStatus(Integer status,int pageNum,int pageSize);
    //按照链接状态统计链接数量
    int queryCountForLinkByStatus(Integer status);
    //按照链接状态和标签查询
    QueryPageMap<LinkDTO> queryLinkByStatusAndLabelId(Integer status,Integer labelId,int pageNum,int pageSize);
    //按照链接状态和标签统计链接数量
    int queryCountForLinkByStatusAndLabelId(Integer status,Integer labelId);
    //按照用户ID和链接状态查询
    QueryPageMap<LinkDTO> queryLinkByUserIdAndStatus(Integer userId,Integer status,int pageNum,int pageSize);
    //按照用户ID和链接状态统计链接数量
    int queryCountForLinkByUserIdAndStatus(Integer userId,Integer status);
    //新建新的链接信息
    LinkInfo addNewLink(LinkInfo linkInfo);
    //按照链接ID修改链接状态
    boolean modifyLinkStatus(Integer linkId,Integer status);
    //按照链接ID增加点赞数
    boolean increaseLinkLikeNum(Integer linkId);
}
