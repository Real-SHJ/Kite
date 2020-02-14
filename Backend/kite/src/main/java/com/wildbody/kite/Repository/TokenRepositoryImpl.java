package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.Token;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepositoryImpl implements TokenRepository {

	@Autowired
	private SqlSession session;

	@Override
	public Token select(Member member) {
		return session.selectOne("kite.token.select", member);
	}

	@Override
	public int insert(Token token) {
		return session.insert("kite.token.insert", token);
	}

	@Override
	public void update(Token token) {
		session.update("kite.token.update", token);
	}
}
