package cn.welfareZhu.item.myblog.service.impl;

import cn.welfareZhu.item.myblog.dao.MessageDao;
import cn.welfareZhu.item.myblog.dao.UserSearchDao;
import cn.welfareZhu.item.myblog.dto.MessageDTO;
import cn.welfareZhu.item.myblog.model.Message;
import cn.welfareZhu.item.myblog.model.UserSearch;
import cn.welfareZhu.item.myblog.service.MessageService;
import cn.welfareZhu.item.myblog.util.QueryPageMap;
import cn.welfareZhu.item.myblog.util.QuerySQLException;
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
 * @name 留言Service
 * @date 2019-08-28
 * **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class MessageServiceImpl implements MessageService {

    @Resource
    private UserSearchDao userSearchDao;
    @Resource
    private MessageDao messageDao;

    @Override
    public QueryPageMap<MessageDTO> queryMessageByUserId(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Message> messageInfos=messageDao.selectMessageByUserId(userId);
        PageInfo pageInfo=new PageInfo(messageInfos);
        if (messageInfos.size()<=0){
            return null;
        }
        List<MessageDTO> messages=new ArrayList<>();
        for (Message messageInfo:messageInfos){
            MessageDTO message=new MessageDTO();
            message.setType(messageInfo.getMessageType());
            message.setMessageId(messageInfo.getMessageId());
            message.setContent(messageInfo.getContent());
            message.setCreateDate(messageInfo.getCreateDate());
            UserSearch user=userSearchDao.selectUserSearchByUserId(messageInfo.getUserId());
            message.setUserId(user.getUserId());
            message.setUserNickname(user.getUserNickname());
            message.setUserViaSrc(user.getUserViaSrc());
            UserSearch visitor=userSearchDao.selectUserSearchByUserId(messageInfo.getVisitorId());
            message.setVisitorId(visitor.getUserId());
            message.setVisitorNickname(visitor.getUserNickname());
            message.setVisitorViaSrc(visitor.getUserViaSrc());
            messages.add(message);
        }
        QueryPageMap<MessageDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(messages);
        return queryPageMap;
    }

    @Override
    public MessageDTO queryMessageByMessageId(Integer messageId) {
        Message messageInfo=messageDao.selectMessageByMessageId(messageId);
        if (messageInfo==null){
            return null;
        }
        MessageDTO message=new MessageDTO();
        message.setMessageId(messageInfo.getMessageId());
        message.setType(messageInfo.getMessageType());
        message.setContent(messageInfo.getContent());
        message.setCreateDate(messageInfo.getCreateDate());
        UserSearch user=userSearchDao.selectUserSearchByUserId(messageInfo.getUserId());
        message.setUserId(user.getUserId());
        message.setUserNickname(user.getUserNickname());
        message.setUserViaSrc(user.getUserViaSrc());
        UserSearch visitor=userSearchDao.selectUserSearchByUserId(messageInfo.getVisitorId());
        message.setVisitorId(visitor.getUserId());
        message.setVisitorNickname(visitor.getUserNickname());
        message.setVisitorViaSrc(visitor.getUserViaSrc());
        return message;
    }

    @Override
    public Message addNewMessage(Message message) {
        try{
            int count=messageDao.insertMessage(message);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 添加留言失败！用户ID："+message.getUserId());
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return null;
        }
        return message;
    }

    @Override
    public boolean modifyMessageStatus(Integer messageId,Integer status) {
        try{
            int count=messageDao.updateMessageStatus(messageId,status);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改留言ID："+messageId+" 状态失败！");
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }
}
