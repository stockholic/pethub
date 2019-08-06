package kr.pethub.core.configuration.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import kr.pethub.core.authority.AuthInfo;
import kr.pethub.core.authority.AuthService;


public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {

    protected Log logger = LogFactory.getLog(this.getClass());

    private AuthService authService;
    
    public LoginSuccessHandler(UserDetailsService authService) {
        this.authService = (AuthService)authService;
    }
    
    
    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    throws ServletException, IOException {
        AuthInfo user = (AuthInfo) authentication.getPrincipal();
        String remoteAddr = request.getRemoteAddr();
        
        if(user == null) {
            logger.error("user is null");
        }else {
            logger.debug("user.getUsername(): " + user.getUsername() + ", user.getPassword(): " + user.getPassword());
            
            // record login success of user
            authService.insertLoginLog(user.getUsername(), "Y", remoteAddr, "로그인 성공");
        }
        
        super.onAuthenticationSuccess(request, response, authentication);
    }
   

}