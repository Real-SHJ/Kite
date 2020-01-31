package com.wildbody.kite.Controller;

import com.wildbody.kite.Dto.Member;
import com.wildbody.kite.Dto.NaverMember;
import com.wildbody.kite.Service.MemberService;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService mSer;

    @PostMapping("/register")
    @ApiOperation(value = "member 등록 서비스")
    private @ResponseBody
    ResponseEntity<Map<String, Object>> registerMember(Member member) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        Map<String, Object> map = new HashMap<>();
        int insert = mSer.memberInsert(member);
        if (insert == 1) {
            map.put("message", "회원 가입 성공");
        } else {
            map.put("message", "회원 가입 실패");
        }
        resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        return resEntity;
    }

    @PutMapping("/update")
    @ApiOperation(value = "member 수정 서비스")
    private @ResponseBody
    ResponseEntity<Map<String, Object>> updateMember(Member member) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        Map<String, Object> map = new HashMap<>();
        try {
            int update = mSer.memberUpdate(member);
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
    private ResponseEntity<Map<String, Object>> deleteMember(Member member) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            mSer.memberDelete(member.getEmail());
            map.put("message", "회원 탈퇴 성공");
        } catch (RuntimeException e) {
            map.put("message", "회원 탈퇴 실패");
        }
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    @GetMapping("/info")
    @ApiOperation(value = "id를 받아 member 조회 서비스", response = Member.class)
    private ResponseEntity<Map<String, Object>> infoMember(Member member) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        Member mem = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            mem = mSer.memberInfo(member.getEmail());
            map.put("message", "내 정보 조회 성공");
            map.put("result", mem);
        } catch (RuntimeException e) {
            map.put("message", "내 정보 조회 실패");
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
            map.put("result", mSer.memberList());
        } catch (RuntimeException e) {
            map.put("message", "회원 목록 조회 실패");
        }
        resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        return resEntity;
    }

    @PostMapping("/login")
    @ApiOperation("로그인")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> login(Member member, HttpServletResponse response) {
        ResponseEntity<Map<String, Object>> ret = null;
        Member loginMem = mSer.login(member);
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

    @PostMapping("/naverlogin")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> naverLogin(NaverMember nMember,
        HttpServletRequest request) {
        ResponseEntity<Map<String, Object>> ret = null;
        String state = request.getHeader("nstate");
        boolean isAuth = false;
        Map<String, Object> map = new HashMap<>();

        if (state.equals(nMember.getState())) {
            // authorization
            isAuth = true;
            map.put("isAuth", isAuth);
            ret = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } else {
            map.put("isAuth", isAuth);
            ret = new ResponseEntity<Map<String, Object>>(map, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return ret;
    }

    @PostMapping("/kakaologin")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> kakaoLogin() {
        ResponseEntity<Map<String, Object>> ret = null;

        return ret;
    }
}
