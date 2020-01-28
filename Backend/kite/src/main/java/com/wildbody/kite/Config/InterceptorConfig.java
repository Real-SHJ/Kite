package com.wildbody.kite.Config;

import com.wildbody.kite.Interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private static final String[] EXCLUDE_PATH = {"/api/article/**"};
    // 우선은 멤버관련 경로로 접근하는 경우에만 체크를 해보자
    private static final String[] INCLUDE_PATH={"/api/member/**"};

    @Autowired
    private AuthInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
            .addPathPatterns(INCLUDE_PATH)    /* 유저 인증이 필요한 경로 */
            .excludePathPatterns(EXCLUDE_PATH);     /* 유저 인증이 필요하지 않은 경로 */
    }
}
