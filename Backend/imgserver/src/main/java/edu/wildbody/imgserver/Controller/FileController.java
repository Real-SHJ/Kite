package edu.wildbody.imgserver.Controller;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/file")
public class FileController {

    private static final String prefix="/var/lib/img/";

    @RequestMapping(value = "/multiupload",method = RequestMethod.POST)
    public String upload(@RequestPart MultipartFile file){
        String msg = null;

        try{
        }catch (Exception e){
            // 파일 업로드 에러
        }

        return msg;
    }
}
