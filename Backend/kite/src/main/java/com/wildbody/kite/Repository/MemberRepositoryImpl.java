package com.wildbody.kite.Repository;

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
		System.out.println("repository====================================");
		int ret = 0;
		try {
			session.insert("kite.member.scrap", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
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