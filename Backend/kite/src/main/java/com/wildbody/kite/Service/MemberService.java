package com.wildbody.kite.Service;

import java.util.List;

import com.wildbody.kite.Dto.Article;
import com.wildbody.kite.Dto.Member;

public interface MemberService {
	public int memberInsert(String email, String pw, String lastname, String firstname, String birthday,
			int gender, String area, String job, String company, String image);

	public int memberUpdate(int id, String email, String pw, String lastname, String firstname, String birthday,
			int gender, String area, String job, String company, String image, List<Article> articleList,
			List<String> keywordList);

	public int memberDelete(String id);

	public Member memberInfo(String id);

	public List<Member> memberList();

}
