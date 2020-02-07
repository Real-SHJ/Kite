package com.wildbody.kite.Service;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.Friend;
import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.Repository.MemberRepository;
import java.util.List;
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
    public List<Friend> friendList(int memberid) {
        return repo.friendList(memberid);
    }

    @Override
    public int scrapArticle(Member member, String articles) {
        return repo.scrapArticle(member,articles);
    }

    @Override
    public int delScrap() {
            return 0;
    }

    @Override
    public String getMyScrap(Member member) {
        return repo.getMyScrap(member);
    }

}
