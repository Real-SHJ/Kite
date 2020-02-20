package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.ArticleKeyword;
import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.MemberArticle;
import com.wildbody.kite.DTO.MemberKeyword;
import com.wildbody.kite.DTO.Message;
import com.wildbody.kite.DTO.MyArticle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

	@Autowired
	private SqlSession session;

	@Override
	public int memberInsert(Member member) {
		return session.insert("kite.member.insert", member);
	}

	@Override
	public int memberUpdate(Member member) {
		return session.update("kite.member.update", member);
	}

	@Override
	public int memberDelete(Member member) {
		return session.delete("kite.member.delete", member);
	}

	@Override
	public Member memberInfo(Member member) {
		return session.selectOne("kite.member.selectOne", member);
	}

	@Override
	public List<Member> memberList() {
		return session.selectList("kite.member.selectList");
	}

	@Override
	public int scrapArticle(Member member, String articles) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberid", member.getMemberid());
		map.put("articles", articles);
		int cnt = session.selectOne("kite.member.scrapcounter", member);
		if (cnt > 0) {
			return session.update("kite.member.scrapUpdate", map);
		} else {
			return session.insert("kite.member.scrapInsert", map);
		}
	}

	@Override
	public String getMyScrap(Member member) {
		return session.selectOne("kite.member.getscrap", member);
	}

	@Override
	public Member login(Member member) {
		return session.selectOne("kite.member.selectOne", member);
	}

	@Override
	public List<Member> myMemberList(int memberid) {
		return session.selectList("kite.member.selectMyMemberList", memberid);
	}

	@Override
	public List<Member> friendList(int memberid) {
		return session.selectList("kite.member.selectFriendList", memberid);
	}

	@Override
	public List<Member> requestList(int memberid) {
		return session.selectList("kite.member.selectRequestList", memberid);
	}

	@Override
	public List<Member> responseList(int memberid) {
		return session.selectList("kite.member.selectResponseList", memberid);
	}

	@Override
	public List<Member> noRelationList(int memberid) {
		return session.selectList("kite.member.selectNoRelationList", memberid);
	}

	@Override
	public int friendWaitInsert(int requestid, int responseid) {
		Map<String, Object> map = new HashMap<>();
		map.put("requestid", requestid);
		map.put("responseid", responseid);
		return session.insert("kite.member.insertFriendWait", map);
	}

	@Override
	public int friendWaitDelete(int requestid, int responseid) {
		Map<String, Object> map = new HashMap<>();
		map.put("requestid", requestid);
		map.put("responseid", responseid);
		return session.delete("kite.member.deleteFriendWait", map);
	}

	@Override
	public int friendInsert(int memberid, int friendid) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("friendid", friendid);
		int insert = session.insert("kite.member.insertFriend", map);
		map = new HashMap<>();
		map.put("memberid", friendid);
		map.put("friendid", memberid);
		insert += session.insert("kite.member.insertFriend", map);
		return insert;
	}

	@Override
	public int friendDelete(int memberid, int friendid) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("friendid", friendid);
		return session.delete("kite.member.deleteFriend", map);
	}

	// ------------------------------메세지-----------------------
	@Override
	public int messageInsert(Message m) {
		return session.insert("kite.member.insertMessage", m);
	}

	@Override
	public List<Message> messageList(int memberid) {
		// TODO Auto-generated method stub
		return session.selectList("kite.member.selectMessage", memberid);
	}

	@Override
	public int updateCompany(int memberid, String companylist) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("companylist", companylist);
		return session.update("kite.member.updateCompany", map);
	}

	@Override
	public String selectArticleCompany(int articleid) {
		return session.selectOne("kite.member.selectArticleCompany", articleid);
	}

	@Override
	public String selectArticleContent(int articleid) {
		return session.selectOne("kite.member.selectArticleContent", articleid);
	}

	@Override
	public int insertMemberArticle(MemberArticle ma) {
		ma.setCompany(selectArticleCompany(ma.getArticleid()));
		ma.setContent(selectArticleContent(ma.getArticleid()));
		return session.insert("kite.member.insertMemberArticle", ma);
	}

	@Override
	public List<Article> selectMyArticleList(int memberid) {
		List<Article> articlelist = session.selectList("kite.member.selectMyArticleList", memberid);
		List<MyArticle> mylist = selectMyContentList(memberid);
		for (int i = 0; i < articlelist.size(); i++) {
			for (int j = 0; j < mylist.size(); j++) {
				if (articlelist.get(i).getArticleid() == mylist.get(j).getArticleid()) {
					articlelist.get(i).setContent(mylist.get(j).getContent());
				}
			}
		}
		return articlelist;
	}

	@Override
	public List<Article> selectMyCompanyArticleList(MemberArticle ma) {
		List<Article> articlelist = session.selectList("kite.member.selectMyCompanyArticleList", ma);
		List<MyArticle> mylist = selectMyCompanyContentList(ma);
		for (int i = 0; i < articlelist.size(); i++) {
			for (int j = 0; j < mylist.size(); j++) {
				if (articlelist.get(i).getArticleid() == mylist.get(j).getArticleid()) {
					articlelist.get(i).setContent(mylist.get(j).getContent());
				}
			}
		}
		return articlelist;
	}

	@Override
	public List<MyArticle> selectMyContentList(int memberid) {
		return session.selectList("kite.member.selectMyContentList", memberid);
	}

	@Override
	public List<MyArticle> selectMyCompanyContentList(MemberArticle ma) {
		return session.selectList("kite.member.selectMyCompanyContentList", ma);
	}

	@Override
	public int deleteMyArticle(MemberArticle ma) {
		return session.delete("kite.member.deleteMyArticle", ma);
	}

	@Override
	public int saveContent(MemberArticle ma) {
		return session.update("kite.member.saveContent", ma);
	}

	@Override
	public Member memberid(int id) {
		// TODO Auto-generated method stub
		return session.selectOne("kite.member.selectid", id);
	}

	@Override
	public int deleteMessage(int id) {
		// TODO Auto-generated method stub
		return session.delete("kite.member.deleteMessage", id);
	}

	@Override
	public int insertMemberKeyword(List<MemberKeyword> list) {
		return session.insert("kite.keyword.insertMemberKeyword", list);
	}

	@Override
	public int deleteMemberKeyword(int memberid, int articleid) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("articleid", articleid);
		return session.delete("kite.keyword.deleteMemberKeyword", map);
	}
	
	@Override
	public List<MemberKeyword> selectMemberKeywordList(int memberid) {
		return session.selectList("kite.keyword.selectMemberKeyword", memberid);
	}

	@Override
	public List<ArticleKeyword> selectArticleKeywordList(String company) {
		return session.selectList("kite.keyword.selectArticleKeyword", company);
	}
	
	@Override
	public List<Integer> getArticleid(int memberid) {
		return session.selectList("kite.member.getArticleid", memberid);
	}
}
