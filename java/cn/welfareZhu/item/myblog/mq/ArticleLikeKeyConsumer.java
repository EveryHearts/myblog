package cn.welfareZhu.item.myblog.mq;

import cn.welfareZhu.item.myblog.service.ArticleService;
import cn.welfareZhu.item.myblog.vo.ArticleLikeMoodVO;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author 朱剑飞
 * @name 文章点赞消费者
 * @date 2019-10-05
 * **/
@Component
public class ArticleLikeKeyConsumer implements MessageListener {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private ArticleService articleService;

    @Override
    public void onMessage(Message message){
        try{
            ArticleLikeMoodVO articleLikeMoodVO=(ArticleLikeMoodVO)((ActiveMQObjectMessage)message).getObject();
            redisTemplate.opsForSet().add(articleLikeMoodVO.getLikeKey(),articleLikeMoodVO.getLikeUserId());
            articleService.increaseArticleLikeNum(articleLikeMoodVO.getLikeKeyId());
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
