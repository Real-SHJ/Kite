package com.wildbody.kite.Repository;

import com.wildbody.kite.Dto.Member;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("MemberRepositoryImpl")
public class MemberRepositoryImpl implements MemberRepository {

    @Autowired
    SqlSession session;

    public MemberRepositoryImpl() {
        super();
    }

    @Override
    public int memberInsert(Member m) {
        return session.insert("kite.member.insert", m);
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
    public Member memberInfo(int id) {
        Member dto = session.selectOne("kite.member.selectOne", id);
        return dto;
    }

    @Override
    public List<Member> memberList() {
        List<Member> list = session.selectList("kite.member.selectList");
        return list;
    }
}
