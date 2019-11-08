package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.LabelInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱剑飞
 * @name 标签信息DAO
 * @date 2019-8-13
 * **/
@Repository
public interface LabelInfoDao {
    //按照标签ID查询
    LabelInfo selectLabelByLabelId(Integer labelId);
    //按照标签值进行查新
    LabelInfo selectLabelInfoByLabelValue(String value);
    //按照字符串进行模糊查询
    List<LabelInfo> selectLabelInfoByStringValue(String value);
    //插入新的标签
    int insertLabelInfo(LabelInfo labelInfo);
    //按照标签ID更新标签状态
    int updateLabelInfoStatus(@Param("labelId") Integer labelId, @Param("labelStatus") Integer labelStatus);
    //按照标签ID增加使用数
    int increaseLabelUseNum(Integer labelId);
}
