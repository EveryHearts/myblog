package cn.welfareZhu.item.myblog.service;

import cn.welfareZhu.item.myblog.dto.FileDTO;
import cn.welfareZhu.item.myblog.model.FileByte;
import cn.welfareZhu.item.myblog.model.FileInfo;
import cn.welfareZhu.item.myblog.util.QueryPageMap;

public interface FileService {
    //按照文件ID查询
    FileDTO queryFileByFileId(Integer fileId);
    //按照文件权限和状态查询
    QueryPageMap<FileDTO> queryFileByAuthAndStatus(Integer auth,Integer status,int pageNum,int pageSize);
    //按照用户ID和权限查询
    QueryPageMap<FileDTO> queryFileByUserIdAndAuth(Integer userId,Integer auth,int pageNum,int pageSize);
    //按照用户ID和状态查询
    QueryPageMap<FileDTO> queryFileByUserIdAndStatus(Integer userId,Integer status,int pageNum,int pageSize);
    //按照用户ID、权限和状态查询
    QueryPageMap<FileDTO> queryFileByUserIdAndAuthAndStatus(Integer userId, Integer auth ,Integer status, int pageNum, int pageSize);
    //添加新文件
    FileInfo addNewFile(FileInfo fileInfo);
    //按照文件ID修改文件描述（Path）
    boolean modifyPathByFileId(Integer fileId,String path);
    //按照文件ID修改文件权限
    boolean modifyAuthByFileId(Integer fileId,Integer auth);
    //按照文件ID修改文件状态
    boolean modifyStatusByFileId(Integer fileId,Integer status);
    //按照文件ID增加点赞数量
    boolean increaseFileLikeNum(Integer fileId);
    //按照文件ID查询文件数据流
    FileByte queryFileByteByFileId(Integer fileId);
    //添加新的文件数据流
    boolean addNewFileByte(FileByte fileByte);
}
