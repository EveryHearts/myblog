package cn.welfareZhu.item.myblog.mq;

import cn.welfareZhu.item.myblog.vo.ArticleLikeMoodVO;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author 朱剑飞
 * @name 文章点赞生产者
 * @date 2019-10-05
 * **/
@Component
public class ArticleLikeKeyProducer {
    @Resource
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, final ArticleLikeMoodVO articleLikeMoodVO){
        jmsTemplate.convertAndSend(destination,articleLikeMoodVO);
    }
}
