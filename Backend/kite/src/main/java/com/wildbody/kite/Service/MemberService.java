package com.wildbody.kite.Service;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.MemberArticle;
import com.wildbody.kite.DTO.MemberKeyword;
import com.wildbody.kite.DTO.Message;

import java.util.List;
import java.util.Map;

public interface MemberService {

	public int memberInsert(Member member);

	public int memberUpdate(Member member);

	public int memberDelete(Member member);

	public Member memberInfo(Member member);
	
	public Member selectid(int id);
	
	public int deleteMessage(int id);

	public List<Member> memberList();

	public Member login(Member member);

	public int delScrap();

	public String getMyScrap(Member member);

	public int scrapArticle(Member member, String articles);

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

	public List<Message> messageList(int memberid);
	
	public int updateCompany(int memberid, String companylist);

	public int insertMemberArticle(MemberArticle ma);

	public List<Article> selectMyArticleList(int memberid);

	public List<Article> selectMyCompanyArticleList(MemberArticle ma);

	public int deleteMyArticle(MemberArticle ma);
	
	public int saveContent(MemberArticle ma);
	
	public Map<String, Integer> selectMemberKeywordList(int memberid);
	
	public Map<String, Integer> selectArticleKeywordList(String company);
	
	public List<Integer> getArticleid(int memberid);
}
