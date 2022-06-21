package com.ozoz.fs.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author: cnljj1995@gmail.com
 * @Date: 2022/6/21
 */
@Slf4j
@Component
public class FileTask {

    @Value("${file.path}")
    private String path;


    @Scheduled(cron = "59 59 23 * * ?")
    public void deleteFile() {
        log.info("定时删除文件下的目录");
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (File f : tempList) {
            f.delete();
        }
    }
}
