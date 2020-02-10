package com.wildbody.kite.Controller;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.NaverMember;
import com.wildbody.kite.DTO.Token;
import com.wildbody.kite.JWT.JwtService;
import com.wildbody.kite.Service.ArticleService;
import com.wildbody.kite.Service.MemberService;
import com.wildbody.kite.Service.TokenService;
import com.wildbody.kite.Util.DateUtil;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {

	@Autowired
	private MemberService msvc;

	@Autowired
	private JwtService jsvc;

	@Autowired
	private TokenService tsvc;

	@Autowired
	private ArticleService asvc;

  @PostMapping("/signup")
  @ApiOperation(value = "member 등록 서비스")
  public @ResponseBody ResponseEntity<Map<String, Object>> registerMember(
      Member member, HttpServletResponse response) {
    ResponseEntity<Map<String, Object>> resEntity = null;
    Map<String, Object> map = new HashMap<>();
    int insert = msvc.memberInsert(member);
    member = msvc.memberInfo(member);
    if (insert == 1) {
      String token = jsvc.getAccessToken(member);
      map.put("message", "회원 가입 성공");
      response.addHeader("Authorization", token);
      Token aToken = new Token();
      aToken.setMemberid(member.getMemberid());
      aToken.setEmail(member.getEmail());
      aToken.setRefreshToken(token);
      tsvc.insert(aToken);
    } else {
      map.put("message", "회원 가입 실패");
    }
    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
  }

  @PutMapping("/update")
  @ApiOperation(value = "member 수정 서비스")
  public @ResponseBody ResponseEntity<Map<String, Object>> updateMember(Member member) {
    ResponseEntity<Map<String, Object>> resEntity = null;
    Map<String, Object> map = new HashMap<>();
    try {
      int update = msvc.memberUpdate(member);
      map.put("result", update);
      map.put("isupdate", true);
    } catch (RuntimeException e) {
      map.put("isupdate", "회원정보 수정 실패");
    }
    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
  }

	@DeleteMapping("/delete")
	@ApiOperation(value = "멤버 삭제")
	public ResponseEntity<Map<String, Object>> deleteMember(Member member) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			msvc.memberDelete(member);
			map.put("message", "회원 탈퇴 성공");
		} catch (RuntimeException e) {
			map.put("message", "회원 탈퇴 실패");
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

  @PostMapping("/info")
  @ApiOperation(value = "id를 받아 member 조회 서비스", response = Member.class)
  public @ResponseBody ResponseEntity<Map<String, Object>> infoMember(Member member) {
    ResponseEntity<Map<String, Object>> resEntity = null;
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      member = msvc.memberInfo(member);
      member.setPw("");
      map.put("message", "success");
      map.put("result", member);
    } catch (RuntimeException e) {
      map.put("message", "fail");
      e.printStackTrace();
    }
    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
  }

  @GetMapping("/list")
  @ApiOperation("회원 목록 조회 서비스")
  public @ResponseBody ResponseEntity<Map<String, Object>> listMember() {
    ResponseEntity<Map<String, Object>> resEntity = null;
    List<Member> list = null;
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      map.put("message", "회원 목록 조회 성공");
      map.put("result", msvc.memberList());
    } catch (RuntimeException e) {
      map.put("message", "회원 목록 조회 실패");
    }
    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
  }

	@PostMapping("/signin")
	@ApiOperation("로그인")
	public @ResponseBody ResponseEntity<Map<String, Object>> login(Member member, HttpServletResponse response) {
		ResponseEntity<Map<String, Object>> ret = null;
		Member loginMem = msvc.login(member);
		Map<String, Object> map = new HashMap<>();

		if (loginMem != null) {
			map.put("access_token", response.getHeader("Authorization"));
			map.put("isLogin", true);
		} else {
			map.put("isLogin", false);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@GetMapping("/naverlogin")
	public @ResponseBody ResponseEntity<Map<String, Object>> naverLogin(NaverMember naverMember) {
		ResponseEntity<Map<String, Object>> ret = null;
		Map<String, Object> map = new HashMap<>();

		// naver에 정보 요청

    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @PostMapping("/kakaologin")
  public @ResponseBody ResponseEntity<Map<String, Object>> kakaoLogin() {
    ResponseEntity<Map<String, Object>> ret = null;
    Map<String, Object> map = new HashMap<>();

    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @PostMapping("/scrap")
  @ApiOperation("스크랩")
  public @ResponseBody ResponseEntity<Map<String, Object>> scrapArticle(
      Member member, Article article) {
    Map<String, Object> map = new HashMap<>();
    if (article.getArticleid() > asvc.articleList().size()) {
      map.put("result", "out of range");
      map.put("msg", false);
      return new ResponseEntity<>(map, HttpStatus.OK);
    }
    member = msvc.memberInfo(member);
    StringBuilder sb = new StringBuilder();
    String scrpas = msvc.getMyScrap(member);
    String articles = "";
    if (scrpas != null) {
      for (String val : scrpas.split(",")) {
        if (!val.equals("" + article.getArticleid())) sb.append(val).append(",");
      }
    }
    if (sb.length() > 1) articles = sb.toString() + "" + article.getArticleid();
    else articles = "" + article.getArticleid();

    try {
      map.put("result", msvc.scrapArticle(member, articles));
      map.put("msg", true);
    } catch (Exception e) {
      e.printStackTrace();
      map.put("msg", false);
    }
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

	@DeleteMapping("/delscrap")
	@ApiOperation("스크랩 삭제")
	public @ResponseBody ResponseEntity<Map<String, Object>> delScrap(Member member, Article article) {
		Map<String, Object> map = new HashMap<>();
		member = msvc.memberInfo(member);
		try {
			StringBuilder articles = new StringBuilder();
			for (String val : msvc.getMyScrap(member).split(",")) {
				if (!val.equals("" + article.getArticleid()))
					articles.append(val).append(",");
			}
			if (articles.length() > 0)
				articles.deleteCharAt(articles.length() - 1);
			msvc.scrapArticle(member, articles.toString());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", false);
		}

    return new ResponseEntity<>(map, HttpStatus.OK);
  }
  // todo 인피니티 로딩으로 바꾸자
  //  기사날짜로 검색, 검색기간, 기업명
  @PostMapping("/getscrap/{page}")
  @ApiOperation("스크랩한 기사만 가져온다")
  public @ResponseBody ResponseEntity<Map<String, Object>> showScraps(
      Member member, Article article, HttpServletRequest request, @PathVariable int page) {
    Map<String, Object> map = new HashMap<>();
    List<Article> list = new ArrayList<>();
    String start = request.getHeader("start"), end = request.getHeader("end");
    String company = article.getCompany();
    try {
      if (company == null) {
        for (String articleid : msvc.getMyScrap(msvc.memberInfo(member)).split(",")) {
          list.add(asvc.oneArticle(Integer.parseInt(articleid)));
        }
      } else {
        for (String articleid : msvc.getMyScrap(msvc.memberInfo(member)).split(",")) {
          Article a = asvc.oneArticle(Integer.parseInt(articleid));
          if (a.getCompany().trim().equals(company.trim())) list.add(a);
        }
      }
      if (start != null || end != null) {
        list = DateUtil.getInstance().getMatchedList(list, start, end);
      }

      // 잘라서 보내준다
      map.put("result", DateUtil.getInstance().makeInfi(page, list));
      map.put("msg", true);
    } catch (Exception e) {
      e.printStackTrace();
      map.put("msg", false);
    }

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

  @GetMapping("/likecomp")
  @ApiOperation("관시기업 기사만 가져온다")
  public @ResponseBody ResponseEntity<Map<String, Object>> likeCompanyArticle(Member member) {
    HashMap<String, Object> map = new HashMap<>();
    member = msvc.memberInfo(member);
    try {
      for (String comp : member.getCompany().split(",")) {
        map.put(comp, asvc.infi(comp));
      }
      map.put("msg", true);
    } catch (Exception e) {
      map.put("msg", false);
    }

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/friendlist/{memberid}")
	@ApiOperation("친구 목록 조회 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> listFriend(@PathVariable("memberid") String memberid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("message", "친구 목록 조회 성공");
			System.out.println("memberid:"+memberid);
			List<Member> flist = msvc.friendList(Integer.parseInt(memberid));
			for (int i = 0 ; i < flist.size(); i++) {
				System.out.println(flist.get(i));
			}
			map.put("flist", flist);
		} catch (RuntimeException e) {
			map.put("message", "친구 목록 조회 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@GetMapping("/requestlist/{memberid}")
	@ApiOperation("친구 요청 목록 조회 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> listRequest(@PathVariable("memberid") String memberid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("message", "친구 요청 목록 조회 성공");
			List<Member> rqlist = msvc.requestList(Integer.parseInt(memberid));
			for (int i = 0 ; i < rqlist.size(); i++) {
				System.out.println(rqlist.get(i));
			}
			map.put("rqlist", rqlist);
		} catch (RuntimeException e) {
			map.put("message", "친구 목록 조회 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@GetMapping("/responselist/{memberid}")
	@ApiOperation("친구 응답 목록 조회 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> listResponse(@PathVariable("memberid") String memberid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("message", "친구 응답 목록 조회 성공");
			List<Member> rslist = msvc.responseList(Integer.parseInt(memberid));
			for (int i = 0 ; i < rslist.size(); i++) {
				System.out.println(rslist.get(i));
			}
			map.put("rslist", rslist);
		} catch (RuntimeException e) {
			map.put("message", "친구 목록 조회 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@GetMapping("/norelationlist/{memberid}")
	@ApiOperation("관계 없는 회원 목록 조회 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> listNoRelation(@PathVariable("memberid") String memberid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("message", "관계 없는 회원 목록 조회 성공");
			List<Member> nrlist = msvc.noRelationList(Integer.parseInt(memberid));
			for (int i = 0 ; i < nrlist.size(); i++) {
				System.out.println(nrlist.get(i));
			}
			map.put("nrlist", nrlist);
		} catch (RuntimeException e) {
			map.put("message", "관계 없는 회원 목록 조회 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@PostMapping("/insertfriendwait/{requestid}/{responseid}")
	@ApiOperation(value = "친구 요청 등록 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> insertfriendwait(@PathVariable("requestid") String requestid, @PathVariable("responseid") String responseid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			System.out.println("requestid:"+requestid+",responseid:"+responseid);
			int insert = msvc.friendWaitInsert(Integer.parseInt(requestid), Integer.parseInt(responseid));
			System.out.println(insert);
			map.put("message", "친구 요청 등록 성공");
		} catch (RuntimeException e) {
			map.put("message", "친구 요청 등록 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@PostMapping("/insertfriend/{memberid}/{friendid}")
	@ApiOperation(value = "친구 등록 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> insertfriend(@PathVariable("memberid") String memberid, @PathVariable("friendid") String friendid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			System.out.println("memberid:"+memberid+",friendid:"+friendid);
			int insert = msvc.friendInsert(Integer.parseInt(memberid), Integer.parseInt(friendid));
			System.out.println(insert);
			map.put("message", "친구 등록 성공");
		} catch (RuntimeException e) {
			map.put("message", "친구 등록 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@DeleteMapping("/deletefriendwait/{requestid}/{responseid}")
	@ApiOperation(value = "친구 요청 삭제 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> deletefriendwait(@PathVariable("requestid") String requestid, @PathVariable("responseid") String responseid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			System.out.println("requestid:"+requestid+",responseid:"+responseid);
			int delete = msvc.friendWaitDelete(Integer.parseInt(requestid), Integer.parseInt(responseid));
			map.put("message", "친구 요청 삭제 성공");
		} catch (RuntimeException e) {
			map.put("message", "친구 요청 삭제 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@DeleteMapping("/deletefriend/{memberid}/{friendid}")
	@ApiOperation(value = "친구 삭제 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> deletefriend(@PathVariable("memberid") String memberid, @PathVariable("friendid") String friendid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			System.out.println("memberid:"+memberid+",friendid:"+friendid);
			int delete = msvc.friendDelete(Integer.parseInt(memberid), Integer.parseInt(friendid));
			map.put("message", "친구 삭제 성공");
		} catch (RuntimeException e) {
			map.put("message", "친구 삭제 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
}
