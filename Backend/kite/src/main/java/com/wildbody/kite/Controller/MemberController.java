package com.wildbody.kite.Controller;

import com.wildbody.kite.Dto.Member;
import com.wildbody.kite.Service.MemberService;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    MemberService mSer;

    @PostMapping("/registermem")
    @ApiOperation(value = "member 등록 서비스")
    private @ResponseBody
    ResponseEntity<Map<String, Object>> registerMember(@RequestBody Member dto) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            int insert = mSer
                .memberInsert(dto.getEmail(), dto.getPw(), dto.getLastname(), dto.getFirstname(),
                    dto.getBirthday(), dto.getGender(), dto.getArea(), dto.getJob(),
                    dto.getCompany(), dto.getImage());
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

    @PutMapping("/updatemem")
    @ApiOperation(value = "member 수정 서비스")
    private @ResponseBody
    ResponseEntity<Map<String, Object>> updateMember(@RequestBody Member dto) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            int update = mSer
                .memberUpdate(dto.getMemberId(), dto.getEmail(), dto.getPw(), dto.getLastname(),
                    dto.getFirstname(), dto.getBirthday(), dto.getGender(), dto.getArea(),
                    dto.getJob(),
                    dto.getCompany(), dto.getImage(), dto.getArticleList(), dto.getKeywordList());
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

    @PostMapping("/deletemem/{id}")
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

    @GetMapping("/infomem/{id}")
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

    @GetMapping("/listmem")
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

}
