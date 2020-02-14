package com.wildbody.kite.Interceptor;

import com.wildbody.kite.DTO.KakaoMember;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class KakaoInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		KakaoMember kmem = getKakaoMember(request.getParameterMap());
		// 만료시간 체크 어떻게?

		return false;
	}

	private KakaoMember getKakaoMember(Map<String, String[]> map) {
		KakaoMember ret = new KakaoMember();

		for (String key : map.keySet()) {
			for (String val : map.get(key)) {
				switch (key) {
				case "accesstoken":
					ret.setAccesstoken(val);
					break;
				case "refreshtoken":
					ret.setRefreshtoken(val);
				}
			}
		}

		return ret;
	}
}
