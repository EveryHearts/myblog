package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.NotifyInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱剑飞
 * @name 通知信息DAO
 * @date 2019-8-13
 * **/
@Repository
public interface NotifyInfoDao {
    //按照通知ID查询
    NotifyInfo selectNotifyInfoByNotifyId(Integer notifyId);
    //按照用户ID以及通知状态查询
    List<NotifyInfo> selectNotifyInfoByUserId(@Param("userId") Integer userId, @Param("notifyStatus") Integer notifyStatus);
    //按照用户ID查询通知数量
    int countNotifyInfoByUserId(@Param("userId") Integer userId,@Param("notifyStatus") Integer notifyStatus);
    //按照通知类型、权限以及状态查询
    List<NotifyInfo> selectNotifyInfoByTAS(@Param("type") Integer type,@Param("auth") Integer auth,@Param("status") Integer status);
    //按照通知类型、权限以及状态查询通知数量
    int countNotifyInfoByTAS(@Param("type") Integer type,@Param("auth") Integer auth,@Param("status") Integer status);
    //按照通知权限、状态查询通知数量
    int countNotifyInfoByAuthAndStatus(@Param("auth") Integer auth,@Param("status") Integer status);
    //插入新的通知
    int insertNotifyInfo(NotifyInfo notifyInfo);
    //按照通知ID更新状态
    int updateNotifyStatusByNotifyId(@Param("notifyId") Integer notifyId,@Param("notifyStatus") Integer notifyStatus);
}
