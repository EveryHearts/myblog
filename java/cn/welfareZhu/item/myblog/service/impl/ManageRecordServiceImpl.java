package cn.welfareZhu.item.myblog.service.impl;

import cn.welfareZhu.item.myblog.dao.ManageRecordDao;
import cn.welfareZhu.item.myblog.dao.UserSearchDao;
import cn.welfareZhu.item.myblog.dto.ManageRecordDTO;
import cn.welfareZhu.item.myblog.model.ManageRecord;
import cn.welfareZhu.item.myblog.model.UserSearch;
import cn.welfareZhu.item.myblog.service.ManageRecordService;
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
 * @name 管理记录Service
 * @date 2019-09-06
 * **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class ManageRecordServiceImpl implements ManageRecordService {
    @Resource
    private ManageRecordDao manageRecordDao;
    @Resource
    private UserSearchDao userSearchDao;

    @Override
    public ManageRecordDTO queryManageRecordByRecordId(Integer recordId) {
        ManageRecord manageRecord=manageRecordDao.selectManageRecordByRecordId(recordId);
        if (manageRecord==null){
            return null;
        }
        ManageRecordDTO record=new ManageRecordDTO();
        record.setRecordId(manageRecord.getRecordId());
        record.setManagerId(manageRecord.getManagerId());
        UserSearch manager=userSearchDao.selectUserSearchByUserId(manageRecord.getManagerId());
        record.setManagerNickname(manager.getUserNickname());
        record.setManagerViaSrc(manager.getUserViaSrc());
        record.setRecordStatus(manageRecord.getRecordStatus());
        record.setProcessResult(manageRecord.getProcessResult());
        record.setOriginId(manageRecord.getOriginId());
        record.setOriginType(manageRecord.getOriginType());
        record.setOriginUserId(manageRecord.getOriginUserId());
        UserSearch user=userSearchDao.selectUserSearchByUserId(manageRecord.getOriginUserId());
        record.setOriginUserNickname(user.getUserNickname());
        record.setOriginUserViaSrc(user.getUserViaSrc());
        record.setContent(manageRecord.getContent());
        record.setCreateDate(manageRecord.getCreateDate());
        return record;
    }

    @Override
    public QueryPageMap<ManageRecordDTO> queryManageRecordByRecordStatus(Integer recordStatus,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ManageRecord> manageRecords=manageRecordDao.selectManageRecordByRecordStatus(recordStatus);
        PageInfo pageInfo=new PageInfo(manageRecords);
        if (manageRecords.size()<=0){
            return null;
        }
        List<ManageRecordDTO> records=new ArrayList<>();
        for (ManageRecord manageRecord:manageRecords){
            ManageRecordDTO record=new ManageRecordDTO();
            record.setRecordId(manageRecord.getRecordId());
            record.setManagerId(manageRecord.getManagerId());
            UserSearch manager=userSearchDao.selectUserSearchByUserId(manageRecord.getManagerId());
            record.setManagerNickname(manager.getUserNickname());
            record.setManagerViaSrc(manager.getUserViaSrc());
            record.setRecordStatus(manageRecord.getRecordStatus());
            record.setProcessResult(manageRecord.getProcessResult());
            record.setOriginId(manageRecord.getOriginId());
            record.setOriginType(manageRecord.getOriginType());
            record.setOriginUserId(manageRecord.getOriginUserId());
            UserSearch user=userSearchDao.selectUserSearchByUserId(manageRecord.getOriginUserId());
            record.setOriginUserNickname(user.getUserNickname());
            record.setOriginUserViaSrc(user.getUserViaSrc());
            record.setContent(manageRecord.getContent());
            record.setCreateDate(manageRecord.getCreateDate());
            records.add(record);
        }
        QueryPageMap<ManageRecordDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(records);
        return queryPageMap;
    }

    @Override
    public boolean addNewManageRecord(ManageRecord manageRecord) {
        if (manageRecord==null){
            return false;
        }
        try{
            int count=manageRecordDao.insertManageRecord(manageRecord);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 添加新的管理记录失败！ 管理员ID："+manageRecord.getManagerId());
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyRecordStatusByRecordId(Integer recordId, Integer status) {
        try{
            int count=manageRecordDao.updateRecordStatusByRecordId(recordId,status);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改管理记录状态失败！ 记录ID："+recordId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyProcessResultByRecordId(Integer recordId, Integer processResult) {
        try{
            int count=manageRecordDao.updateProcessResultByRecordId(recordId,processResult);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改管理记录处理结果失败！ 记录ID："+recordId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }
}
