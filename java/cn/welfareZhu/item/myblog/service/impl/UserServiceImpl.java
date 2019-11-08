package cn.welfareZhu.item.myblog.service.impl;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.dao.UserInfoDao;
import cn.welfareZhu.item.myblog.dao.UserLoginDao;
import cn.welfareZhu.item.myblog.dao.UserSearchDao;
import cn.welfareZhu.item.myblog.dto.UserSearchDTO;
import cn.welfareZhu.item.myblog.model.UserInfo;
import cn.welfareZhu.item.myblog.model.UserLogin;
import cn.welfareZhu.item.myblog.model.UserSearch;
import cn.welfareZhu.item.myblog.service.UserService;
import cn.welfareZhu.item.myblog.util.QueryPageMap;
import cn.welfareZhu.item.myblog.util.QuerySQLException;
import cn.welfareZhu.item.myblog.vo.UserRegister;
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
 * @name 用户Service
 * @date 2019-08-26
 * **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl implements UserService {
    @Resource
    private UserSearchDao userSearchDao;
    @Resource
    private UserLoginDao userLoginDao;
    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public int queryUserStatusByUserId(Integer userId) {
        return userLoginDao.selectUserStatusByUserId(userId);
    }

    @Override
    public int queryUserAuthByUserId(Integer userId) {
        return userLoginDao.selectUserAuthByUserId(userId);
    }

    @Override
    public UserSearchDTO queryUserByUserId(Integer userId) {
        UserSearch userSearch=userSearchDao.selectUserSearchByUserId(userId);
        if(userSearch==null){
            return null;
        }
        UserSearchDTO user=new UserSearchDTO();
        user.setUserId(userSearch.getUserId());
        user.setUserNickname(userSearch.getUserNickname());
        user.setViaSrc(userSearch.getUserViaSrc());
        user.setIsTourist(userSearch.getIsTourist());
        user.setUserStatus(userSearch.getUserStatus());
        user.setUserAuth(userLoginDao.selectUserAuthByUserId(userSearch.getUserId()));
        user.setCreateDate(userSearch.getCreateDate());
        return user;
    }

    @Override
    public UserSearchDTO queryUserByAccountAndPassword(String account, String password) {
        UserLogin userLogin=userLoginDao.selectUserLoginByAccountAndPassword(account,password);
        if(userLogin==null){
            return null;
        }
        UserSearch userSearch=userSearchDao.selectUserSearchByUserId(userLogin.getUserId());
        UserSearchDTO user=new UserSearchDTO();
        user.setUserId(userSearch.getUserId());
        user.setUserNickname(userSearch.getUserNickname());
        user.setViaSrc(userSearch.getUserViaSrc());
        user.setIsTourist(userSearch.getIsTourist());
        user.setUserStatus(userSearch.getUserStatus());
        user.setUserAuth(userLogin.getUserAuth());
        user.setCreateDate(userSearch.getCreateDate());
        return user;
    }

    @Override
    public boolean validUserIdentity(String account, String password, String securityCode) {
        UserLogin userLogin=userLoginDao.selectUserLoginByAccountAndPassword(account,password);
        if(userLogin==null){
            return false;
        }
        if(!userLogin.getUserSecurityCode().equals(securityCode)){
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyUserPassword(Integer userId, String password) {
        UserLogin userLogin=userLoginDao.selectUserLoginByUserId(userId);
        if(userLogin==null){
            return false;
        }
        userLogin.setUserPassword(password);
        try {
            int count = userLoginDao.updateUserLogin(userLogin);
            if (count != 1) {
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改用户ID："+userId+" 的密码时失败！");
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyUserAuth(Integer userId, Integer auth) {
        UserLogin userLogin=userLoginDao.selectUserLoginByUserId(userId);
        if(userLogin==null){
            return false;
        }
        userLogin.setUserAuth(auth);
        try {
            int count = userLoginDao.updateUserLogin(userLogin);
            if (count != 1) {
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改用户ID："+userId+" 权限失败！");
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyUserStatus(Integer userId, Integer status) {
        UserSearch userSearch=userSearchDao.selectUserSearchByUserId(userId);
        if(userSearch==null){
            return false;
        }
        userSearch.setUserStatus(status);
        try {
            int count = userSearchDao.updateUserSearch(userSearch);
            if (count != 1) {
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改用户ID："+userId+" 状态失败！");
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        userLoginDao.updateUserStatus(userId,status);
        return true;
    }

    @Override
    public QueryPageMap<UserSearchDTO> queryUserByAuthAndStatus(Integer auth, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Integer> userIds=userLoginDao.selectUserIdByUserAuthAndStatus(auth,status);
        PageInfo pageInfo=new PageInfo(userIds);
        if(userIds.size()<=0){
            return null;
        }
        List<UserSearchDTO> users=new ArrayList<>();
        for (int userId:userIds){
            UserSearch userSearch=userSearchDao.selectUserSearchByUserId(userId);
            UserSearchDTO user=new UserSearchDTO();
            user.setUserId(userId);
            user.setUserNickname(userSearch.getUserNickname());
            user.setIsTourist(userSearch.getIsTourist());
            user.setViaSrc(userSearch.getUserViaSrc());
            user.setUserStatus(userSearch.getUserStatus());
            user.setUserAuth(userLoginDao.selectUserAuthByUserId(userId));
            user.setCreateDate(userSearch.getCreateDate());
            users.add(user);
        }
        QueryPageMap<UserSearchDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(users);
        return queryPageMap;
    }

    @Override
    public UserInfo queryUserInfoByUserId(Integer userId) {
        return userInfoDao.selectUserInfoByUserId(userId);
    }

    @Override
    public int registerUserAccount(UserRegister userRegister) {
        UserSearch userSearch=new UserSearch();
        userSearch.setUserNickname(userRegister.getUserNickname());
        userSearch.setUserStatus(NORMAL);
        userSearch.setIsTourist(NOT_TOURIST);
        userSearch.setUserIp(userRegister.getUserIp());
        userSearch.setCreateDate(new Date());
        userSearchDao.insertUserSearch(userSearch);
        int userId=userSearch.getUserId();
        UserLogin userLogin=new UserLogin();
        userLogin.setUserId(userId);
        userLogin.setUserAccount(userRegister.getAccount());
        userLogin.setUserPassword(userRegister.getPassword());
        userLogin.setUserSecurityCode(userRegister.getSecurityCode());
        userLogin.setUserStatus(TO_AUDIT);
        userLogin.setUserAuth(USER);
        userLogin.setCreateDate(new Date());
        userLoginDao.insertUserLogin(userLogin);
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setCreateDate(new Date());
        userInfoDao.insertUserInfo(userInfo);
        return userId;
    }

    @Override
    public boolean modifyUserSearch(UserSearch userSearch) {
        try {
            int count = userSearchDao.updateUserSearch(userSearch);
            if (count != 1) {
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改用户检索表信息失败！：");
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyUserInfo(UserInfo userInfo) {
        try {
            int count = userInfoDao.updateUserInfo(userInfo);
            if (count != 1) {
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改用户信息表失败！");
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean queryAccountIsExist(String account) {
        int count = userLoginDao.countUserByUserAccount(account);
        if (count == 0) {
            return false;
        }
        return true;
    }
}
