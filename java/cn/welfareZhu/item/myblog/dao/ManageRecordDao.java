package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.ManageRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱剑飞
 * @name 管理记录DAO
 * @date 2019-09-06
 * **/
@Repository
public interface ManageRecordDao {
    //按照记录ID查询
    ManageRecord selectManageRecordByRecordId(Integer recordId);
    //按照记录状态查询
    List<ManageRecord> selectManageRecordByRecordStatus(Integer recordStatus);
    //插入新的记录
    int insertManageRecord(ManageRecord manageRecord);
    //按照记录ID更新记录状态
    int updateRecordStatusByRecordId(@Param("recordId") Integer recordId,@Param("status") Integer status);
    //按照记录ID更新处理结果
    int updateProcessResultByRecordId(@Param("recordId") Integer recordId,@Param("processResult") Integer processResult);
}
