package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.Token;

public interface TokenRepository {

    Token select(Member member);

    int insert(Token token);

    void update(Token token);
}
