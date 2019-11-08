package cn.welfareZhu.item.myblog.mq;

import cn.welfareZhu.item.myblog.service.LinkService;
import cn.welfareZhu.item.myblog.vo.LinkLikeMoodVO;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author 朱剑飞
 * @name 链接点赞消费者
 * @date 2019-10-10
 * **/
@Component
public class LinkLikeKeyConsumer implements MessageListener {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private LinkService linkService;

    @Override
    public void onMessage(Message message) {
        try{
            LinkLikeMoodVO linkLikeMoodVO=(LinkLikeMoodVO)((ActiveMQObjectMessage)message).getObject();
            redisTemplate.opsForSet().add(linkLikeMoodVO.getLikeKey(),linkLikeMoodVO.getLikeUserId());
            linkService.increaseLinkLikeNum(linkLikeMoodVO.getLikeKeyId());
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
