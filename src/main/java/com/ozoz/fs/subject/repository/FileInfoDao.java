package com.ozoz.fs.subject.repository;

import com.ozoz.fs.subject.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: cnljj1995@gmail.com
 * @Date: 2022/6/21
 */
public interface FileInfoDao extends JpaRepository<FileInfo, String> {
}
