package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱剑飞
 * @name 用户检索DAO
 * @date 2019-8-13
 * **/
@Repository
public interface UserSearchDao {
    //按照用户ID查询用户
    UserSearch selectUserSearchByUserId(Integer userId);
    //按照用户昵称查询用户
    UserSearch selectUserSearchByNickname(String nickname);
    //按照用户IP地址查询
    UserSearch selectUserSearchByUserIp(String userIp);
    //按照用户权限、状态查询
    List<UserSearch> selectUserSearchByStatus(@Param("status") Integer status);
    //按照用户ID查询用户状态
    int selectUserStatusByUserId(Integer userId);

    //添加新用户检索记录
    int insertUserSearch(UserSearch userSearch);

    //更新用户检索记录
    int updateUserSearch(UserSearch userSearch);
}
