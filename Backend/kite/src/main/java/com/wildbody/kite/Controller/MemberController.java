package com.wildbody.kite.Controller;

import com.wildbody.kite.Dto.Member;
import com.wildbody.kite.Service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/mem/")
public class MemberController {

  @Autowired
  private IMemberService svc;

  @PostMapping("signup")
  public void Sign_Up(@RequestBody Member mem) {
    // TODO: 2020-01-17
  }

  @GetMapping("")
  public Member select(@RequestBody Member mem) {
    // TODO: 2020-01-17
    return null;
  }
}
