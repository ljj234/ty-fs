package com.ozoz.fs.subject.service;


import com.ozoz.fs.subject.entity.FileInfo;

/**
 * @author: cnljj1995@gmail.com
 * @Date: 2022/6/21
 */
public interface FileInfoService {

    /**
     * 根据文件id查询文件对象
     * @param id id
     * @return obj
     */
    FileInfo findById(String id);

    /**
     * 保存文件信息对象
     * @param fileInfo 文件对象
     * @return 保存后的文件对象
     */
    FileInfo saveFileInfo(FileInfo fileInfo);
}
