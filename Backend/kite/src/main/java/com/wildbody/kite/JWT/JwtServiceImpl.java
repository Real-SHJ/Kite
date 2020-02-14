package com.wildbody.kite.JWT;

import com.wildbody.kite.DTO.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtServiceImpl implements JwtService {

	static final String SALT = "AuthentificationSALT";
	static final String REFSALT = "RefreshAuthentificationSALT";

	@Override
	public String getAccessToken(Member member) {
		return Jwts.builder().setHeaderParam("typ", "JWT").setIssuedAt(new Date()).claim("uid", member.getMemberid())
				.claim("umail", member.getEmail())
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 2)))
				.signWith(SignatureAlgorithm.HS256, generateKey(SALT)).compact();
	}

	@Override
	public String getRefreshToken(Member member) {
		return Jwts.builder().setHeaderParam("typ", "JWT").setIssuedAt(new Date()).claim("uid", member.getMemberid())
				.claim("umail", member.getEmail())
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 15)))
				.signWith(SignatureAlgorithm.HS256, generateKey(REFSALT)).compact();
	}

	@Override
	public byte[] generateKey(String salt) {
		try {
			return salt.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getCause());
		}
		return null;
	}

	// 우선은 액세스토큰만 검증한다
	@Override
	public boolean validateToken(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(generateKey(SALT)).parseClaimsJws(jwt);
			return true;
		} catch (Exception e) {
			System.out.println(e.getCause());
		}
		return false;
	}

	@Override
	public boolean validateRefreshToken(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(generateKey(REFSALT)).parseClaimsJws(jwt);
			return true;
		} catch (Exception e) {
			System.out.println(e.getCause());
		}
		return false;
	}

	@Override
	public boolean isExpiration(String jwt) {
		Jws<Claims> claims;
		try {
			claims = Jwts.parser().setSigningKey(generateKey(SALT)).parseClaimsJws(jwt);
		} catch (SignatureException e) {
			System.out.println(e.getCause());
			return false;
		}
		// 만료시간이 현재시간보다 늦을경우
		return !claims.getBody().getExpiration().after(new Date());
	}

	@Override
	public boolean isExpirationRefresh(String jwt) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(generateKey(REFSALT)).parseClaimsJws(jwt);
		} catch (SignatureException e) {
			System.out.println(e.getCause());
			return false;
		}
		return !claims.getBody().getExpiration().after(new Date());
	}
}
