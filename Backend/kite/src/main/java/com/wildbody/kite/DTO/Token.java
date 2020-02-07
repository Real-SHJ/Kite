package com.wildbody.kite.DTO;

import lombok.Data;

@Data
public class Token {

    private int memberid;
    private String email;
    private String refreshToken;
}
