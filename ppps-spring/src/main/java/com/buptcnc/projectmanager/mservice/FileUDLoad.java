package com.buptcnc.projectmanager.mservice;

import com.buptcnc.projectmanager.config.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@Service
public class FileUDLoad {
    public void download(String path, HttpServletResponse response) {
        File file = new File(path);
        if (!file.exists()) {
            log.debug("File not exists.");
            return;
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" +
                path.split("\\\\")[path.split("\\\\").length - 1]);
        log.info(path.split("\\\\")[path.split("\\\\").length - 1]);
        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String upload(MultipartFile file, String path) throws Exception {
        if (file == null || file.isEmpty()) {
//            throw new Exception("文件错误");
            return "FILE_ERROR";
        }
        String filePath = new File(path).getAbsolutePath();
        log.info("upload path: " + filePath);
        File fileUpload = new File(filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }
        fileUpload = new File(filePath, file.getOriginalFilename());

        if (fileUpload.exists()) {
//            throw new Exception("文件已存在");
            return Constant.EXIST;
        }
        try {
            file.transferTo(fileUpload);
            return fileUpload.getAbsolutePath();
        } catch (IOException e) {
            return  "上传失败：" + e;
        }
    }
}
