package cn.welfareZhu.item.myblog.mq;

import cn.welfareZhu.item.myblog.model.FileByte;
import cn.welfareZhu.item.myblog.service.FileService;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author 朱剑飞
 * @name 文件流存储Consumer
 * @date 2019-10-14
 * **/
@Component
public class FileByteConsumer implements MessageListener {

    @Resource
    private FileService fileService;

    @Override
    public void onMessage(Message message) {
        try{
            FileByte fileByte=(FileByte)((ActiveMQObjectMessage)message).getObject();
            fileService.addNewFileByte(fileByte);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
