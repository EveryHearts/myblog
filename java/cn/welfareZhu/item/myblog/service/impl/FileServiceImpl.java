package cn.welfareZhu.item.myblog.service.impl;

import cn.welfareZhu.item.myblog.dao.FileByteDao;
import cn.welfareZhu.item.myblog.dao.FileInfoDao;
import cn.welfareZhu.item.myblog.dao.UserSearchDao;
import cn.welfareZhu.item.myblog.dto.FileDTO;
import cn.welfareZhu.item.myblog.model.FileByte;
import cn.welfareZhu.item.myblog.model.FileInfo;
import cn.welfareZhu.item.myblog.model.UserSearch;
import cn.welfareZhu.item.myblog.service.FileService;
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
 * @name 文件Service
 * @date 2019-09-02
 * **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class FileServiceImpl implements FileService {
    @Resource
    private UserSearchDao userSearchDao;
    @Resource
    private FileInfoDao fileInfoDao;
    @Resource
    private FileByteDao fileByteDao;

    @Override
    public FileDTO queryFileByFileId(Integer fileId) {
        FileInfo fileInfo=fileInfoDao.selectFileInfoByFileId(fileId);
        if (fileInfo==null){
            return null;
        }
        FileDTO file=new FileDTO();
        file.setFileId(fileInfo.getFileId());
        file.setFilePath(fileInfo.getFilePath());
        file.setFileName(fileInfo.getFileName());
        file.setFileType(fileInfo.getFileType());
        file.setFileAuth(fileInfo.getFileAuth());
        file.setLikeNum(fileInfo.getLikeNum());
        file.setFileStatus(fileInfo.getFileStatus());
        file.setCreateDate(fileInfo.getCreateDate());
        UserSearch user=userSearchDao.selectUserSearchByUserId(fileInfo.getUserId());
        file.setUserId(fileInfo.getUserId());
        file.setUserNickname(user.getUserNickname());
        file.setUserViaSrc(user.getUserViaSrc());
        return file;
    }

    @Override
    public QueryPageMap<FileDTO> queryFileByAuthAndStatus(Integer auth, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<FileInfo> fileInfos=fileInfoDao.selectFileByAS(auth,status);
        PageInfo pageInfo=new PageInfo(fileInfos);
        if (fileInfos.size()<=0){
            return null;
        }
        List<FileDTO> files=new ArrayList<>();
        for (FileInfo fileInfo:fileInfos){
            FileDTO file=new FileDTO();
            file.setFileId(fileInfo.getFileId());
            file.setFilePath(fileInfo.getFilePath());
            file.setFileName(fileInfo.getFileName());
            file.setFileType(fileInfo.getFileType());
            file.setFileAuth(fileInfo.getFileAuth());
            file.setLikeNum(fileInfo.getLikeNum());
            file.setFileStatus(fileInfo.getFileStatus());
            file.setCreateDate(fileInfo.getCreateDate());
            UserSearch user=userSearchDao.selectUserSearchByUserId(fileInfo.getUserId());
            file.setUserId(fileInfo.getUserId());
            file.setUserNickname(user.getUserNickname());
            file.setUserViaSrc(user.getUserViaSrc());
            files.add(file);
        }
        QueryPageMap<FileDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(files);
        return queryPageMap;
    }

    @Override
    public QueryPageMap<FileDTO> queryFileByUserIdAndAuth(Integer userId, Integer auth, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<FileInfo> fileInfos=fileInfoDao.selectFileByUserIdAndAuth(userId,auth);
        PageInfo pageInfo=new PageInfo();
        if (fileInfos.size()<=0){
            return null;
        }
        List<FileDTO> files=new ArrayList<>();
        for (FileInfo fileInfo:fileInfos){
            FileDTO file=new FileDTO();
            file.setFileId(fileInfo.getFileId());
            file.setFilePath(fileInfo.getFilePath());
            file.setFileName(fileInfo.getFileName());
            file.setFileType(fileInfo.getFileType());
            file.setFileAuth(fileInfo.getFileAuth());
            file.setLikeNum(fileInfo.getLikeNum());
            file.setFileStatus(fileInfo.getFileStatus());
            file.setCreateDate(fileInfo.getCreateDate());
            UserSearch user=userSearchDao.selectUserSearchByUserId(fileInfo.getUserId());
            file.setUserId(fileInfo.getUserId());
            file.setUserNickname(user.getUserNickname());
            file.setUserViaSrc(user.getUserViaSrc());
            files.add(file);
        }
        QueryPageMap<FileDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(files);
        return queryPageMap;
    }

    @Override
    public QueryPageMap<FileDTO> queryFileByUserIdAndStatus(Integer userId, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<FileInfo> fileInfos=fileInfoDao.selectFileByUserIdAndStatus(userId,status);
        PageInfo pageInfo=new PageInfo(fileInfos);
        if (fileInfos.size()<=0){
            return null;
        }
        List<FileDTO> files=new ArrayList<>();
        for (FileInfo fileInfo:fileInfos){
            FileDTO file=new FileDTO();
            file.setFileId(fileInfo.getFileId());
            file.setFilePath(fileInfo.getFilePath());
            file.setFileName(fileInfo.getFileName());
            file.setFileType(fileInfo.getFileType());
            file.setFileAuth(fileInfo.getFileAuth());
            file.setLikeNum(fileInfo.getLikeNum());
            file.setFileStatus(fileInfo.getFileStatus());
            file.setCreateDate(fileInfo.getCreateDate());
            UserSearch user=userSearchDao.selectUserSearchByUserId(fileInfo.getUserId());
            file.setUserId(fileInfo.getUserId());
            file.setUserNickname(user.getUserNickname());
            file.setUserViaSrc(user.getUserViaSrc());
            files.add(file);
        }
        QueryPageMap<FileDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(files);
        return queryPageMap;
    }

    @Override
    public QueryPageMap<FileDTO> queryFileByUserIdAndAuthAndStatus(Integer userId, Integer auth, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<FileInfo> fileInfos=fileInfoDao.selectFileByUserIdAndAuthAndStatus(userId,auth,status);
        PageInfo pageInfo=new PageInfo(fileInfos);
        if (fileInfos.size()<=0){
            return null;
        }
        List<FileDTO> files=new ArrayList<>();
        for (FileInfo fileInfo:fileInfos){
            FileDTO file=new FileDTO();
            file.setFileId(fileInfo.getFileId());
            file.setFilePath(fileInfo.getFilePath());
            file.setFileName(fileInfo.getFileName());
            file.setFileType(fileInfo.getFileType());
            file.setFileAuth(fileInfo.getFileAuth());
            file.setLikeNum(fileInfo.getLikeNum());
            file.setFileStatus(fileInfo.getFileStatus());
            file.setCreateDate(fileInfo.getCreateDate());
            UserSearch user=userSearchDao.selectUserSearchByUserId(fileInfo.getUserId());
            file.setUserId(fileInfo.getUserId());
            file.setUserNickname(user.getUserNickname());
            file.setUserViaSrc(user.getUserViaSrc());
            files.add(file);
        }
        QueryPageMap<FileDTO> queryPageMap=new QueryPageMap<>();
        queryPageMap.setPageNum(pageInfo.getPageNum());
        queryPageMap.setPageSize(pageInfo.getPageSize());
        queryPageMap.setPages(pageInfo.getPages());
        queryPageMap.setSize(pageInfo.getSize());
        queryPageMap.setRecords(files);
        return queryPageMap;
    }

    @Override
    public FileInfo addNewFile(FileInfo fileInfo) {
        if (fileInfo==null){
            return null;
        }
        try{
            int count=fileInfoDao.insertFileInfo(fileInfo);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 添加新文件信息失败！ 用户ID："+fileInfo.getUserId()+" 文件名："+fileInfo.getFileName());
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return null;
        }
        return fileInfo;
    }

    @Override
    public boolean modifyPathByFileId(Integer fileId, String path) {
        try{
            int count=fileInfoDao.updateFilePath(fileId,path);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改文件Path失败！ 文件ID："+fileId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyAuthByFileId(Integer fileId, Integer auth) {
        try{
            int count=fileInfoDao.updateFileAuth(fileId,auth);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改文件权限失败！ 文件ID："+fileId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyStatusByFileId(Integer fileId, Integer status) {
        try{
            int count=fileInfoDao.updateFileStatus(fileId,status);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 修改文件状态失败！ 文件ID："+fileId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean increaseFileLikeNum(Integer fileId) {
        try {
            int count=fileInfoDao.increaseFileLikeNum(fileId);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 增加文件点赞数量失败！ 文件ID："+fileId);
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }

    @Override
    public FileByte queryFileByteByFileId(Integer fileId) {
        return fileByteDao.selectFileByteByFileId(fileId);
    }

    @Override
    public boolean addNewFileByte(FileByte fileByte) {
        try{
            int count=fileByteDao.insertFileByte(fileByte);
            if (count!=1){
                QuerySQLException e=new QuerySQLException();
                e.setMessage(new Date()+" 添加新的文件数据流信息失败！ 文件ID："+fileByte.getFileId());
                throw e;
            }
        }catch (QuerySQLException e){
            e.printMessage();
            return false;
        }
        return true;
    }
}
