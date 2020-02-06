package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Friend;
import com.wildbody.kite.DTO.Member;
import java.util.List;
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
        System.out.println(member.toString());
        member = session.selectOne("kite.member.selectOne", member);
        try{
            System.out.println(member.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public List<Member> memberList() {
        return session.selectList("kite.member.selectList");
    }

    @Override
    public List<Friend> friendList(int memberid) {
        return session.selectList("kite.member.selectFriendList", memberid);
    }

    @Override
    public Member login(Member member) {
        return session.selectOne("kite.member.selectOne", member);
    }

}
