package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Friend;
import com.wildbody.kite.DTO.Member;
import java.util.List;

public interface MemberRepository {

    public int memberInsert(Member member);

    public int memberUpdate(Member member);

    public int memberDelete(Member member);

    public Member memberInfo(Member member);

    public List<Member> memberList();

    public Member login(Member member);
    
    public List<Friend> friendList(int memberid);

}
