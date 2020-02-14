package com.wildbody.kite.Interceptor;

import com.wildbody.kite.DTO.NaverMember;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class NaverInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String state = request.getHeader("nstate");

		String storedState = (String) request.getSession().getAttribute("state");
		NaverMember naverMember = nMember(request.getParameterMap());

		if (naverMember.getAccesstoken() != null && naverMember.getState() != null
				&& state.equals(naverMember.getState())) {
			return true;
		}

		return false;
	}

	private NaverMember nMember(Map<String, String[]> map) {
		NaverMember naverMember = new NaverMember();

		for (String key : map.keySet()) {
			for (String val : map.get(key)) {
				switch (key) {
				case "accesstoken":
					naverMember.setAccesstoken(val);
					break;
				case "state":
					naverMember.setState(val);
					break;
				}
			}
		}

		return naverMember;
	}

}
