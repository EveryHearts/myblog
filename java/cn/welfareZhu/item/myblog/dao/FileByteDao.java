package cn.welfareZhu.item.myblog.dao;

import cn.welfareZhu.item.myblog.model.FileByte;
import org.springframework.stereotype.Repository;

/**
 * @author 朱剑飞
 * @name 文件数据流DAO
 * @date 2019-8-13
 * **/
@Repository
public interface FileByteDao {
    //按照文件ID查询
    FileByte selectFileByteByFileId(Integer fileId);
    //插入新的文件数据流
    int insertFileByte(FileByte fileByte);
}
