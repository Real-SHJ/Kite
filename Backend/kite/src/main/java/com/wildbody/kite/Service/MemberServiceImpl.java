package com.wildbody.kite.Service;

import com.wildbody.kite.Dto.Article;
import com.wildbody.kite.Dto.Member;
import com.wildbody.kite.Repository.MemberRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MemberServiceImpl")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repo;

    public MemberServiceImpl() {
        super();
    }

    @Override
    public int memberInsert(String email, String pw, String lastname, String firstname,
        String birthday,
        int gender, String area, String job, String company, String image) {
        return repo.memberInsert(
            new Member(0, email, pw, lastname, firstname, birthday, gender, area, job, company,
                image, null, null));
    }

    @Override
    public int memberUpdate(int id, String email, String pw, String lastname, String firstname,
        String birthday,
        int gender, String area, String job, String company, String image,
        List<Article> articleList,
        List<String> keywordList) {
        return repo.memberUpdate(
            new Member(id, email, pw, lastname, firstname, birthday, gender, area, job, company,
                image, articleList, keywordList));
    }

    @Override
    public int memberDelete(String id) {
        return repo.memberDelete(Integer.parseInt(id));
    }

    @Override
    public Member memberInfo(String id) {
        return repo.memberInfo(Integer.parseInt(id));
    }

    @Override
    public List<Member> memberList() {
        return repo.memberList();
    }
}
