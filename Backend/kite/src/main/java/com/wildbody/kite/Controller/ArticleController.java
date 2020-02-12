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
  static final String imgUrl="http://13.125.153.118:8999/img";
  static Map<String,String> background_img=new HashMap<>();
  static Map<String,String> logo_img=new HashMap<>();
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

  @GetMapping("/info/{articleid}")
  @ApiOperation(value = "id를 받아 article 조회 서비스", response = Article.class)
  public ResponseEntity<Map<String, Object>> infoArticle(@PathVariable("articleid") String articleid) {
	  ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("message", "기사 조회 성공");
			Article article = svc.articleInfo(Integer.parseInt(articleid));
			map.put("article", article);
		} catch (RuntimeException e) {
			map.put("message", "기사 조회 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
  }

  @GetMapping("/list")
  @ApiOperation("기사 목록 조회 서비스")
  public @ResponseBody ResponseEntity<Map<String, Object>> listArticle() {
    //------------------------------------------
    
    ResponseEntity<Map<String, Object>> resEntity = null;
    List<Article> list = null;
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      list=svc.articleList();
      for(Article ar:list){
        if(background_img.containsKey(ar.getCompany())){
          ar.setImage(imgUrl+background_img.get(ar.getCompany()));
          ar.setLogo(imgUrl+logo_img.get(ar.getCompany()));
          System.out.println(imgUrl+background_img.get(ar.getCompany()));
        }
      }
      map.put("message", "기사 목록 조회 성공");
      map.put("resvalue", list);
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

  public ArticleController(){
    input_img();
    input_logo();
  }
  public static void input_logo(){
    logo_img.put("삼성전자", "/logo/Samsung_Elec.svg");
    logo_img.put("LG전자", "/logo/LG_Elec.svg");
    logo_img.put("SK텔레콤", "/logo/SK_Telecom.svg");
    logo_img.put("GS칼텍스", "/logo/GS_Caltex.svg");
    logo_img.put("KT", "/logo/KT.svg");
    logo_img.put("네이버", "/logo/Naver.svg");
    logo_img.put("S-OIL", "/logo/S-Oil.svg");
    logo_img.put("SK하이닉스", "/logo/SK_Hynix.svg");
    logo_img.put("현대자동차", "/logo/Hyundai_Car.svg");
    logo_img.put("CJ제일제당", "/logo/CJ_Cheiljedang.svg");
    logo_img.put("국민은행", "/logo/KB_Bank.svg");
    logo_img.put("포스코", "/logo/Posco.svg");
    logo_img.put("삼성SDS", "/logo/Samsung_SDS.svg");
    logo_img.put("신한은행", "/logo/Shinhan_Bank.svg");
    logo_img.put("쿠팡", "/logo/Coupang.svg");
    logo_img.put("하나은행", "/logo/Hana_Bank.svg");
    logo_img.put("현대모비스", "/logo/Hyundai_Mobis.svg");
    logo_img.put("IBK기업은행", "/logo/IBK_Bank.svg");
    logo_img.put("현대모비스", "/logo/Hyundai_Mobis.svg");
    logo_img.put("카카오", "/logo/Kakao.svg");
    logo_img.put("한국전력공사", "/logo/Korea_Elec.svg");
    logo_img.put("LG화학", "/logo/LG_Chemi.svg");
    logo_img.put("LG유플러스", "/logo/LG_Uplus.svg");
    logo_img.put("우리은행", "/logo/Woori_Bank.svg");
  }
  public static void input_img(){
    background_img.put("삼성전자", "/tmp/samsung.jpg");
    background_img.put("LG전자", "/tmp/lg.jpg");
    background_img.put("SK텔레콤", "/tmp/sk.jpg");
    background_img.put("GS칼텍스", "/tmp/gs.jpg");
    background_img.put("KT", "/tmp/kt.jpg");
    background_img.put("네이버", "/tmp/naver.jpg");
    background_img.put("S-OIL", "/tmp/soil.jpg");
    background_img.put("SK하이닉스", "/tmp/sk.jpg");
    background_img.put("현대자동차", "/tmp/Hyundai.jpg");
    background_img.put("CJ제일제당", "/tmp/cj.jpg");
    background_img.put("포스코", "/tmp/posco.jpg");
    background_img.put("삼성SDS", "/samsung.jpg");
    background_img.put("현대모비스", "/tmp/Hyundai.jpg");
    background_img.put("카카오", "/tmp/kakao.jpg");
    background_img.put("LG화학", "/tmp/lg.jpg");
    background_img.put("LG유플러스", "/tmp/lg.jpg");
  }
}
