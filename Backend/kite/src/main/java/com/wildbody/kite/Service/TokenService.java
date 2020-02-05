package com.wildbody.kite.Service;

import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.Token;

public interface TokenService {

    Token select(Member member);

    int insert(Token token);

    void update(Token token);
}
