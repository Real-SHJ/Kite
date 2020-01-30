package com.wildbody.kite.Controller;

import com.wildbody.kite.Dto.Article;
import com.wildbody.kite.Service.ArticleService;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    ArticleService aSer;

    @PostMapping("/regist")
    @ApiOperation(value = "article 등록 서비스")
    private @ResponseBody
    ResponseEntity<Map<String, Object>> registerArticle(@RequestBody Article dto) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            int insert = aSer.articleInsert(dto);
            Map<String, Object> map = new HashMap<>();
            map.put("resvalue", insert);
            map.put("message", "기사 등록 성공");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "기사 등록 실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @PutMapping("/update")
    @ApiOperation(value = "article 수정 서비스")
    private @ResponseBody
    ResponseEntity<Map<String, Object>> updateArticle(@RequestBody Article dto) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            int update = aSer.articleUpdate(dto);
            Map<String, Object> map = new HashMap<>();
            map.put("resvalue", update);
            map.put("message", "기사 수정 성공");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "기사 수정 실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "id를 받아 article 삭제 서비스")
    private ResponseEntity<Map<String, Object>> deleteArticle(@PathVariable("id") String id) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            int delete = aSer.articleDelete(id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "기사 삭제 성공");
            map.put("resvalue", delete);
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "기사 삭제 실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "id를 받아 article 조회 서비스", response = Article.class)
    private ResponseEntity<Map<String, Object>> infoArticle(@PathVariable("id") String id) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        Article article = null;
        try {
            article = aSer.articleInfo(id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "기사 조회 성공");
            map.put("article", article);
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "기사 조회 실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @GetMapping("/list")
    @ApiOperation("기사 목록 조회 서비스")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> listArticle() {
        ResponseEntity<Map<String, Object>> resEntity = null;
        List<Article> list = null;
        try {
            list = aSer.articleList();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "기사 목록 조회 성공");
            map.put("resvalue", list);
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "기사 목록 조회 실패");
            map.put("error", e.getMessage());
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }
}
