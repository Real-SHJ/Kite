package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.Message;

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
		System.out.println("req:" + requestid + ",res:" + responseid);
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
		System.out.println("repo test: mid:" + memberid + ",fid:" + friendid);
		Map<String, Object> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("friendid", friendid);
		int insert = session.insert("kite.member.insertFriend", map);
		map = new HashMap<>();
		map.put("memberid", friendid);
		map.put("friendid", memberid);
		insert += session.insert("kite.member.insertFriend", map);
		System.out.println("repo test:" + insert);
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
		System.out.println("repo"+memberid + "," + companylist);
		Map<String, Object> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("companylist", companylist);
		return session.update("kite.member.updateCompany", map);
	}

	

}
