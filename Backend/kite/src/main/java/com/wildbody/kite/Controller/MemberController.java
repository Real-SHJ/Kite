package com.wildbody.kite.Controller;

import com.wildbody.kite.Dto.Member;
import com.wildbody.kite.Dto.NaverMember;
import com.wildbody.kite.Service.MemberService;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    MemberService mSer;

    @PostMapping("/register")
    @ApiOperation(value = "member 등록 서비스")
    private @ResponseBody
    ResponseEntity<Map<String, Object>> registerMember(@RequestBody Member dto) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            int insert = mSer
                .memberInsert(dto);
            Map<String, Object> map = new HashMap<>();
            map.put("resvalue", insert);
            map.put("message", "회원 가입 성공");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "회원 가입 실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @PutMapping("/update")
    @ApiOperation(value = "member 수정 서비스")
    private @ResponseBody
    ResponseEntity<Map<String, Object>> updateMember(@RequestBody Member dto) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            int update = mSer
                .memberUpdate(dto);
            Map<String, Object> map = new HashMap<>();
            map.put("resvalue", update);
            map.put("message", "회원정보 수정 성공");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "회원정보 수정 실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "id를 받아 member 삭제 서비스")
    private ResponseEntity<Map<String, Object>> deleteMember(@PathVariable("id") String id) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            int delete = mSer.memberDelete(id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "회원 탈퇴 성공");
            map.put("resvalue", delete);
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "회원 탈퇴 실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "id를 받아 member 조회 서비스", response = Member.class)
    private ResponseEntity<Map<String, Object>> infoMember(@PathVariable("id") String id) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        Member mem = null;
        try {
            mem = mSer.memberInfo(id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "내 정보 조회 성공");
            map.put("mem", mem);
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "내 정보 조회 실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @GetMapping("/list")
    @ApiOperation("회원 목록 조회 서비스")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> listMember() {
        ResponseEntity<Map<String, Object>> resEntity = null;
        List<Member> list = null;
        try {
            list = mSer.memberList();
            System.out.println(list);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "회원 목록 조회 성공");
            map.put("resvalue", list);
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "회원 목록 조회 실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @PostMapping("/login")
    @ApiOperation("로그인")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> login(@RequestBody Member member) {
        ResponseEntity<Map<String, Object>> ret = null;
        boolean isLogin = false;
        Member logintMem = mSer.login(member);
        Map<String, Object> map = new HashMap<>();

        if (logintMem != null) {
//            로그인이 되었을 때
            map.put("isLogin", isLogin);
        } else {
//            로그인이 되지 않았을 때
            map.put("isLogin", isLogin);
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
            map.put("isAuth",isAuth);
            ret = new ResponseEntity<Map<String, Object>>(map, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return ret;
    }

    @PostMapping("/kakaologin")
    public @ResponseBody
    ResponseEntity<Map<String,Object>> kakaoLogin(){
        ResponseEntity<Map<String,Object>> ret=null;

        return ret;
    }
}
