package kr.pethub.core.configuration.filter;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.util.matcher.RequestMatcher;

import kr.pethub.core.authority.AuthInfo;
import kr.pethub.core.authority.AuthService;


public class SSOAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private AuthService authService;

	public SSOAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher,	UserDetailsService authService) {
		super(requiresAuthenticationRequestMatcher);
		this.authService = (AuthService) authService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		PreAuthenticatedAuthenticationToken authentication = null;

		// TODO
		// 1 .Cookie or SSO ,  login 폼에서 쿠키가 있다면 이쪽으로 전송하는 로직구현.  ex) document.location.href = "/loginSSO?userId=xxx"
		// 2. 전달 받은 값(userId)과 다시한번 쿠키체크 하여 처리 
		
		 String userId = request.getParameter("userId");
		 
		if (isCookie(userId)) {
			AuthInfo authInfo = (AuthInfo) authService.loadUserByUsername(userId);
			authentication =  (authInfo.getUser() != null) ? new PreAuthenticatedAuthenticationToken(authInfo, null, authInfo.getAuthorities()) :  new PreAuthenticatedAuthenticationToken(null, null);
		}
		
		if(authentication == null)  response.sendRedirect("/login");
		
		return authentication;

	}
	
//	@Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
//            throws IOException, ServletException {
//
//        // As this authentication is in HTTP header, after success we need to continue the request normally
//        // and return the response as if the resource was not secured at all
//        chain.doFilter(request, response);
//    }
	
	public final boolean isCookie(String userId) {
		
		if(StringUtils.isEmpty(userId )) return false;
		
		//TODO
		//전달받은  usreId와 쿠키값 일치 하나 체크
		
		return true;
	}

}
