package cn.welfareZhu.item.myblog.service;

import cn.welfareZhu.item.myblog.dto.LabelDTO;
import cn.welfareZhu.item.myblog.model.LabelInfo;

import java.util.List;

public interface LabelService {
    //按照标签ID查询标签
    LabelDTO queryLabelByLabelId(Integer labelId);
    //按照标签值查询标签
    LabelDTO queryLabelByLabelValue(String value);
    //按照标签字符串进行模糊查询
    List<LabelDTO> queryLabelByStringValue(String value,int pageNum,int pageSize);
    //新建标签
    int addNewLabel(LabelInfo labelInfo);
    //按照标签ID修改标签状态
    boolean modifyLabelStatus(Integer labelId,Integer status);
    //按照标签ID增加使用数
    boolean increaseLabelUseNum(Integer labelId);
}
