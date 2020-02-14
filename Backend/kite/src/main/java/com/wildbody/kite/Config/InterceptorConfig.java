package com.wildbody.kite.Config;

import com.wildbody.kite.Interceptor.AuthInterceptor;
import com.wildbody.kite.Interceptor.KakaoInterceptor;
import com.wildbody.kite.Interceptor.NaverInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	private static final String[] EXCLUDE_PATH = {
		"/api/article/**",
		"/api/member/signup",
		"/swagger-ui.html",
		"/api/member/naverlogin",
		"/api/member/kakaologin",
		"/api/member/list",
		"/api/member/friendlist/**",
		"/api/member/requestlist/**",
		"/api/member/responselist/**",
		"/api/member/norelationlist/**"
	};
	
	private static final String[] INCLUDE_PATH = { "/api/member/**" };

	@Autowired
	private AuthInterceptor interceptor;

	@Autowired
	private NaverInterceptor naverInterceptor;

	@Autowired
	private KakaoInterceptor kakaoInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry
//		.addInterceptor(interceptor)
//		.addPathPatterns(INCLUDE_PATH) /* 유저 인증이 필요한 경로 */
//		.excludePathPatterns(EXCLUDE_PATH); /* 유저 인증이 필요하지 않은 경로 */
//				
//		registry.addInterceptor(naverInterceptor)
//		.addPathPatterns("/api/member/naverlogin")
//		.excludePathPatterns(EXCLUDE_PATH);
//		
//		registry.addInterceptor(kakaoInterceptor)
//		.addPathPatterns("/api/member/kakaologin")
//		.excludePathPatterns(EXCLUDE_PATH);
	}
}
