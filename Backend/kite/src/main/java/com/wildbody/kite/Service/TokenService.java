package com.wildbody.kite.Service;

import com.wildbody.kite.Dto.Member;
import com.wildbody.kite.Dto.Token;

public interface TokenService {

    Token select(Member member);

    int insert(Token token);

    void update(Token token);
}
