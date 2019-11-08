package cn.welfareZhu.item.myblog.service;

import cn.welfareZhu.item.myblog.dto.UserSearchDTO;
import cn.welfareZhu.item.myblog.model.UserInfo;
import cn.welfareZhu.item.myblog.model.UserSearch;
import cn.welfareZhu.item.myblog.util.QueryPageMap;
import cn.welfareZhu.item.myblog.vo.UserRegister;

import java.util.List;

/**
 * @author 朱剑飞
 * @name User服务层接口
 * @date 2019-08-26
 * **/
public interface UserService {
    //按照用户ID查询用户状态
    int queryUserStatusByUserId(Integer userId);
    //按照用户ID查询用户权限
    int queryUserAuthByUserId(Integer userId);
    //按照用户ID查询用户登录信息
    UserSearchDTO queryUserByUserId(Integer userId);
    //按照用户账号密码查询用户登录信息
    UserSearchDTO queryUserByAccountAndPassword(String account,String password);
    //按照用户账号、密码和安全码确认身份信息是否正确
    boolean validUserIdentity(String account,String password,String securityCode);
    //按照用户ID修改用户密码
    boolean modifyUserPassword(Integer userId,String password);
    //按照用户ID修改用户权限
    boolean modifyUserAuth(Integer userId,Integer auth);
    //按照用户ID修改用户状态
    boolean modifyUserStatus(Integer userId,Integer status);
    //按照用户权限、状态查询用户登录信息
    QueryPageMap<UserSearchDTO> queryUserByAuthAndStatus(Integer auth, Integer status,int pageNum,int pageSize);
    //按照用户ID查询用户基本信息
    UserInfo queryUserInfoByUserId(Integer userId);
    //新建用户登录信息
    int registerUserAccount(UserRegister userRegister);
    //修改用户Search表
    boolean modifyUserSearch(UserSearch userSearch);
    //修改用户基本信息
    boolean modifyUserInfo(UserInfo userInfo);
    //查询输入账号是否存在
    boolean queryAccountIsExist(String account);
}
