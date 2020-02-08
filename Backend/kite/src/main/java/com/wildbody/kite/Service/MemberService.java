package com.wildbody.kite.Service;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.Member;
import java.util.List;

public interface MemberService {

	public int memberInsert(Member member);

	public int memberUpdate(Member member);

	public int memberDelete(Member member);

	public Member memberInfo(Member member);

	public List<Member> memberList();

	public Member login(Member member);

	public int scrapArticle(Member member, Article article);
    
    public List<Integer> friendList(int memberid);

	public List<Integer> requestList(int memberid);

	public List<Integer> responseList(int memberid);

}
