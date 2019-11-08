package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.LinkInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱剑飞
 * @name 链接信息DAO
 * @date 2019-8-13
 * **/
@Repository
public interface LinkInfoDao {
    //按照链接ID查询
    LinkInfo selectLinkInfoByLinkId(Integer linkId);
    //按照链接状态查询
    List<LinkInfo> selectLinkByStatus(Integer status);
    //按照链接状态统计链接数量
    int countLinkByStatus(Integer status);
    //按照链接状态和标签查询
    List<LinkInfo> selectLinkBySL(@Param("status") Integer status,@Param("labelId") Integer labelId);
    //按照链接状态和标签统计链接数量
    int countLinkBySL(@Param("status") Integer status,@Param("labelId") Integer labelId);
    //按照用户ID和链接状态查询
    List<LinkInfo> selectLinkByUserIdAndStatus(@Param("userId") Integer userId,@Param("status") Integer status);
    //按照用户ID和链接状态统计链接数量
    int countLinkByUserIdAndStatus(@Param("userId") Integer userId,@Param("status") Integer status);
    //插入新的链接
    int insertLinkInfo(LinkInfo linkInfo);
    //按照链接ID更新链接状态
    int updateLinkStatus(@Param("linkId") Integer linkId,@Param("status") Integer status);
    //按照链接ID增加链接点赞数
    int increaseLinkLikeNum(Integer linkId);
}
