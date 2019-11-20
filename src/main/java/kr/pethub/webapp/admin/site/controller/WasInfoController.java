package kr.pethub.webapp.admin.site.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.pethub.webapp.admin.site.service.WasInfoService;


@Controller
@RequestMapping("/wasInfo")
public class WasInfoController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	WasInfoService wasInfo;

	@ResponseBody
	@RequestMapping(value="/cpu")
	public void cpu() {
		System.out.println("CPU uage : " + wasInfo.getCpuInfo());
	} 
	
	
}