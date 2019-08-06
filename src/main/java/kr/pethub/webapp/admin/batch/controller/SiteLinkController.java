package kr.pethub.webapp.admin.batch.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.pethub.core.authority.Auth;
import kr.pethub.core.module.model.SiteLinkData;
import kr.pethub.core.module.service.ConsoleLog;
import kr.pethub.webapp.admin.batch.model.SiteInfo;
import kr.pethub.webapp.admin.batch.model.SiteLink;
import kr.pethub.webapp.admin.batch.model.SiteLinkDataList;
import kr.pethub.webapp.admin.batch.service.SiteLinkService;


@Controller
@RequestMapping("/adm")
public class SiteLinkController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SiteLinkService siteLinkService;
	
	@Autowired
	private ConsoleLog consoleLog;
	
	/**
	 * 사이트 링크 화면
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/batch/siteLinkList")
	public String siteList(Auth user, Model model) {
		
		 return "admin:batch/siteLinkList";
	} 

	/**
	 * 사이트 링크 데이터
	 * @param siteLink
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/batch/siteLinkJson", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object>  siteListJson(@ModelAttribute SiteLink siteLink) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<SiteLink> list =  siteLinkService.selectSiteLinkList(siteLink);
		
		map.put("page", siteLink.getPage());
		map.put("totalRow", siteLink.getTotalRow());
		map.put("totalPage", siteLink.getTotalPage());
		map.put("dataList", list);

		return map;
		
	} 
	
	
	/**
	 * 사이트 링크 등록폼
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/batch/siteLinkForm")
	public String siteForm(Auth user, @RequestParam(value="linkSrl", required=false) String linkSrl, Model model) {
		
		//사이트 목록
		List<SiteInfo> siteInfoLIst =  siteLinkService.selectSiteInfoList();
		
		if( StringUtils.isNotEmpty(linkSrl) ) {
			model.addAttribute("siteLink", siteLinkService.selectSiteLink(linkSrl));
		}
		
		model.addAttribute("siteInfoLIst", siteInfoLIst);
		
		 return "ajax:admin/batch/siteLinkForm";
	} 
	

	/**
	 * 사이트 링크 등록
	 * @param user
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/batch/insertSiteLink", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> insertSiteLink(SiteLink siteLink) {
		
		int result = siteLinkService.insertSiteLink(siteLink);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		
		return map;
	} 

	/**
	 * 사이트 링크 수정
	 * @param user
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/batch/updateSiteLink", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateSite(SiteLink siteLink) {
		
		int result = siteLinkService.updateSiteLink(siteLink);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		
		return map;
	} 
	
	/**
	 * 사이트 링크 삭제
	 * @param user
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/batch/deleteSiteLink", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> deleteSiteLink(SiteLink siteLink) {
		
		int result = siteLinkService.deleteSiteLink(siteLink);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		
		return map;
	} 
	
	/**
	 * 사이트 링크 테스트
	 */
	@ResponseBody
	@RequestMapping(value="/batch/siteLinkTest")
	public void siteLinkTest( SiteLink siteLink) {
		
		try {
			
			if( consoleLog.getConsole() != null && consoleLog.getConsole().isOpen() == true) {
				
				//Class 생성
				Class<?> clasz = Class.forName(siteLink.getLinkCls());
				Object obj = clasz.newInstance();
				
				//Method 호출
				Method getList = clasz.getMethod(siteLink.getLinkMtdLst(), String.class, ConsoleLog.class);
				getList.invoke(obj, siteLink.getLinkUrl(), consoleLog);
			}
			
		}catch(Exception e) {
			
			SiteLinkData siteLinkData  = new SiteLinkData();
			siteLinkData.setDataContent(e.toString());
			
			ObjectMapper mapper = new ObjectMapper(); 
			
			try {
				consoleLog.getConsole().send(mapper.writeValueAsString(siteLinkData) );
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			logger.error(e.toString());
			e.getStackTrace();
		}
		
	} 
	
	/**
	 * 사이트 데이터 등록
	 * @param siteLinkDataList
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/batch/insertSiteLinkData", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> insertSiteLinkData(SiteLinkDataList siteLinkDataList) {
		
		int result  = siteLinkService.insertSiteLinkData(siteLinkDataList);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		
		return map;
	} 
	
}