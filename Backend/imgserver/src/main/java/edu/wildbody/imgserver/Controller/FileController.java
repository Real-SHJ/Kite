package edu.wildbody.imgserver.Controller;

import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/file")
@CrossOrigin("*")
public class FileController {

    private static String path = null;

    FileController(@Value("${file.upload.path}") String path) {
        this.path = path;
    }

    @PostMapping("/upload")
    public String upload(@RequestPart("images") MultipartFile file) {
        String msg = "file upload fail";
        String filename = file.getOriginalFilename();
        String conType = file.getContentType();

        System.out.println("receive file : " + filename + "  " + conType);

        if (!"image".contains(file.getContentType().split("/")[0])) {
            //에러 페이지
            return "not allow file type";
        }

        try {
            file.transferTo(new File(path, filename));
            msg = "file upload success";
        } catch (Exception e) {
            // 파일 업로드 에러
            System.out.println(e.getMessage());
        }

        return msg;
    }
}
