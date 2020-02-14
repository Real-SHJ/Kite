package com.wildbody.kite.Controller;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.Service.ArticleService;
import com.wildbody.kite.Service.MemberService;
import com.wildbody.kite.Util.DateUtil;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	static final String imgUrl = "http://13.125.153.118:8999/img";
	static Map<String, ArrayList<String>> background_img = new HashMap<>();
	static Map<String, String> logo_img = new HashMap<>();
	static final int offset = 5;
	@Autowired
	private ArticleService svc;
	@Autowired
	private MemberService msvc;

	@PostMapping("/regist")
	@ApiOperation(value = "article 등록 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> registerArticle(@RequestBody Article dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			int insert = svc.articleInsert(dto);
			map.put("result", insert);
			map.put("message", "기사 등록 성공");
		} catch (RuntimeException e) {
			map.put("message", "기사 등록 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@PutMapping("/update")
	@ApiOperation(value = "article 수정 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> updateArticle(@RequestBody Article dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			int update = svc.articleUpdate(dto);
			map.put("result", update);
			map.put("message", "기사 수정 성공");
		} catch (RuntimeException e) {
			map.put("message", "기사 수정 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@PostMapping("/delete/{id}")
	@ApiOperation(value = "id를 받아 article 삭제 서비스")
	public ResponseEntity<Map<String, Object>> deleteArticle(@PathVariable("id") String id) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int delete = svc.articleDelete(id);
			map.put("result", delete);
		} catch (RuntimeException e) {
			map.put("message", "기사 삭제 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@GetMapping("/onearticle/{articleid}")
	@ApiOperation(value = "articleid에 맞은 기사 하나만 출력", response = Article.class)
	public ResponseEntity<Map<String, Object>> oneArticle(@PathVariable String articleid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Article article = svc.oneArticle(Integer.parseInt(articleid));
			map.put("result", article);
			map.put("message", "기사 1개 조회 성공");
		} catch (Exception e) {
			map.put("message", "기사 1개 조회 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@GetMapping("/list")
	@ApiOperation(value = "기사 목록 조회 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> listArticle() {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Article> list = svc.articleList();
			for (Article ar : list) {
				if (background_img.containsKey(ar.getCompany())) {
					int index = background_img.get(ar.getCompany()).size();
					int random_index = (int) (Math.random() * index);
					System.out.println("랜덤인덱스:" + random_index);
					ar.setImage(imgUrl + background_img.get(ar.getCompany()).get(random_index));
					ar.setLogo(imgUrl + logo_img.get(ar.getCompany()));
					System.out.println(imgUrl + background_img.get(ar.getCompany()));
				}
			}
			map.put("result", list);
			map.put("message", "기사 목록 조회 성공");
		} catch (RuntimeException e) {
			map.put("message", "기사 목록 조회 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@PostMapping("/infiloading/{page}")
	@ApiOperation("Infinite Loading")
	public @ResponseBody ResponseEntity<Map<String, Object>> infiniteLoading(@PathVariable int page, Member member, Article article) {
		ResponseEntity<Map<String, Object>> resEntity = null;
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
			map.put("message", "인피니티 로딩 성공");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", new ArrayList<>());
			map.put("message", "인피니티 로딩 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	public ArticleController() {
		input_img();
		input_logo();
	}

	public static void input_logo() {
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

	public static void input_img() {
		ArrayList<String> arr[] = new ArrayList[20];
		for (int i = 0; i < 20; i++) {
			arr[i] = new ArrayList<String>();
		}
		int cnt = 5;
		for (int i = 1; i <= cnt; i++) {
			arr[0].add("/tmp/samsung" + Integer.toString(i) + ".jpg");
		}
		background_img.put("삼성전자", arr[0]);
		background_img.put("삼성SDS", arr[0]);
		for (int i = 1; i <= cnt; i++) {
			arr[1].add("/tmp/lg" + Integer.toString(i) + ".jpg");
		}
		background_img.put("LG전자", arr[1]);
		background_img.put("LG유플러스", arr[1]);
		background_img.put("LG화학", arr[1]);
		for (int i = 1; i <= cnt; i++) {
			arr[2].add("/tmp/sk" + Integer.toString(i) + ".jpg");
		}
		background_img.put("SK텔레콤", arr[2]);
		background_img.put("SK하이닉스", arr[2]);
		for (int i = 1; i <= cnt; i++) {
			arr[3].add("/tmp/gs" + Integer.toString(i) + ".jpg");
		}
		background_img.put("GS칼텍스", arr[3]);
		for (int i = 1; i <= cnt; i++) {
			arr[4].add("/tmp/kt" + Integer.toString(i) + ".jpg");
		}
		background_img.put("KT", arr[4]);
		for (int i = 1; i <= cnt; i++) {
			arr[5].add("/tmp/soil" + Integer.toString(i) + ".jpg");
		}
		background_img.put("S-OIL", arr[5]);
		arr[6].add("/tmp/naver.jpg");
		background_img.put("네이버", arr[6]);
		arr[7].add("/tmp/Hyundai.jpg");
		background_img.put("현대자동차", arr[7]);
		background_img.put("현대모비스", arr[7]);
		arr[8].add("/tmp/cj.jpg");
		background_img.put("CJ제일제당", arr[8]);
		arr[9].add("/tmp/posco.jpg");
		background_img.put("포스코", arr[9]);
		arr[10].add("/tmp/kakao.jpg");
		background_img.put("카카오", arr[10]);
	}
}
