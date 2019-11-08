package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.FileInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱剑飞
 * @name 文件信息DAO
 * @date 2019-8-13
 * **/
@Repository
public interface FileInfoDao {
    //按照文件ID查询
    FileInfo selectFileInfoByFileId(Integer fileId);
    //按照权限和状态查询
    List<FileInfo> selectFileByAS(@Param("auth") Integer auth,@Param("status") Integer status);
    //按照用户ID和权限查询
    List<FileInfo> selectFileByUserIdAndAuth(@Param("userId") Integer userId,@Param("auth") Integer auth);
    //按照用户ID和状态查询
    List<FileInfo> selectFileByUserIdAndStatus(@Param("userId") Integer userId,@Param("status") Integer status);
    //按照用户ID、权限和状态查询
    List<FileInfo> selectFileByUserIdAndAuthAndStatus(@Param("userId") Integer userId,@Param("auth") Integer auth,@Param("status") Integer status);
    //插入新的文件
    int insertFileInfo(FileInfo fileInfo);
    //更新文件描述(Path)
    int updateFilePath(@Param("fileId") Integer fileId,@Param("path") String path);
    //按照文件ID更新文件权限
    int updateFileAuth(@Param("fileId") Integer fileId,@Param("auth") Integer auth);
    //按照文件ID更新文件状态
    int updateFileStatus(@Param("fileId") Integer fileId,@Param("status") Integer status);
    //按照文件ID增加点赞数量
    int increaseFileLikeNum(Integer fileId);
}
