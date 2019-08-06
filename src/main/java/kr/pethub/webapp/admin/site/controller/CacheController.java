package kr.pethub.webapp.admin.site.controller;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.pethub.core.utils.DateUtil;
import kr.pethub.webapp.admin.site.model.Cache;
import kr.pethub.webapp.admin.site.model.CacheElement;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.statistics.StatisticsGateway;


@Controller
@RequestMapping("/adm")
public class CacheController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CacheManager cacheManager;
	
	/**
	 * 캐쉬 목록
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/cache/list")
	public String cacheList(Model model) {
		
		List<Cache> cacheList = new ArrayList<Cache>();
		Collection<String> cacheNames = cacheManager.getCacheNames();
		for (String cacheNm: cacheNames ){
			
			Ehcache ehcache =  (Ehcache)cacheManager.getCache(cacheNm).getNativeCache();
			StatisticsGateway status = ehcache.getStatistics();
			
			Cache cache = new Cache();
			cache.setCacheNm(cacheNm);
			cache.setCacheSize(status.getSize());
			cache.setCacheHitCount(status.cacheHitCount());
			cache.setCacheExpiredCount(status.cacheExpiredCount());
			cache.setCacheRemoveCount(status.cacheRemoveCount());
			cache.setMemorySize(ehcache.calculateInMemorySize());
			
			cacheList.add(cache);
			
		}
				
		model.addAttribute("cacheList", cacheList);
		
		 return "admin:site/cache/cacheList";
	} 
	
	/**
	 * 캐쉬 요소 목록
	 */
	@RequestMapping(value="/cache/elementList")
	public String elementList(@RequestParam(value = "cacheNm", required = true) String cacheNm,Model model) {
		
		List<CacheElement> cacheElementList = new ArrayList<CacheElement>();
		Ehcache ehcache =  (Ehcache)cacheManager.getCache(cacheNm).getNativeCache();
		
		for (Object key: ehcache.getKeys()) {
			
			Element element = ehcache.get(key);
			CacheElement cacheElement  = new CacheElement();
			cacheElement.setCacheNm(ehcache.getName());
			cacheElement.setCacheKey(key.toString());
			cacheElement.setHitCount(element.getHitCount());
			cacheElement.setCreationTime(DateUtil.timestampToDate(element.getCreationTime()));
			cacheElement.setExpirationTime(DateUtil.timestampToDate(element.getExpirationTime()));
			cacheElement.setLastAccessTime(DateUtil.timestampToDate(element.getLastAccessTime()));
			cacheElement.setExpired(element.isExpired());
			
			cacheElementList.add(cacheElement);
		}
		
		model.addAttribute("cacheNm", cacheNm);
		model.addAttribute("cacheElementList", cacheElementList);
		
		 return "ajax:admin/site/cache/elementAjaxList";
	} 
		

	/**
	 * 캐쉬 삭제
	 */
	@ResponseBody
	@RequestMapping(value="/cache/delete" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> deleteCache(
			@RequestParam(value = "cacheNm", required = true ) String cacheNm
			,@RequestParam(value = "cacheKey", required = true ) String cacheKey ) {
		
		boolean result = false;
		Ehcache cache = (Ehcache) cacheManager.getCache(cacheNm).getNativeCache();
		
		try {
			result =cache.remove(cacheKey);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", result);
		
		
		return map;
			
	}
		
}