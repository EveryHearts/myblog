package cn.welfareZhu.item.myblog.mq;

import cn.welfareZhu.item.myblog.vo.LinkLikeMoodVO;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author 朱剑飞
 * @name 链接点赞生产者
 * @date 2019-10-10
 * **/
@Component
public class LinkLikeKeyProducer {

    @Resource
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, final LinkLikeMoodVO linkLikeMoodVO){
        jmsTemplate.convertAndSend(destination,linkLikeMoodVO);
    }
}
