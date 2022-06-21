package com.ozoz.fs.subject.service;

import com.ozoz.fs.subject.entity.FileInfo;
import com.ozoz.fs.subject.repository.FileInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: cnljj1995@gmail.com
 * @Date: 2022/6/21
 */
@Service
@Slf4j
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoDao fileInfoDao;

    @Override
    public FileInfo findById(String id) {
        return fileInfoDao.findById(id).orElse(new FileInfo());
    }

    @Override
    public FileInfo saveFileInfo(FileInfo fileInfo) {
        return fileInfoDao.save(fileInfo);
    }
}
