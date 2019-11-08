package cn.welfareZhu.item.myblog.service.impl;

import cn.welfareZhu.item.myblog.dao.LabelInfoDao;
import cn.welfareZhu.item.myblog.dto.LabelDTO;
import cn.welfareZhu.item.myblog.model.LabelInfo;
import cn.welfareZhu.item.myblog.service.LabelService;
import cn.welfareZhu.item.myblog.util.QuerySQLException;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 朱剑飞
 * @name 标签Service
 * @date 2019-08-29
 * **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class LabelServiceImpl implements LabelService {
    @Resource
    private LabelInfoDao labelInfoDao;

    @Override
    public LabelDTO queryLabelByLabelId(Integer labelId) {
        LabelInfo labelInfo=labelInfoDao.selectLabelByLabelId(labelId);
        if(labelInfo==null){
            return null;
        }
        LabelDTO label=new LabelDTO();
        label.setLabelId(labelId);
        label.setLabelValue(labelInfo.getLabelValue());
        label.setUseNum(labelInfo.getUseNum());
        return label;
    }

    @Override
    public LabelDTO queryLabelByLabelValue(String value) {
        LabelInfo labelInfo=labelInfoDao.selectLabelInfoByLabelValue(value);
        if (labelInfo==null){
            return null;
        }
        LabelDTO label=new LabelDTO();
        label.setLabelId(labelInfo.getLabelId());
        label.setLabelValue(labelInfo.getLabelValue());
        label.setUseNum(labelInfo.getUseNum());
        return label;
    }

    @Override
    public List<LabelDTO> queryLabelByStringValue(String value,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<LabelInfo> labelInfos=labelInfoDao.selectLabelInfoByStringValue(value);
        if (labelInfos.size()<=0){
            return null;
        }
        List<LabelDTO> labels=new ArrayList<>();
        for(LabelInfo labelInfo:labelInfos){
            LabelDTO label=new LabelDTO();
            label.setLabelId(labelInfo.getLabelId());
            label.setLabelValue(labelInfo.getLabelValue());
            label.setUseNum(labelInfo.getUseNum());
            labels.add(label);
        }
        return labels;
    }

    @Override
    public int addNewLabel(LabelInfo labelInfo) {
        if (labelInfo==null){
            return 0;
        }
        try{
            int count=labelInfoDao.insertLabelInfo(labelInfo);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 添加新的标签值失败！ Value is : "+labelInfo.getLabelValue());
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return 0;
        }
        return labelInfo.getLabelId();
    }

    @Override
    public boolean modifyLabelStatus(Integer labelId, Integer status) {
        try{
            int count=labelInfoDao.updateLabelInfoStatus(labelId,status);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改标签状态失败！ 标签ID："+labelId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean increaseLabelUseNum(Integer labelId) {
        try {
            int count=labelInfoDao.increaseLabelUseNum(labelId);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 增加标签点赞数失败！ 标签ID："+labelId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }
}
