package com.wildbody.kite.Service;

import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.Token;

public interface TokenService {

	public Token select(Member member);

	public int insert(Token token);

	public void update(Token token);
}
