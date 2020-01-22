package com.wildbody.kite.Repository;

import com.wildbody.kite.Dto.Member;
import java.util.List;

public interface MemberRepository {

    public int memberInsert(Member m);

    public int memberUpdate(Member m);

    public int memberDelete(int memberid);

    public Member memberInfo(int memberid);

    public List<Member> memberList();

}
