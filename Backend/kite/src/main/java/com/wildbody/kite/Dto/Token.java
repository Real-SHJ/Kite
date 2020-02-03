package com.wildbody.kite.Dto;

import lombok.Data;

@Data
public class Token {
    private int memberid;
    private String email;
    private String refreshToken;
}
