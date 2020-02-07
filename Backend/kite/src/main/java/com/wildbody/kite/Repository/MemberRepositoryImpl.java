package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Friend;
import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.Member;
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
	public int scrapArticle(Member member, Article article) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberid", member.getMemberid());
		map.put("articleid", article.getArticleid());
		map.put("keyword", article.getKeyword());
		int ret = 0;
		try {
			session.insert("kite.member.scrap", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
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
	public List<Integer> friendList(int memberid) {
		return session.selectList("kite.member.selectFriendList", memberid);
	}

	@Override
	public List<Integer> requestList(int memberid) {
		return session.selectList("kite.member.selectRequestList", memberid);
	}

	@Override
	public List<Integer> responseList(int memberid) {
		return session.selectList("kite.member.selectResponseList", memberid);
	}
}