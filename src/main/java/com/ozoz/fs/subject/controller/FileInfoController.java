package com.ozoz.fs.subject.controller;

import com.ozoz.fs.subject.entity.FileInfo;
import com.ozoz.fs.subject.service.FileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

/**
 * @author: cnljj1995@gmail.com
 * @Date: 2022/6/21
 */
@RestController
@Slf4j
@Api
@RequestMapping("/api/fs")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;

    @Value("${file.path}")
    private String path;

    @PostMapping("/file")
    @ApiOperation(value = "上传文件")
    public ResponseEntity<String> upLoadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();

        FileInfo build = FileInfo.builder()
                .fileName(fileName)
                .creatTime(LocalDateTime.now())
                .build();

        FileInfo fileInfo = fileInfoService.saveFileInfo(build);

        File dest = new File(new File(path).getAbsolutePath()+ "/" + fileInfo.getId());
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); // 保存文件
        } catch (Exception e) {
            log.error(e.toString());
        }
        return ResponseEntity.ok(fileInfo.getId());
    }

    @GetMapping("/file/{id}")
    @ApiOperation(value = "根据文件id获取文件")
    public ResponseEntity<FileInfo> findFile(@PathVariable String id) {
        FileInfo file = fileInfoService.findById(id);
        return ResponseEntity.ok(file);
    }

}
