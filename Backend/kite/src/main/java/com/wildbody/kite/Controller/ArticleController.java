package com.wildbody.kite.Controller;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.Service.ArticleService;
import com.wildbody.kite.Service.MemberService;
import com.wildbody.kite.Util.DateUtil;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

  static final int offset = 5;
  @Autowired private ArticleService svc;
  @Autowired private MemberService msvc;

  @PostMapping("/regist")
  @ApiOperation(value = "article 등록 서비스")
  public @ResponseBody ResponseEntity<Map<String, Object>> registerArticle(
      @RequestBody Article dto) {
    ResponseEntity<Map<String, Object>> resEntity = null;
    try {
      int insert = svc.articleInsert(dto);
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
  public @ResponseBody ResponseEntity<Map<String, Object>> updateArticle(@RequestBody Article dto) {
    ResponseEntity<Map<String, Object>> resEntity = null;
    try {
      int update = svc.articleUpdate(dto);
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
  public ResponseEntity<Map<String, Object>> deleteArticle(@PathVariable("id") String id) {
    ResponseEntity<Map<String, Object>> resEntity = null;
    try {
      int delete = svc.articleDelete(id);
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

  @GetMapping("/info")
  @ApiOperation(value = "id를 받아 article 조회 서비스", response = Article.class)
  public ResponseEntity<Map<String, Object>> infoArticle(HttpServletRequest request) {
    ResponseEntity<Map<String, Object>> resEntity = null;
    Map<String, Object> map = new HashMap<String, Object>();
    String email = request.getHeader("email");
    int pageNum = Integer.parseInt(request.getHeader("page"));
    Member member = new Member();
    member.setEmail(email);
    try {
      // 몇칸으로 자를건지를 알아내야 한다
      // 페이지수 * 자른 칸 수 만큼 보낸다
      int start = (pageNum - 1) * offset;
      int end = start + offset;
      map.put("message", "기사 조회 성공");
      map.put("article", msvc.memberInfo(member).getArticleList().subList(start, end));
    } catch (RuntimeException e) {
      map.put("message", "기사 조회 실패");
    }
    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
  }

  @GetMapping("/list")
  @ApiOperation("기사 목록 조회 서비스")
  public @ResponseBody ResponseEntity<Map<String, Object>> listArticle() {
    ResponseEntity<Map<String, Object>> resEntity = null;
    List<Article> list = null;
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      map.put("message", "기사 목록 조회 성공");
      map.put("resvalue", svc.articleList());
    } catch (RuntimeException e) {
      map.put("message", "기사 목록 조회 실패");
      map.put("error", e.getMessage());
    }
    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
  }

  // likeArticle : 멤버가 관심기업으로 등록한 회사들의 기사만 출력해줌
  public ResponseEntity<Map<String, Object>> likeArticle() {
    Map<String, Object> map = new HashMap<String, Object>();
    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
  }

  @GetMapping("/onearticle/{articleid}")
  @ApiOperation("articleid에 맞은 기사 하나만 출력")
  public ResponseEntity<Map<String, Object>> oneArticle(@PathVariable String articleid) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      map.put("result", svc.oneArticle(Integer.parseInt(articleid)));
      map.put("msg", true);
    } catch (Exception e) {
      e.printStackTrace();
      map.put("msg", false);
    }
    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
  }

  //  companyArticle : 기업의 기사만 출력

  public ResponseEntity<Map<String, Object>> companyArticle() {
    Map<String, Object> map = new HashMap<String, Object>();
    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
  }

  @PostMapping("/infiloading/{page}")
  @ApiOperation("Infinite Loading")
  public @ResponseBody ResponseEntity<Map<String, Object>> infiniteLoading(
      @PathVariable int page, Member member, Article article) {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Article> list = new ArrayList<>();
    member = msvc.memberInfo(member);
    String company = article.getCompany();
    System.out.println(company);
    try {
      if (company == null) {
        for (String comp : member.getCompany().split(",")) {
          list.addAll(svc.infi(comp));
        }
      } else {
        list.addAll(svc.infi(company));
      }
      map.put("result", DateUtil.getInstance().makeInfi(page, list));
      map.put("msg", true);
    } catch (Exception e) {
      e.printStackTrace();
      map.put("result", new ArrayList<>());
      map.put("msg", false);
    }
    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
  }
}
