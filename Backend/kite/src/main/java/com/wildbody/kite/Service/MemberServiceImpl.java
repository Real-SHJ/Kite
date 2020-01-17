package com.wildbody.kite.Service;

import com.wildbody.kite.Repository.IMemberRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberServiceImpl implements IMemberService {

  @Autowired private IMemberRepo repo;
}
