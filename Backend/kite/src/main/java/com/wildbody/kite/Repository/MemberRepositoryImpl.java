package com.wildbody.kite.Repository;

import com.wildbody.kite.Dto.Member;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("MemberRepositoryImpl")
public class MemberRepositoryImpl implements MemberRepository {

    @Autowired
    private SqlSession session;

    @Override
    public int memberInsert(Member member) {
        return session.insert("kite.member.insert", member);
    }

    @Override
    public int memberUpdate(Member m) {
        return session.update("kite.member.update", m);
    }

    @Override
    public int memberDelete(int id) {
        return session.delete("kite.member.delete", id);
    }

    @Override
    public Member memberInfo(Member member) {
        Member dto = null;
        try {
            dto = session.selectOne("kite.member.selectOne", member);
            System.out.println(dto.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("에러발");
        }

        return dto;
    }

    @Override
    public List<Member> memberList() {
        List<Member> list = session.selectList("kite.member.selectList");
        return list;
    }

    @Override
    public Member login(Member member) {
        System.out.println(member.toString());
        Member mm = null;
        try {
            mm = session.selectOne("kite.member.login", member);
            System.out.println(mm.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mm;
    }

}
