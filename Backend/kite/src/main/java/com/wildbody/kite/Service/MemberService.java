package com.wildbody.kite.Service;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.Member;
import java.util.List;

public interface MemberService {

	int memberInsert(Member member);

	int memberUpdate(Member member);

	int memberDelete(Member member);

	Member memberInfo(Member member);

	List<Member> memberList();

	Member login(Member member);

	List<Integer> friendList(int memberid);

	int scrapArticle(Member member, String articles);

	List<Integer> requestList(int memberid);

	List<Integer> responseList(int memberid);

	int delScrap();

	String getMyScrap(Member member);
}
