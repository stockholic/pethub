package kr.pethub.webapp.admin.stock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pethub.core.authority.Auth;


@Controller
@RequestMapping("/adm")
public class StocInfokController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 사이트 정보 화면
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/stock/info")
	public String siteList(Auth user, Model model) {
		
		 return "none:admin/stock/stockInfoList";
	} 

	
	
	
}