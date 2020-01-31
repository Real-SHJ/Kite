package com.wildbody.kite.Interceptor;

import com.wildbody.kite.Dto.Member;
import com.wildbody.kite.JWT.JwtService;
import com.wildbody.kite.Service.MemberService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@CrossOrigin(origins = {"*"})
public class AuthInterceptor implements HandlerInterceptor {

    static final String SALT = "AuthentificationSALT";
    static final String REFSALT = "RefreshAuthentificationSALT";
    private static final String HEADER_ACCESS = "Authorization";
    private static final String HEADER_RFRESH = "Auth_Refresh";
    @Autowired
    private JwtService jsvc;
    @Autowired
    private MemberService msvc;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
//        왜 너냐???
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Credentials", "true");

        // 클라이언트에서 보내온 데이터로부터 멤버객체 생성
        Member accessMem = setMember(request.getParameterMap());
        System.out.println("client access");
        accessMem = msvc.login(accessMem);
        if (accessMem == null) {
            System.out.println("로그인 실패");
            return false;
        }

        String atoken = request.getHeader(HEADER_ACCESS);
        String rtoken = request.getHeader(HEADER_RFRESH);

        // 인증이 되어있는지 확인한
        if (atoken != null && jsvc.isExpiration(atoken) && jsvc.validateToken(atoken)) {
            // 인증이 안료되었으므로 원래 로직으로 넘어가게 한다
            System.out.println("인증 완료!");
            return true;
        }
        // 인증이 안되면?? refresh 토큰이 있는지 확인하고

        if (accessMem.getRefreshToken() != null && accessMem.getRefreshToken().equals(rtoken)) {
            // refresh token이 존재하므로 accesstoken 발급
            response.setHeader(HEADER_ACCESS, jsvc.getAccessToken(accessMem));
            System.out.println("토큰 재발급 완료");
            return true;
        }
        // refresh토큰마저 없으면 refresh토큰과 accesstoken을 발급 받는다
        rtoken = jsvc.getRefreshToken(accessMem);
        atoken = jsvc.getAccessToken(accessMem);
        // 발급받은 리프레시 토큰을 저장
        accessMem.setRefreshToken(rtoken);
        msvc.memberUpdate(accessMem);
        // access token을 클라이언트에 전송
        response.setHeader(HEADER_ACCESS, atoken);
        System.out.println("인증 성공!");
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
