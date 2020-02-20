package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.ArticleKeyword;
import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.MemberArticle;
import com.wildbody.kite.DTO.MemberKeyword;
import com.wildbody.kite.DTO.Message;
import com.wildbody.kite.DTO.MyArticle;

import java.util.List;

public interface MemberRepository {

	public int memberInsert(Member member);

	public int memberUpdate(Member member);

	public int memberDelete(Member member);

	public Member memberInfo(Member member);
	
	public Member memberid(int id);
	
	public int deleteMessage(int id);

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

	public List<Message> messageList(int memberid);

	public int updateCompany(int memberid, String companylist);

	public String selectArticleCompany(int articleid);
	
	public String selectArticleContent(int articleid);

	public int insertMemberArticle(MemberArticle ma);

	public List<Article> selectMyArticleList(int memberid);

	public List<Article> selectMyCompanyArticleList(MemberArticle ma);
	
	public List<MyArticle> selectMyContentList(int memberid);
	
	public List<MyArticle> selectMyCompanyContentList(MemberArticle ma);

	public int deleteMyArticle(MemberArticle ma);
	
	public int saveContent(MemberArticle ma);
	
	public int insertMemberKeyword(List<MemberKeyword> list);
	
	public int deleteMemberKeyword(int memberid, int articleid);
	
	public List<MemberKeyword> selectMemberKeywordList(int memberid);
	
	public List<ArticleKeyword> selectArticleKeywordList(String company);
	
	public List<Integer> getArticleid(int memberid);
}
