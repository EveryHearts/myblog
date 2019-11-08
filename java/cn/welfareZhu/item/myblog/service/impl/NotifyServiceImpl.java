package cn.welfareZhu.item.myblog.service.impl;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.dao.NotifyInfoDao;
import cn.welfareZhu.item.myblog.dao.UserSearchDao;
import cn.welfareZhu.item.myblog.dto.NotifyDTO;
import cn.welfareZhu.item.myblog.model.NotifyInfo;
import cn.welfareZhu.item.myblog.model.UserSearch;
import cn.welfareZhu.item.myblog.service.NotifyService;
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
 * @name 通知Service
 * @date 2019-08-27
 * **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class NotifyServiceImpl implements NotifyService {

    @Resource
    private UserSearchDao userSearchDao;
    @Resource
    private NotifyInfoDao notifyInfoDao;

    @Override
    public NotifyDTO queryNotifyByNotifyId(Integer notifyId) {
        NotifyInfo notifyInfo=notifyInfoDao.selectNotifyInfoByNotifyId(notifyId);
        if (notifyInfo==null){
            return null;
        }
        NotifyDTO notifyDTO=new NotifyDTO();
        notifyDTO.setNotifyId(notifyId);
        notifyDTO.setContent(notifyInfo.getContent());
        notifyDTO.setCreateDate(notifyInfo.getCreateDate());
        notifyDTO.setNotifyType(notifyInfo.getNotifyType());
        if (notifyInfo.getNotifyAuth()==AUTH_PUBLIC){
            return notifyDTO;
        }
        notifyDTO.setOriginUserId(notifyInfo.getOriginUserId());
        notifyDTO.setOriginId(notifyInfo.getOriginId());
        UserSearch userSearch=userSearchDao.selectUserSearchByUserId(notifyInfo.getOriginUserId());
        notifyDTO.setOriginUserNickname(userSearch.getUserNickname());
        notifyDTO.setOriginUserViaSrc(userSearch.getUserViaSrc());
        return notifyDTO;
    }

    @Override
    public QueryPageMap<NotifyDTO> queryNotifyByUserIdAndStatus(Integer userId, Integer status,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum ,pageSize);
        List<NotifyInfo> notifyInfos=notifyInfoDao.selectNotifyInfoByUserId(userId,status);
        PageInfo pageInfo=new PageInfo(notifyInfos);
        if (notifyInfos.size()<=0){
            return null;
        }
        List<NotifyDTO> notifies=new ArrayList<>();
        for (NotifyInfo notifyInfo:notifyInfos){
            NotifyDTO notify=new NotifyDTO();
            notify.setNotifyId(notifyInfo.getNotifyId());
            notify.setContent(notifyInfo.getContent());
            notify.setCreateDate(notifyInfo.getCreateDate());
            notify.setNotifyType(notifyInfo.getNotifyType());
            UserSearch userSearch=userSearchDao.selectUserSearchByUserId(notifyInfo.getOriginUserId());
            notify.setOriginUserId(notifyInfo.getOriginUserId());
            notify.setOriginId(notifyInfo.getOriginId());
            notify.setOriginUserNickname(userSearch.getUserNickname());
            notify.setOriginUserViaSrc(userSearch.getUserViaSrc());
            notifies.add(notify);
        }
        QueryPageMap<NotifyDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(notifies);
        return queryPageMap;
    }

    @Override
    public int queryCountForNotifyByUserId(Integer userId, Integer status) {
        return notifyInfoDao.countNotifyInfoByUserId(userId,status);
    }

    @Override
    public QueryPageMap<NotifyDTO> queryNotifyByTAS(Integer type, Integer auth, Integer status,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<NotifyInfo> notifyInfos=notifyInfoDao.selectNotifyInfoByTAS(type,auth,status);
        PageInfo pageInfo=new PageInfo(notifyInfos);
        if (notifyInfos.size()<=0){
            return null;
        }
        List<NotifyDTO> notifies=new ArrayList<>();
        for (NotifyInfo notifyInfo:notifyInfos){
            NotifyDTO notify = new NotifyDTO();
            notify.setNotifyId(notifyInfo.getNotifyId());
            notify.setContent(notifyInfo.getContent());
            notify.setCreateDate(notifyInfo.getCreateDate());
            notify.setNotifyType(notifyInfo.getNotifyType());
            if (notifyInfo.getNotifyAuth()==AUTH_PUBLIC){
                continue;
            }
            notify.setOriginUserId(notifyInfo.getOriginUserId());
            notify.setOriginId(notifyInfo.getOriginId());
            UserSearch userSearch=userSearchDao.selectUserSearchByUserId(notifyInfo.getOriginUserId());
            notify.setOriginUserNickname(userSearch.getUserNickname());
            notify.setOriginUserViaSrc(userSearch.getUserViaSrc());
            notifies.add(notify);
        }
        QueryPageMap<NotifyDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(notifies);
        return queryPageMap;
    }

    @Override
    public int queryCountForNotifyByTAS(Integer type, Integer auth, Integer status) {
        return notifyInfoDao.countNotifyInfoByTAS(type,auth,status);
    }

    @Override
    public boolean addNewNotify(NotifyInfo notifyInfo) {
        try {
            int count = notifyInfoDao.insertNotifyInfo(notifyInfo);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 新建通知失败 notifyContent ："+notifyInfo.getContent()+" userId："+notifyInfo.getUserId());
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyNotifyStatus(Integer notifyId, Integer status) {
        if (notifyInfoDao.selectNotifyInfoByNotifyId(notifyId)==null){
            return false;
        }
        try{
            int count=notifyInfoDao.updateNotifyStatusByNotifyId(notifyId,status);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改通知ID："+notifyId+" 状态失败！");
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public int queryCountForNotifyByAuthAndStatus(Integer auth, Integer status) {
        return notifyInfoDao.countNotifyInfoByAuthAndStatus(auth,status);
    }
}
