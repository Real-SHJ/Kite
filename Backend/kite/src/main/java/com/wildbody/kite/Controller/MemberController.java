package com.wildbody.kite.Controller;

import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.NaverMember;
import com.wildbody.kite.DTO.Token;
import com.wildbody.kite.JWT.JwtService;
import com.wildbody.kite.Service.MemberService;
import com.wildbody.kite.Service.TokenService;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/signup")
    @ApiOperation(value = "member 등록 서비스")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> registerMember(Member member,
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
        resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        return resEntity;
    }

    @PutMapping("/update")
    @ApiOperation(value = "member 수정 서비스")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> updateMember(Member member) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        Map<String, Object> map = new HashMap<>();
        try {
            int update = msvc.memberUpdate(member);
            map.put("result", update);
            map.put("isupdate", true);
        } catch (RuntimeException e) {
            map.put("isupdate", "회원정보 수정 실패");
        }
        resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        return resEntity;
    }

    @PostMapping("/delete")
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
    public @ResponseBody
    ResponseEntity<Map<String, Object>> infoMember(Member member) {
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
        resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        return resEntity;
    }

    @GetMapping("/list")
    @ApiOperation("회원 목록 조회 서비스")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> listMember() {
        ResponseEntity<Map<String, Object>> resEntity = null;
        List<Member> list = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("message", "회원 목록 조회 성공");
            map.put("result", msvc.memberList());
        } catch (RuntimeException e) {
            map.put("message", "회원 목록 조회 실패");
        }
        resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        return resEntity;
    }

    @PostMapping("/signin")
    @ApiOperation("로그인")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> login(Member member, HttpServletResponse response) {
        ResponseEntity<Map<String, Object>> ret = null;
        Member loginMem = msvc.login(member);
        Map<String, Object> map = new HashMap<>();

        if (loginMem != null) {
            map.put("access_token", response.getHeader("Authorization"));
            map.put("isLogin", true);
        } else {
            map.put("isLogin", false);
        }
        ret = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        return ret;
    }

    @GetMapping("/naverlogin")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> naverLogin(NaverMember naverMember) {
        ResponseEntity<Map<String, Object>> ret = null;
        Map<String, Object> map = new HashMap<>();

        // naver에 정보 요청

        ret = new ResponseEntity<>(map, HttpStatus.OK);
        return ret;
    }

    @PostMapping("/kakaologin")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> kakaoLogin() {
        ResponseEntity<Map<String, Object>> ret = null;

        return ret;
    }
}
