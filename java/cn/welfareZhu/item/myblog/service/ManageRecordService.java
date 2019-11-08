package cn.welfareZhu.item.myblog.service;

import cn.welfareZhu.item.myblog.dto.ManageRecordDTO;
import cn.welfareZhu.item.myblog.model.ManageRecord;
import cn.welfareZhu.item.myblog.util.QueryPageMap;

public interface ManageRecordService {
    //按照记录ID查询
    ManageRecordDTO queryManageRecordByRecordId(Integer recordId);
    //按照记录状态查询
    QueryPageMap<ManageRecordDTO> queryManageRecordByRecordStatus(Integer recordStatus,int pageNum,int pageSize);
    //添加新的记录
    boolean addNewManageRecord(ManageRecord  manageRecord);
    //按照记录ID修改记录状态
    boolean modifyRecordStatusByRecordId(Integer recordId,Integer status);
    //按照记录ID修改处理结果
    boolean modifyProcessResultByRecordId(Integer recordId,Integer processResult);
}
