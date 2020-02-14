package com.wildbody.kite.Service;

import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.Token;
import com.wildbody.kite.Repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenRepository repo;

	@Override
	public Token select(Member member) {
		return repo.select(member);
	}

	@Override
	public int insert(Token token) {
		return repo.insert(token);
	}

	@Override
	public void update(Token token) {
		repo.update(token);
	}
}
