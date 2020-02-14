package com.wildbody.kite.Interceptor;

import com.wildbody.kite.DTO.Member;
import com.wildbody.kite.DTO.Token;
import com.wildbody.kite.JWT.JwtService;
import com.wildbody.kite.Service.MemberService;
import com.wildbody.kite.Service.TokenService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	private static final String HEADER_ACCESS = "Authorization";

	@Autowired
	private JwtService jsvc;
	@Autowired
	private MemberService msvc;
	@Autowired
	private TokenService tsvc;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String cAccessToken = request.getHeader(HEADER_ACCESS);
		System.out.println("client access");

		Member accessMem = setMember(request.getParameterMap());
		String rtoken, atoken = null;
		Token accMemToken = null;

		if (cAccessToken == null) {
			// access token이 없으면 등록된 유저인지 확인을 하고 accesstoken과 refreshtoken을 발급한다
			accessMem = msvc.login(accessMem);
			if (accessMem == null) {
				System.out.println("Not sign up User");
				response.addHeader("islogin", "false");
				response.addHeader("msg", "Don't Sign in User");
				return false;
			}

			// accesstoken을 발급해 보내준다
			atoken = jsvc.getAccessToken(accessMem);
			rtoken = jsvc.getRefreshToken(accessMem);
			accMemToken = tsvc.select(accessMem);

			accMemToken.setRefreshToken(rtoken);
			tsvc.update(accMemToken);
			response.addHeader(HEADER_ACCESS, atoken);

			msvc.memberUpdate(accessMem);

		} else {
			// accessToken이 있으면
			// 토큰 검증 (내가 발급했는지)
			if (!jsvc.validateToken(cAccessToken)) {
				System.out.println("1번째");
				response.addHeader("msg", "validation error");
				response.addHeader("islogin", "false");
				return false;
			} else {
				// 만료되었는지 확인
				if (!jsvc.isExpiration(cAccessToken)) {
					// 만료가 안되었으면
					// 리프레시 토큰을 이용해 인증 후 액세스 토큰을 재발급
//          ! null
					accessMem = msvc.memberInfo(accessMem);
					accMemToken = tsvc.select(accessMem);
					rtoken = accMemToken.getRefreshToken();
					if (!jsvc.validateToken(rtoken)) {
						// 리프레시 토큰 인증안됨
						System.out.println("2번째");
						response.addHeader("msg", "validation error");
						response.addHeader("islogin", "false");
						return false;
					} else {
						if (!jsvc.isExpirationRefresh(rtoken)) {
							// refreshtoken 만료
							// refreshtoken과 accesstoken을 동시에 발급
							atoken = jsvc.getAccessToken(accessMem);
							rtoken = jsvc.getRefreshToken(accessMem);
							accMemToken.setRefreshToken(rtoken);
							// db 업데이트
							tsvc.update(accMemToken);
						} else {
							// refreshtoken 만료 안됨
							// accesstoken만 발급
							atoken = jsvc.getAccessToken(accessMem);
						}
					}
					response.addHeader(HEADER_ACCESS, atoken);
				}
			}
		}
		return true;
	}

	private Member setMember(Map<String, String[]> input) {
		Member ret = new Member();
		for (String key : input.keySet()) {
			for (String val : input.get(key)) {
				switch (key) {
				case "id":
					ret.setMemberid(Integer.parseInt(val));
					break;
				case "email":
					ret.setEmail(val);
					break;
				case "pw":
					ret.setPw(val);
				}
			}
		}

		return ret;
	}
}
