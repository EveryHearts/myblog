package cn.welfareZhu.item.myblog.service;

import cn.welfareZhu.item.myblog.dto.NotifyDTO;
import cn.welfareZhu.item.myblog.model.NotifyInfo;
import cn.welfareZhu.item.myblog.util.QueryPageMap;

public interface NotifyService {
    //按照通知ID查询通知
    NotifyDTO queryNotifyByNotifyId(Integer notifyId);
    //按照用户ID和通知状态查询通知
    QueryPageMap<NotifyDTO> queryNotifyByUserIdAndStatus(Integer userId,Integer status,int pageNum,int pageSize);
    //按照用户ID和通知状态查询通知数量
    int queryCountForNotifyByUserId(Integer userId,Integer status);
    //按照通知类型、权限、状态查询通知
    QueryPageMap<NotifyDTO> queryNotifyByTAS(Integer type,Integer auth,Integer status,int pageNum,int pageSize);
    //按照通知类型、权限、状态查询通知数量
    int queryCountForNotifyByTAS(Integer type,Integer auth,Integer status);
    //按照通知权限、状态查询通知数量
    int queryCountForNotifyByAuthAndStatus(Integer auth,Integer status);
    //新建通知
    boolean addNewNotify(NotifyInfo notifyInfo);
    //按照通知ID修改通知状态
    boolean modifyNotifyStatus(Integer notifyId,Integer status);
}
