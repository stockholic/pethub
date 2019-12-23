package kr.pethub.webapp.admin.site.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pethub.webapp.admin.site.service.WasInfoService;


@RestController
@RequestMapping("/adm")
public class WasInfoController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	WasInfoService wasInfo;

	@RequestMapping(value="/wasInfo/cpu", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> cpu() {
		 return wasInfo.getCpuInfo();
	} 
	
	@RequestMapping(value="/wasInfo/mem", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String,Object>> mem() {
		return wasInfo.getMemIfo();
	} 
	
	@RequestMapping(value="/wasInfo/jdbc", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> jdbc() {
		return wasInfo.getJdbcPool();
	} 
	
	@RequestMapping(value="/wasInfo/thread", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> thread() {
		return wasInfo.getThread();
	} 
	
	
}