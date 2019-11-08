package cn.welfareZhu.item.myblog.test;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.dao.LabelInfoDao;
import cn.welfareZhu.item.myblog.dao.UserLoginDao;
import cn.welfareZhu.item.myblog.dao.UserSearchDao;
import cn.welfareZhu.item.myblog.model.LabelInfo;
import cn.welfareZhu.item.myblog.model.UserLogin;
import cn.welfareZhu.item.myblog.model.UserSearch;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author 朱剑飞
 * @name DAO测试类
 * @date 2019-8-13
 * **/
public class UserSearchDaoTest extends BaseJunit4Test{
    @Resource
    private UserSearchDao userSearchDao;
    @Resource
    private LabelInfoDao labelInfoDao;
    @Resource
    private UserLoginDao userLoginDao;
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void testSearchDao(){
        UserLogin user=new UserLogin();
        user.setUserId(1002);
        user.setUserAccount("testaccount01");
        user.setUserPassword("123456");
        user.setUserSecurityCode("123456");
        user.setCreateDate(new Date());
        userLoginDao.insertUserLogin(user);
    }
}
