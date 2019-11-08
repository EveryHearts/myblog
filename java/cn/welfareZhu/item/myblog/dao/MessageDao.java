package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱剑飞
 * @name 用户留言DAO
 * @date 2019-8-13
 * **/
@Repository
public interface MessageDao {
    //按照用户ID查询用户留言
    List<Message> selectMessageByUserId(Integer userId);
    //按照留言ID查询留言
    Message selectMessageByMessageId(Integer messageId);
    //插入新的留言
    int insertMessage(Message message);
    //按照留言ID修改留言状态
    int updateMessageStatus(@Param("messageId") Integer messageId, @Param("messageStatus") Integer messageStatus);
}
