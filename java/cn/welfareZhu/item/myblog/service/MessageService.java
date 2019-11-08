package cn.welfareZhu.item.myblog.service;

import cn.welfareZhu.item.myblog.dto.MessageDTO;
import cn.welfareZhu.item.myblog.model.Message;
import cn.welfareZhu.item.myblog.util.QueryPageMap;

public interface MessageService {
    //按照用户ID查询用户留言
    QueryPageMap<MessageDTO> queryMessageByUserId(Integer userId,int pageNum,int pageSize);
    //按照留言ID查询留言
    MessageDTO queryMessageByMessageId(Integer messageId);
    //新建留言
    Message addNewMessage(Message message);
    //按照留言ID修改留言状态
    boolean modifyMessageStatus(Integer messageId,Integer status);
}
