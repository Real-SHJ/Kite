package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.Message;

import java.util.List;

public interface MemberRepository {

	public int memberInsert(Member member);

	public int memberUpdate(Member member);

	public int memberDelete(Member member);

	public Member memberInfo(Member member);

	public List<Member> memberList();

	public Member login(Member member);

	public int scrapArticle(Member member, String articles);

	public String getMyScrap(Member member);

	public List<Member> myMemberList(int memberid);

	public List<Member> friendList(int memberid);

	public List<Member> requestList(int memberid);

	public List<Member> responseList(int memberid);

	public List<Member> noRelationList(int memberid);
	
	public int friendWaitInsert(int requestid, int responseid);
	
	public int friendWaitDelete(int requestid, int responseid);
	
	public int friendInsert(int memberid, int friendid);
	
	public int friendDelete(int memberid, int friendid);

	public int messageInsert(Message m);

	public int updateCompany(int memberid, String companylist);
}
