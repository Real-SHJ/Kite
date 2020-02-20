package com.wildbody.kite.Controller;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.ArticleKeyword;
import com.wildbody.kite.DTO.Check;
import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.MemberArticle;
import com.wildbody.kite.DTO.MemberKeyword;
import com.wildbody.kite.DTO.Message;
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
import org.springframework.web.bind.annotation.RequestBody;

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
	public @ResponseBody ResponseEntity<Map<String, Object>> registerMember(Member member,
			HttpServletResponse response) {
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
	
	@GetMapping("/relationlist/{memberid}")
	@ApiOperation(" 회원 목록 조회 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> listRelation(@PathVariable("memberid") String memberid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Member> list = new ArrayList<>();
			map.put("message", "회원 목록 조회 성공");
			List<Check> checklist = new ArrayList<>();
			List<Member> nrlist = msvc.noRelationList(Integer.parseInt(memberid));
			List<Member> rqlist = msvc.requestList(Integer.parseInt(memberid));
			for (Member member : nrlist) {
				Check check = new Check();
				check.setMemberid(member.getMemberid());
				check.setCheck(true);
				checklist.add(check);
			}
			for (Member member : rqlist) {
				Check check = new Check();
				check.setMemberid(member.getMemberid());
				check.setCheck(false);
				checklist.add(check);
			}
			list.addAll(nrlist);
			list.addAll(rqlist);
			map.put("list", list);
			map.put("checklist", checklist);
		} catch (RuntimeException e) {
			map.put("message", "회원 목록 조회 실패");
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
			List<Member> rslist = msvc.responseList(Integer.parseInt(memberid));
			map.put("rslist", rslist);
			map.put("message", "친구 응답 목록 조회 성공");
		} catch (RuntimeException e) {
			map.put("message", "친구 목록 조회 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@GetMapping("/friendlist/{memberid}")
	@ApiOperation("친구 목록 조회 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> listFriend(@PathVariable("memberid") String memberid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Member> flist = msvc.friendList(Integer.parseInt(memberid));
			map.put("flist", flist);
			map.put("message", "친구 목록 조회 성공");
		} catch (RuntimeException e) {
			map.put("message", "친구 목록 조회 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@PostMapping("/insertfriendwait/{requestid}/{responseid}")
	@ApiOperation(value = "친구 요청 등록 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> insertfriendwait(
			@PathVariable("requestid") String requestid, @PathVariable("responseid") String responseid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			int insert = msvc.friendWaitInsert(Integer.parseInt(requestid), Integer.parseInt(responseid));
			map.put("message", "친구 요청 등록 성공");
		} catch (RuntimeException e) {
			map.put("message", "친구 요청 등록 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@PostMapping("/insertfriend/{memberid}/{friendid}")
	@ApiOperation(value = "친구 등록 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> insertfriend(@PathVariable("memberid") String memberid,
			@PathVariable("friendid") String friendid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			int insert = msvc.friendInsert(Integer.parseInt(memberid), Integer.parseInt(friendid));
			map.put("message", "친구 등록 성공");
		} catch (RuntimeException e) {
			map.put("message", "친구 등록 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@DeleteMapping("/deletefriendwait/{requestid}/{responseid}")
	@ApiOperation(value = "친구 요청 삭제 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> deletefriendwait(
			@PathVariable("requestid") String requestid, @PathVariable("responseid") String responseid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
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
	public @ResponseBody ResponseEntity<Map<String, Object>> deletefriend(@PathVariable("memberid") String memberid,
			@PathVariable("friendid") String friendid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			int delete = msvc.friendDelete(Integer.parseInt(memberid), Integer.parseInt(friendid));
			map.put("message", "친구 삭제 성공");
		} catch (RuntimeException e) {
			map.put("message", "친구 삭제 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	// ----------------아이디 2개를 받아 내 친구와 메세지 전송----------------
	// --------message 중간역할 온 데이터는 다시  
	@PostMapping("/sendmessage")
	@ApiOperation(value="친구 메시지 보내기")
	public @ResponseBody ResponseEntity<Map<String, Object>> sendmessage(Message m){
		ResponseEntity<Map<String, Object>> resEntity=null;
		Map<String, Object> map=new HashMap<>();
		try{
			List<Integer> tmp=m.getReceivelist();
			for(int i=0;i<tmp.size();i++){
				m.setReceiveid(tmp.get(i));
				int insert= msvc.messageInsert(m);
			}
			map.put("message", "메세지 저장 성공");
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
			map.put("message", "메세지 저장 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	//-------------------공유요청함 보내기-------------------------
	@GetMapping("/messagelist/{memberid}")
	@ApiOperation(value="요청목록조회")
	public @ResponseBody ResponseEntity<Map<String,Object>> listMessage(@PathVariable("memberid") String memberid){
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("message", "공유 요청 목록 조회 성공");
			List<Message> mlist = msvc.messageList(Integer.parseInt(memberid)); //여기수정
			//receiveid:내 공유함 아이디 , sendid: 다른사람이 나에게 보낸 id
			List<Integer> sno=new ArrayList<>();
			List<Member> mblist=new ArrayList<>();
			List<Article> alist=new ArrayList<>();
			for (int i = 0; i < mlist.size(); i++) {
				int sn=mlist.get(i).getSno();
				Member tmp=msvc.selectid(mlist.get(i).getSendid());
				Article atmp=asvc.oneArticle(mlist.get(i).getArticleid());
				sno.add(mlist.get(i).getSno());
				mblist.add(tmp);
				alist.add(atmp);
			}
			map.put("SNO", sno);
			map.put("memberlist", mblist);
			map.put("articlelist", alist);
		} catch (RuntimeException e) {
			map.put("message", "관계 없는 회원 목록 조회 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@PutMapping("/updatecompany/{memberid}")
	@ApiOperation(value = "관심 기업 수정 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> updateCompany(@PathVariable String memberid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int update = msvc.updateCompany(Integer.parseInt(memberid), "");
			Map<String, Object> map = new HashMap<>();
			map.put("result", update);
			map.put("message", "관심 기업 수정 성공");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<>();
			map.put("message", "관심 기업 수정 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	@PutMapping("/updatecompany/{memberid}/{companylist}")
	@ApiOperation(value = "관심 기업 수정 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> updateCompany(@PathVariable String memberid,
			@PathVariable String companylist) {
		System.out.println(memberid + "," + companylist);
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int update = msvc.updateCompany(Integer.parseInt(memberid), companylist);
			Map<String, Object> map = new HashMap<>();
			map.put("result", update);
			map.put("message", "관심 기업 수정 성공");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<>();
			map.put("message", "관심 기업 수정 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@DeleteMapping("/deleteMessage/{sno}")
	@ApiOperation(value = "내 메세지 삭제")
	public @ResponseBody ResponseEntity<Map<String, Object>> deleteMessage(@PathVariable int sno) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			int delete = msvc.deleteMessage(sno);
			map.put("resvalue", delete);
			map.put("message", "삭제 성공");
		} catch (Exception e) {
			map.put("message", "삭제 실패");
		}
		resEntity = new ResponseEntity<>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@PostMapping("/insertscrap")
	@ApiOperation(value = "내 기사 스크랩 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> insertMemberArticle(MemberArticle ma) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			int insert = msvc.insertMemberArticle(ma);
			map.put("result", insert);
			map.put("message", "스크랩 성공");
		} catch (Exception e) {
			map.put("message", "이미 스크랩한 기사입니다.");
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("/getScrap/{memberid}")
	@ApiOperation(value = "내 스크랩 기사 목록 조회 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> selectMyArticleList(@PathVariable String memberid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			List<Article> list = msvc.selectMyArticleList(Integer.parseInt(memberid));
			map.put("result", list);
			map.put("message", "기사 목록 조회 성공");
		} catch (Exception e) {
			map.put("message", "기사 목록 조회 실패");
		}
		resEntity = new ResponseEntity<>(map, HttpStatus.OK);
		return resEntity;
	}

	@GetMapping("/getScrap/{memberid}/{company}")
	@ApiOperation(value = "내 스크랩 기사 목록 조회 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> selectMyArticleList(@PathVariable String memberid, @PathVariable String company) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			MemberArticle ma = new MemberArticle();
			ma.setMemberid(Integer.parseInt(memberid));
			ma.setCompany(company);
			List<Article> list = msvc.selectMyCompanyArticleList(ma);
			map.put("result", list);
			map.put("message", "기사 목록 조회 성공");
		} catch (Exception e) {
			map.put("message", "기사 목록 조회 실패");
		}
		resEntity = new ResponseEntity<>(map, HttpStatus.OK);
		return resEntity;
	}

	@DeleteMapping("/deletescrap")
	@ApiOperation(value = "스크랩 기사 삭제 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> deleteMyArticle(MemberArticle ma) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			int delete = msvc.deleteMyArticle(ma);
			map.put("resvalue", delete);
			map.put("message", "삭제 성공");
		} catch (Exception e) {
			map.put("message", "삭제 실패");
		}
		resEntity = new ResponseEntity<>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@PutMapping("/savecontent")
	@ApiOperation(value = "스크랩 기사 내용 수정 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> saveContent(MemberArticle ma) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			int update = msvc.saveContent(ma);
			map.put("result", update);
			map.put("message", "스크랩 기사 내용 수정 성공");
		} catch (RuntimeException e) {
			map.put("message", "스크랩 기사 내용 수정 실패");
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@GetMapping("/getkeyword/{memberid}")
	@ApiOperation(value = "내 키워드 목록 조회 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> selectMemberKeywordList(@PathVariable String memberid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			Map<String, Integer> list = msvc.selectMemberKeywordList(Integer.parseInt(memberid));
			List<String> li1 = new ArrayList<String>(list.keySet());
			List<Integer> li2 = new ArrayList<Integer>(list.values());
			List<MemberKeyword> result = new ArrayList<>();
			for (int i = 0; i < li1.size(); i++) {
				MemberKeyword mk = new MemberKeyword();
				mk.setMemberid(Integer.parseInt(memberid));
				mk.setKeyword(li1.get(i));
				mk.setCount(li2.get(i));
				result.add(mk);
			}
			map.put("result", result);
			map.put("message", "내 키워드 목록 조회 성공");
		} catch (Exception e) {
			map.put("message", "내 키워드 목록 조회 실패");
		}
		resEntity = new ResponseEntity<>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/getkeywordarticle/{company}")
	@ApiOperation(value = "기업 키워드 목록 조회 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> selectArticleKeywordList(@PathVariable String company) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			Map<String, Integer> list = msvc.selectArticleKeywordList(company);
			List<String> li1 = new ArrayList<String>(list.keySet());
			List<Integer> li2 = new ArrayList<Integer>(list.values());
			List<ArticleKeyword> result = new ArrayList<>();
			for (int i = 0; i < li1.size(); i++) {
				ArticleKeyword ak = new ArticleKeyword();
				ak.setKeyword(li1.get(i));
				ak.setCount(li2.get(i));
				result.add(ak);
			}
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
			map.put("result", result);
			map.put("message", "내 키워드 목록 조회 성공");
		} catch (Exception e) {
			map.put("message", "내 키워드 목록 조회 실패");
		}
		resEntity = new ResponseEntity<>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/getarticleid/{memberid}")
	@ApiOperation(value = "내 스크랩 기사id 목록 조회 서비스")
	public @ResponseBody ResponseEntity<Map<String, Object>> getArticleid(@PathVariable String memberid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<>();
		try {
			List<Integer> list = msvc.getArticleid(Integer.parseInt(memberid));
			map.put("result", list);
			map.put("message", "내 스크랩 기사id  목록 조회 성공");
		} catch (Exception e) {
			map.put("message", "내 스크랩 기사id 목록 조회 실패");
		}
		resEntity = new ResponseEntity<>(map, HttpStatus.OK);
		return resEntity;
	}
}
