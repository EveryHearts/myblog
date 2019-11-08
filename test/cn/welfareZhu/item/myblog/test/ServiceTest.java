package cn.welfareZhu.item.myblog.test;

import static cn.welfareZhu.item.myblog.util.SystemIdentifiers.*;

import cn.welfareZhu.item.myblog.dto.ArticleDTO;
import cn.welfareZhu.item.myblog.dto.UserSearchDTO;
import cn.welfareZhu.item.myblog.mq.ArticleLikeKeyProducer;
import cn.welfareZhu.item.myblog.service.*;
import cn.welfareZhu.item.myblog.util.QueryPageMap;
import cn.welfareZhu.item.myblog.vo.ArticleLikeMoodVO;
import cn.welfareZhu.item.myblog.vo.UserRegister;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.List;
import java.util.Set;

/**
 * @author 朱剑飞
 * @name Service测试类
 * @date 2019-08-26
 * **/
public class ServiceTest extends BaseJunit4Test{

    Destination destination=new ActiveMQQueue("cn.welfareZhu.item.myblog.articleLike");

    @Resource
    private ArticleLikeKeyProducer articleLikeKeyProducer;
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        ArticleLikeMoodVO articleLikeMoodVO=new ArticleLikeMoodVO();
        articleLikeMoodVO.setLikeKeyId(1);
        articleLikeMoodVO.setLikeUserId(1000);
        articleLikeKeyProducer.sendMessage(destination,articleLikeMoodVO);
        long testLong=0;
        double testDouble=Double.parseDouble(testLong+"");
        System.out.println(testDouble);
        System.out.println("The count is : "+redisTemplate.opsForZSet().size("TTTest"));
        long keySize=redisTemplate.opsForZSet().size("TTTest");
        System.out.println(keySize);
        double keyScore=Double.parseDouble(keySize+"");
        System.out.println(keyScore);
        double minKeyScore=keyScore-5.0;
        System.out.println(minKeyScore);
        Set<Integer> valueSet=redisTemplate.opsForZSet().reverseRangeByScore("TTTest",minKeyScore,keyScore);
        for (int value:valueSet){
            System.out.println(value);
        }
    }
}
