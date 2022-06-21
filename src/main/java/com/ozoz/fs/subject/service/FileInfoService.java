package com.ozoz.fs.subject.service;


import com.ozoz.fs.subject.entity.FileInfo;

/**
 * @author: cnljj1995@gmail.com
 * @Date: 2022/6/21
 */
public interface FileInfoService {

    FileInfo findById(String id);

    FileInfo saveFileInfo(FileInfo fileInfo);
}
