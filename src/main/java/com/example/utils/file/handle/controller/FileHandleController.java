package com.example.utils.file.handle.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Blake on 2018/11/7
 * <p>
 * 文件操作：上传 & 下载
 */
@RestController
@RequestMapping("/api/static")
public class FileHandleController {

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        String path = ResourceUtils.getURL("").getPath();

        File convertFile = new File(path + "src/main/resources/static/upload/" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(convertFile);
        outputStream.write(file.getBytes());
        outputStream.close();
        return "{\"message\":\"File is upload successfully\"}";
    }

    /**
     * 文件下载
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/download")
    public ResponseEntity<Object> downloadFile() throws IOException {
        String filename = ResourceUtils.getURL("").getPath() + "/src/main/resources/static/upload/imgad.jpeg";
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt"))
                .body(resource);

        return responseEntity;
    }

}
