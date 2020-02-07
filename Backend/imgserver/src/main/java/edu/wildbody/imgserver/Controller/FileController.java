package edu.wildbody.imgserver.Controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileController {

  private static String path = null;

  FileController(@Value("${file.upload.path}") String path) {
    this.path = path;
  }

  @PostMapping("/profile/upload")
  public ResponseEntity<Map<String, Object>> profile(@RequestPart("profile") MultipartFile file, @RequestParam("imageName") String imageName) {
    String msg = null;
    boolean isupload = false;
    System.out.println("receive file : " + imageName + "  " + file.getContentType());
    ResponseEntity<Map<String, Object>> ret = null;
    Map<String, Object> map = new HashMap<String, Object>();

    if (!"image".contains(file.getContentType().split("/")[0])) {
      // 에러 페이지
      msg = "not allow file type";
      isupload = false;
    } else {
      try {
        file.transferTo(new File(path + "profile/", imageName));
        msg = "file upload success";
        isupload = true;
      } catch (Exception e) {
        // 파일 업로드 에러
        System.out.println(e.getMessage());
        msg = "error with file uploading";
      }
    }
    map.put("isupload", isupload);
    map.put("msg", msg);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @PostMapping("/thumbnail/upload")
  public ResponseEntity<Map<String, Object>> thumnail(
      @RequestPart("thumbnail") MultipartFile file) {
    String msg = null;
    boolean isupload = false;
    String filename = file.getOriginalFilename();
    String conType = file.getContentType();
    System.out.println("receive file : " + filename + "  " + conType);

    Map<String, Object> map = new HashMap<String, Object>();

    if (!"image".contains(file.getContentType().split("/")[0])) {
      // 에러 페이지
      msg = "not allow file type";
      isupload = false;
    } else {
      try {
        file.transferTo(new File(path + "thumbnail/", filename));
        msg = "file upload success";
        isupload = true;
      } catch (Exception e) {
        // 파일 업로드 에러
        System.out.println(e.getMessage());
        msg = "error with file uploading";
      }
    }

    map.put("isupload", isupload);
    map.put("msg", msg);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @PostMapping("/article/upload")
  public ResponseEntity<Map<String, Object>> article(@RequestPart("article") MultipartFile file) {
    String msg = null;
    boolean isupload = false;
    String filename = file.getOriginalFilename();
    String conType = file.getContentType();
    System.out.println("receive file : " + filename + "  " + conType);

    ResponseEntity<Map<String, Object>> ret = null;
    Map<String, Object> map = new HashMap<String, Object>();

    if (!"image".contains(file.getContentType().split("/")[0])) {
      // 에러 페이지
      msg = "not allow file type";
      isupload = false;
    } else {
      try {
        file.transferTo(new File(path + "article/", filename));
        msg = "file upload success";
        isupload = true;
      } catch (Exception e) {
        // 파일 업로드 에러
        System.out.println(e.getMessage());
        msg = "error with file uploading";
      }
    }

    map.put("isupload", isupload);
    map.put("msg", msg);
    ret = new ResponseEntity<>(map, HttpStatus.OK);

    return ret;
  }

  @PostMapping("/logo/upload")
  public ResponseEntity<Map<String, Object>> logo(@RequestPart("logo") MultipartFile file) {
    String msg = null;
    boolean isupload = false;
    String filename = file.getOriginalFilename();
    String conType = file.getContentType();
    System.out.println("receive file : " + filename + "  " + conType);

    ResponseEntity<Map<String, Object>> ret = null;
    Map<String, Object> map = new HashMap<String, Object>();

    if (!"image".contains(file.getContentType().split("/")[0])) {
      // 에러 페이지
      msg = "not allow file type";
      isupload = false;
    } else {
      try {
        file.transferTo(new File(path + "logo/", filename));
        msg = "file upload success";
        isupload = true;
      } catch (Exception e) {
        // 파일 업로드 에러
        System.out.println(e.getMessage());
        msg = "error with file uploading";
      }
    }

    map.put("isupload", isupload);
    map.put("msg", msg);
    ret = new ResponseEntity<>(map, HttpStatus.OK);

    return ret;
  }
}
