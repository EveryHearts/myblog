package cn.welfareZhu.item.myblog.mq;

import cn.welfareZhu.item.myblog.model.FileByte;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author 朱剑飞
 * @name 文件字节流生产者
 * @date 2019-10-14
 * **/
@Component
public class FileByteProducer {

    @Resource
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, final FileByte fileByte){
        jmsTemplate.convertAndSend(destination,fileByte);
    }
}
