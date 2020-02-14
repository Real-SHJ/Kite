package com.wildbody.kite.JWT;

import com.wildbody.kite.DTO.Member;

public interface JwtService {

	String getAccessToken(Member member);

	String getRefreshToken(Member member);

	byte[] generateKey(String salt);

	boolean validateRefreshToken(String jwt);

	boolean validateToken(String jwt);

	boolean isExpiration(String jwt);

	boolean isExpirationRefresh(String jwt);
}