package cn.welfareZhu.item.myblog.service.impl;

import cn.welfareZhu.item.myblog.dao.LabelInfoDao;
import cn.welfareZhu.item.myblog.dao.LinkInfoDao;
import cn.welfareZhu.item.myblog.dao.UserSearchDao;
import cn.welfareZhu.item.myblog.dto.LinkDTO;
import cn.welfareZhu.item.myblog.model.LabelInfo;
import cn.welfareZhu.item.myblog.model.LinkInfo;
import cn.welfareZhu.item.myblog.model.UserSearch;
import cn.welfareZhu.item.myblog.service.LinkService;
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
 * @name 链接Service
 * @date 2019-08-29
 * **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class LinkServiceImpl implements LinkService {
    @Resource
    private LinkInfoDao linkInfoDao;
    @Resource
    private UserSearchDao userSearchDao;
    @Resource
    private LabelInfoDao labelInfoDao;

    @Override
    public LinkDTO queryLinkByLinkId(Integer linkId) {
        LinkInfo linkInfo=linkInfoDao.selectLinkInfoByLinkId(linkId);
        if(linkInfo==null){
            return null;
        }
        LinkDTO link=new LinkDTO();
        link.setLinkId(linkInfo.getLinkId());
        link.setLinkSrc(linkInfo.getLinkSrc());
        link.setTitle(linkInfo.getTitle());
        link.setDescription(linkInfo.getDescription());
        link.setCreateDate(linkInfo.getCreateDate());
        link.setLikeNum(linkInfo.getLikeNum());
        UserSearch userSearch=userSearchDao.selectUserSearchByUserId(linkInfo.getUserId());
        link.setUserId(userSearch.getUserId());
        link.setUserNickname(userSearch.getUserNickname());
        link.setUserViaSrc(userSearch.getUserViaSrc());
        LabelInfo labelInfo=labelInfoDao.selectLabelByLabelId(linkInfo.getLabelId());
        link.setLabelValue(labelInfo.getLabelValue());
        return link;
    }

    @Override
    public QueryPageMap<LinkDTO> queryLinkByStatus(Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<LinkInfo> linkInfos=linkInfoDao.selectLinkByStatus(status);
        PageInfo pageInfo=new PageInfo(linkInfos);
        if (linkInfos.size()<=0){
            return null;
        }
        List<LinkDTO> links=new ArrayList<>();
        for (LinkInfo linkInfo:linkInfos){
            LinkDTO link=new LinkDTO();
            link.setLinkId(linkInfo.getLinkId());
            link.setLinkSrc(linkInfo.getLinkSrc());
            link.setTitle(linkInfo.getTitle());
            link.setDescription(linkInfo.getDescription());
            link.setCreateDate(linkInfo.getCreateDate());
            link.setLikeNum(linkInfo.getLikeNum());
            UserSearch userSearch=userSearchDao.selectUserSearchByUserId(linkInfo.getUserId());
            link.setUserId(userSearch.getUserId());
            link.setUserNickname(userSearch.getUserNickname());
            link.setUserViaSrc(userSearch.getUserViaSrc());
            LabelInfo labelInfo=labelInfoDao.selectLabelByLabelId(linkInfo.getLabelId());
            link.setLabelValue(labelInfo.getLabelValue());
            links.add(link);
        }
        QueryPageMap<LinkDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(links);
        return queryPageMap;
    }

    @Override
    public int queryCountForLinkByStatus(Integer status) {
        return linkInfoDao.countLinkByStatus(status);
    }

    @Override
    public QueryPageMap<LinkDTO> queryLinkByStatusAndLabelId(Integer status, Integer labelId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<LinkInfo> linkInfos=linkInfoDao.selectLinkBySL(status,labelId);
        PageInfo pageInfo=new PageInfo(linkInfos);
        if (linkInfos.size()<=0){
            return null;
        }
        List<LinkDTO> links=new ArrayList<>();
        for (LinkInfo linkInfo:linkInfos){
            LinkDTO link=new LinkDTO();
            link.setLinkId(linkInfo.getLinkId());
            link.setLinkSrc(linkInfo.getLinkSrc());
            link.setTitle(linkInfo.getTitle());
            link.setDescription(linkInfo.getDescription());
            link.setCreateDate(linkInfo.getCreateDate());
            link.setLikeNum(linkInfo.getLikeNum());
            UserSearch userSearch=userSearchDao.selectUserSearchByUserId(linkInfo.getUserId());
            link.setUserId(userSearch.getUserId());
            link.setUserNickname(userSearch.getUserNickname());
            link.setUserViaSrc(userSearch.getUserViaSrc());
            LabelInfo labelInfo=labelInfoDao.selectLabelByLabelId(linkInfo.getLabelId());
            link.setLabelValue(labelInfo.getLabelValue());
            links.add(link);
        }
        QueryPageMap<LinkDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(links);
        return queryPageMap;
    }

    @Override
    public int queryCountForLinkByStatusAndLabelId(Integer status, Integer labelId) {
        return linkInfoDao.countLinkBySL(status,labelId);
    }

    @Override
    public QueryPageMap<LinkDTO> queryLinkByUserIdAndStatus(Integer userId, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<LinkInfo> linkInfos=linkInfoDao.selectLinkByUserIdAndStatus(userId,status);
        PageInfo pageInfo=new PageInfo(linkInfos);
        if (linkInfos.size()<=0){
            return null;
        }
        List<LinkDTO> links=new ArrayList<>();
        for (LinkInfo linkInfo:linkInfos){
            LinkDTO link=new LinkDTO();
            link.setLinkId(linkInfo.getLinkId());
            link.setLinkSrc(linkInfo.getLinkSrc());
            link.setTitle(linkInfo.getTitle());
            link.setDescription(linkInfo.getDescription());
            link.setCreateDate(linkInfo.getCreateDate());
            link.setLikeNum(linkInfo.getLikeNum());
            UserSearch userSearch=userSearchDao.selectUserSearchByUserId(linkInfo.getUserId());
            link.setUserId(userSearch.getUserId());
            link.setUserNickname(userSearch.getUserNickname());
            link.setUserViaSrc(userSearch.getUserViaSrc());
            LabelInfo labelInfo=labelInfoDao.selectLabelByLabelId(linkInfo.getLabelId());
            link.setLabelValue(labelInfo.getLabelValue());
            links.add(link);
        }
        QueryPageMap<LinkDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(links);
        return queryPageMap;
    }

    @Override
    public int queryCountForLinkByUserIdAndStatus(Integer userId, Integer status) {
        return linkInfoDao.countLinkByUserIdAndStatus(userId,status);
    }

    @Override
    public LinkInfo addNewLink(LinkInfo linkInfo) {
        if (linkInfo==null){
            return null;
        }
        try {
            int count = linkInfoDao.insertLinkInfo(linkInfo);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 添加链接失败！ 用户ID："+linkInfo.getUserId());
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return null;
        }
        return linkInfo;
    }

    @Override
    public boolean modifyLinkStatus(Integer linkId, Integer status) {
        try{
            int count=linkInfoDao.updateLinkStatus(linkId,status);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改链接状态失败！ 链接ID："+linkId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean increaseLinkLikeNum(Integer linkId) {
        try{
            int count=linkInfoDao.increaseLinkLikeNum(linkId);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 增加链接点赞数异常！ 链接ID："+linkId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }
}
