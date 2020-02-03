package com.wildbody.kite.Service;

import java.util.List;

import com.wildbody.kite.Dto.Article;
import com.wildbody.kite.Dto.Member;

public interface MemberService {
	public int memberInsert(Member member);

	public int memberUpdate(Member member);

	public int memberDelete(String id);

	public Member memberInfo(Member member);

	public List<Member> memberList();

	public Member login(Member member);

}
