package kr.pethub.core.configuration.handler;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import kr.pethub.core.authority.AuthService;


public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler  {

    protected Log logger = LogFactory.getLog(this.getClass());

    private AuthService authService;

    public LoginFailureHandler(String defaultFailureUrl, UserDetailsService authService) {
        super(defaultFailureUrl);
        this.authService = (AuthService)authService;
    }
    
    @Override
    public void onAuthenticationFailure(
        HttpServletRequest request, HttpServletResponse response, AuthenticationException exception
    ) throws IOException, ServletException {
        
        String loginid = request.getParameter("userNm");
        String loginpasswd = request.getParameter("password");
        String remoteAddr = request.getRemoteAddr();
        
        request.setAttribute("userNm", loginid);
        request.setAttribute("password", loginpasswd);

        logger.debug("loginid: " + loginid + ", loginpasswd: " + loginpasswd);
        
        UserDetails user = authService.loadUserByUsername(loginid);
        if(user == null || StringUtils.isBlank(user.getUsername())) {
            //ID가 없는 경우
            authService.insertLoginLog(loginid, "N", remoteAddr, "사용자ID 오류");
        }else {
            //ID가 있으나 비밀번호를 틀린 경우
            authService.insertLoginLog(user.getUsername(), "N", remoteAddr, "비밀번호 오류");
        }
        
        super.onAuthenticationFailure(request, response, exception);
        
    }
}