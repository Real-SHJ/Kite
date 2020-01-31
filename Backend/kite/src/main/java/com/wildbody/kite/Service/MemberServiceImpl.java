package com.wildbody.kite.Service;

import com.wildbody.kite.Dto.Member;
import com.wildbody.kite.Repository.MemberRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MemberServiceImpl")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repo;

    @Override
    public int memberInsert(Member member) {
        return repo.memberInsert(member);
    }

    @Override
    public int memberUpdate(Member member) {
        return repo.memberUpdate(member);
    }

    @Override
    public int memberDelete(String id) {
        return repo.memberDelete(Integer.parseInt(id));
    }

    @Override
    public Member memberInfo(String id) {
        return repo.memberInfo(Integer.parseInt(id));
    }

    @Override
    public List<Member> memberList() {
        return repo.memberList();
    }

    @Override
    public Member login(Member member) {
        return repo.login(member);
    }

}
