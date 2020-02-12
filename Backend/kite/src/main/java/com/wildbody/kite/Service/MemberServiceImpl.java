package com.wildbody.kite.Service;

import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.Repository.MemberRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository repo;

	@Override
	public int memberInsert(Member member) {
		return repo.memberInsert(member);
	}

	@Override
	public int memberUpdate(Member member) {
		return repo.memberUpdate(member);
	}

	@Override
	public int memberDelete(Member member) {
		return repo.memberDelete(member);
	}

	@Override
	public Member memberInfo(Member member) {
		return repo.memberInfo(member);
	}

	@Override
	public List<Member> memberList() {
		return repo.memberList();
	}

	@Override
	public Member login(Member member) {
		return repo.login(member);
	}

	@Override
	public int scrapArticle(Member member, String articles) {
		return repo.scrapArticle(member, articles);
	}

	@Override
	public int delScrap() {
		// TODO
		return 0;
	}

	@Override
	public String getMyScrap(Member member) {
		return repo.getMyScrap(member);
	}

	@Override
	public List<Member> myMemberList(int memberid) {
		return repo.myMemberList(memberid);
	}

	@Override
	public List<Member> friendList(int memberid) {
		return repo.friendList(memberid);
	}

	@Override
	public List<Member> requestList(int memberid) {
		return repo.requestList(memberid);
	}

	@Override
	public List<Member> responseList(int memberid) {
		return repo.responseList(memberid);
	}

	@Override
	public List<Member> noRelationList(int memberid) {
		return repo.noRelationList(memberid);
	}

	@Override
	public int friendWaitInsert(int requestid, int responseid) {
		return repo.friendWaitInsert(requestid, responseid);
	}

	@Override
	public int friendWaitDelete(int requestid, int responseid) {
		return repo.friendWaitDelete(requestid, responseid);
	}

	@Override
	public int friendInsert(int memberid, int friendid) {
		return repo.friendInsert(memberid, friendid);
	}

	@Override
	public int friendDelete(int memberid, int friendid) {
		return repo.friendDelete(memberid, friendid);
	}

	@Override
	public int updateCompany(int memberid, String companylist) {
		return repo.updateCompany(memberid,companylist);
	}
}
