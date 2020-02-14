package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.Token;

public interface TokenRepository {

	public Token select(Member member);

	public int insert(Token token);

	public void update(Token token);
}
