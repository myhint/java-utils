package com.example.utils.zip;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

/**
 * @Description ZIP压缩包下载工具类
 * @Author blake
 * @Date 2019-03-04 13:11
 * @Version 1.0
 */
public class ZipUtil {

    private static final Logger logger = LoggerFactory.getLogger(ZipUtil.class);

    /**
     * 下载ZIP格式压缩包
     *
     * @param imageNames 图片名称列表
     * @param response   响应体
     */
    public void downloadZipArchive(List<String> imageNames, HttpServletResponse response) {

        // 文件存放目录的路径
        String dirPath = "images";

        try {
            OutputStream out = customizeResponse(response, String.valueOf(new Date().getTime()), "zip");
            ZipOutputStream zipOut = new ZipOutputStream(out);

            for (String imageName : imageNames) {
                // 文件夹路径
                String filePath = dirPath + imageName;
                byte[] data = Files.readAllBytes(new File(filePath).toPath());

                Path path = Paths.get(filePath);

                // 命名ZIP文件，打包时移除目录所在路径
                zipOut.putNextEntry(new ZipEntry(path.toFile().getName()));
                customizeOutputStream(zipOut, data, BUFFER_SIZE);
                zipOut.closeEntry();
            }
            zipOut.flush();
            zipOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("CompressServiceImpl.downloadZipArchive ========= 下载失败: [{}] =========", e.getMessage());
        }

    }

    /**
     * 配置response实例
     *
     * @param response 响应体
     * @param fileName 文件名称
     * @param fileType 文件类型
     * @return OutputStream
     * @throws IOException
     */
    private OutputStream customizeResponse(HttpServletResponse response, String fileName,
                                           String fileType)
            throws IOException {

        fileName = new String(fileName.getBytes(), StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "." + fileType);
        response.setContentType("multipart/form-data");
        return response.getOutputStream();
    }

    /**
     * 将字节数据，写入到输出流outputStream
     *
     * @param out          输出流
     * @param data         希望写入的数据
     * @param bufferLength 写入数据是循环读取写入的，此为每次读取的字节长度
     * @throws IOException
     */
    private void customizeOutputStream(OutputStream out, byte[] data, int bufferLength)
            throws IOException {

        int readTimes = data.length % bufferLength;
        int count = readTimes == 0 ? data.length / bufferLength : data.length / bufferLength + 1;
        for (int countIndex = 0; countIndex < count; countIndex++) {
            if (countIndex == count - 1 && readTimes != 0) {
                out.write(data, countIndex * bufferLength, readTimes);
                continue;
            }
            out.write(data, countIndex * bufferLength, bufferLength);
        }
    }

}
