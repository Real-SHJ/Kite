package com.wildbody.kite.Repository;

import com.wildbody.kite.Dto.Member;
import com.wildbody.kite.Dto.Token;

public interface TokenRepository {
    Token select(Member member);

    int insert(Token token);

    void update(Token token);
}
