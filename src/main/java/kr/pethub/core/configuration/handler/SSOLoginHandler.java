package kr.pethub.core.configuration.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class SSOLoginHandler implements AuthenticationSuccessHandler {


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		if(authentication != null) {
			
			if(authentication.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authentication); 	// 패스워드 없이 자동 로그인
				response.sendRedirect("/");
			}else {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		
	}

}
