package com.wildbody.kite.Repository;

import com.wildbody.kite.Dto.Member;
import java.util.List;

public interface MemberRepository {

    public int memberInsert(Member m);

    public int memberUpdate(Member m);

    public int memberDelete(int memberid);

    public Member memberInfo(Member member);

    public List<Member> memberList();

    public Member login(Member member);

}
