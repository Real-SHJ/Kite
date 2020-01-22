package com.wildbody.kite.Service;

import com.wildbody.kite.Repository.IMemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements IMemberService {

  @Autowired private IMemberRepo repo;
}
