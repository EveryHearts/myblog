package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.UserLogin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱剑飞
 * @name 用户登录DAO
 * @date 2019-8-13
 * **/
@Repository
public interface UserLoginDao {
    //按照用户ID查询用户权限
    int selectUserAuthByUserId(Integer userId);
    //按照用户ID查询用户状态
    int selectUserStatusByUserId(Integer userId);
    //按照用户账号密码查询用户
    UserLogin selectUserLoginByAccountAndPassword(@Param("account") String account,@Param("password") String password);
    //按照用户ID查询用户
    UserLogin selectUserLoginByUserId(Integer userId);
    //按照用户权限查询用户ID
    List<Integer> selectUserIdByUserAuthAndStatus(@Param("auth") Integer auth,@Param("status") Integer status);
    //插入新的UserLogin
    int insertUserLogin(UserLogin userLogin);
    //更新UserLogin
    int updateUserLogin(UserLogin userLogin);
    //按照用户ID更新用户状态
    int updateUserStatus(@Param("userId") Integer userId,@Param("status") Integer status);
    //按照用户账号查询用户ID count
    int countUserByUserAccount(String account);
}
