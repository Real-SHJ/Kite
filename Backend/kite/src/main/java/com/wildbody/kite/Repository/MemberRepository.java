package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.Member;
import java.util.List;

public interface MemberRepository {

  int memberInsert(Member member);

  int memberUpdate(Member member);

  int memberDelete(Member member);

  Member memberInfo(Member member);

  List<Member> memberList();

  Member login(Member member);

  int scrapArticle(Member member, String articles);

  String getMyScrap(Member member);

  List<Integer> friendList(int memberid);

  List<Integer> requestList(int memberid);

  List<Integer> responseList(int memberid);
}
