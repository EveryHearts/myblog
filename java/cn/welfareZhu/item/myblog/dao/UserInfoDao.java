package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author 朱剑飞
 * @name 用户信息DAO
 * @date 2019-8-13
 * **/
@Repository
public interface UserInfoDao {
    //按照用户ID查询
    UserInfo selectUserInfoByUserId(Integer userId);
    //插入新的UserInfo
    int insertUserInfo(UserInfo userInfo);
    //更新UserInfo
    int updateUserInfo(UserInfo userInfo);
}
