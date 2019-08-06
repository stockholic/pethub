package kr.pethub.webapp.front.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.pethub.core.utils.StringUtil;
import kr.pethub.webapp.api.model.SiteLinkData;
import kr.pethub.webapp.api.service.PetService;


@Controller
@RequestMapping("/search")
public class PetController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PetService petService;
	
	/**
	 * 펫 검색
	 * @param siteLinkData
	 * @param model
	 * @param p
	 * @param s
	 * @return
	 */
	@RequestMapping(value= {"list", "list/{p}", "list/{p}/{s}"}, method = RequestMethod.GET)
	public String list( @ModelAttribute SiteLinkData siteLinkData,  Model model
							, @PathVariable(value="p", required = false) String p
							, @PathVariable(value="s", required = false) String s
						 ) 
	{
		
		
		String searchString = "";

		//검색어 분리
		if( StringUtils.isNotEmpty( s ) ) {
			//여러공백 하나의 공잭처리
			searchString = StringUtils.isNotEmpty( s ) ?  s.trim().replaceAll(" +", " ") : "";
			siteLinkData.setSearchString( Arrays.asList(searchString.split(" ")) );
		}
		
		//페이지 번호 validation
		if( StringUtil.isRegex("^[0-9]{1,4}$",p) ) {
			siteLinkData.setPage(Integer.parseInt( p ));
			siteLinkData.setDataTitle( searchString );
		}else {
			siteLinkData.setPage( 1 );
			siteLinkData.setDataTitle("");
		}
		
		
		List<SiteLinkData> list = petService.selectPetList(siteLinkData);
		
		model.addAttribute("list", list);
		model.addAttribute("updatedTime", petService.selectPetUpdatedTime()	);
		
		 return "front:pet/petList";
	} 

}