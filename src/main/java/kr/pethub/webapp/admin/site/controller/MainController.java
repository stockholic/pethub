package kr.pethub.webapp.admin.site.controller;

import java.util.Locale;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pethub.core.authority.Auth;


@Controller
@RequestMapping("/adm")
public class MainController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 로그인 폼
	 * @return
	 */
	@RequestMapping(value="/login")
	public String login() {
		
		 return "none:admin/site/user/login";
		 
	} 
	

	@RequestMapping(value="/main")
	public String main(Locale locale, HttpServletRequest request, Auth auth) {
		
		logger.debug("Welcome home! The client locale is {}.", locale);
		
		return "admin:main";
	} 
	

	
}