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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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


    @PostMapping("/file/download/{id}")
    public ResponseEntity<HttpServletResponse> download(@PathVariable String id, HttpServletRequest httpServletRequest,
                                                        HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path + "/" + id);

            // 以流的形式下载文件。
            InputStream fis = null;
                fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
            response.addHeader("Access-Control-Expose-Headers","token,uid,Content-Disposition");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Allow-Credentials","true");
            response.addHeader("Content-Disposition", "attachment;");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }


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
            // 保存文件
            file.transferTo(dest);
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
