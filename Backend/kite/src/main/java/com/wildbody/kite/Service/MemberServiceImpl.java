package com.wildbody.kite.Service;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.ArticleKeyword;
import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.MemberArticle;
import com.wildbody.kite.DTO.MemberKeyword;
import com.wildbody.kite.DTO.Message;
import com.wildbody.kite.Repository.MemberRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.Token;

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
		int delete = repo.friendDelete(memberid, friendid);
		delete += repo.friendDelete(friendid, memberid);
		return delete;
	}

	@Override
	public int messageInsert(Message m) {
		// TODO Auto-generated method stub
		return repo.messageInsert(m);
	}

	@Override
	public List<Message> messageList(int memberid) {
		// TODO Auto-generated method stub
		return repo.messageList(memberid);
	}

	public int updateCompany(int memberid, String companylist) {
		return repo.updateCompany(memberid, companylist);
	}

	@Override
	public int insertMemberArticle(MemberArticle ma) {
		return repo.insertMemberArticle(ma);
	}

	@Override
	public List<Article> selectMyArticleList(int memberid) {
		return repo.selectMyArticleList(memberid);
	}

	@Override
	public List<Article> selectMyCompanyArticleList(MemberArticle ma) {
		return repo.selectMyCompanyArticleList(ma);
	}

	@Override
	public int deleteMyArticle(MemberArticle ma) {
		return repo.deleteMyArticle(ma);
	}

	@Override
	public int saveContent(MemberArticle ma) {
		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
		List<String> list = new ArrayList<>();
		String content = ma.getContent();
		int temp = 0, start = 0, end = 0;
		int str = "\">".length();
		int str2 = "</span>".length();
		while (true) {
			content = content.substring(temp);
			start = content.indexOf("\">") + str;
			end = content.indexOf("</span>");
			if (end - start < 0)
				break;
			String parseText = content.substring(start, end);
			list.add(parseText);
			temp = end + str2;
		}
		Map<String, Integer> map = new HashMap<>();
		List<Token> select = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			List<Token> tokens = komoran.analyze(list.get(i)).getTokenList();
			for (Token token : tokens) {
				if (token.getPos().equals("NNP") || token.getPos().equals("NNG") || token.getPos().equals("SL")) {
//					System.out.println(token);
					select.add(token);
				}
			}
		}
		for (Token token : select) {
			Integer freq = map.get(token.getMorph());
			map.put(token.getMorph(), (freq == null) ? 1 : freq + 1);
		}

		List<MemberKeyword> mklist = new ArrayList<>();
		for (String key : map.keySet()) {
			Integer value = map.get(key);
//			System.out.println(key + " : " + value);
			MemberKeyword mk = new MemberKeyword();
			mk.setMemberid(ma.getMemberid());
			mk.setArticleid(ma.getArticleid());
			mk.setKeyword(key);
			mk.setCount(value);
			mklist.add(mk);
		}
		int delete = repo.deleteMemberKeyword(ma.getMemberid(), ma.getArticleid());
//		System.out.println("삭제 몇개됐노?:" + delete);
		int insert = repo.insertMemberKeyword(mklist);
//		System.out.println("키워드 몇개들어갔노?:" + insert);
		return repo.saveContent(ma);
	}

	@Override
	public Member selectid(int id) {
		return repo.memberid(id);
	}

	@Override
	public int deleteMessage(int id) {
		return repo.deleteMessage(id);
	}
	
	@Override
	public Map<String, Integer> selectMemberKeywordList(int memberid) {
		Map<String, Integer> map = new HashMap<>();
		List<MemberKeyword> select = repo.selectMemberKeywordList(memberid);
		List<MemberKeyword> result = new ArrayList<>();
		for (MemberKeyword item : select) {
			String keyword = item.getKeyword();
			int count = item.getCount();
			Integer freq = map.get(keyword);
			map.put(keyword, (freq == null) ? count : freq + count);
		}
		return map;
	}

	@Override
	public Map<String, Integer> selectArticleKeywordList(String company) {
		Map<String, Integer> map = new HashMap<>();
		List<ArticleKeyword> select = repo.selectArticleKeywordList(company);
		List<ArticleKeyword> result = new ArrayList<>();
		for (ArticleKeyword item : select) {
			String keyword = item.getKeyword();
			int count = item.getCount();
			Integer freq = map.get(keyword);
			map.put(keyword, (freq == null) ? count : freq + count);
		}
		return map;
	}
	
	@Override
	public List<Integer> getArticleid(int memberid) {
		return repo.getArticleid(memberid);
	}
}
