package kr.pethub.webapp.front.controller;

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

import kr.pethub.core.constrants.SystemConstants;
import kr.pethub.core.utils.StringUtil;
import kr.pethub.webapp.admin.board.model.PetInfo;
import kr.pethub.webapp.admin.board.service.PetInfoService;


@Controller
@RequestMapping("/breed")
public class PetBreedController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PetInfoService petInfoService;
	
	/**
	 * 품종 목록
	 */
	@RequestMapping(value= {"list", "list/{searchString}"}, method = RequestMethod.GET)
	public String list( @ModelAttribute PetInfo petInfo,  Model model, @PathVariable(value="searchString", required = false) String searchString) {
		
		petInfo.setSearchString(searchString);
		petInfo.setRowSize(1000);
		petInfo.setOrderBy("pet_nm ASC");
		
		//소형견 디폴트
		if( StringUtils.isEmpty(petInfo.getPetSize()) && StringUtils.isEmpty(searchString)) petInfo.setPetSize("S");
		
		List<PetInfo> list = petInfoService.selectPetInfoList(petInfo);
		
		model.addAttribute("list", list);
		model.addAttribute("petTitle", SystemConstants.TITLE_PREFIX +" 강아지 품종 정보" );
		
		 return "front:pet/petBreedList";
	} 
	
	/**
	 * 품종 조회
	 */
	@RequestMapping(value= "view/{petSrl}", method = RequestMethod.GET)
	public String view( @ModelAttribute PetInfo petInfo,  Model model, @PathVariable(value="petSrl", required = true) Integer petSrl) {
		
		//petSrl validation
		if( !StringUtil.isRegex("^[0-9]{1,4}$",Integer.toString(petSrl)) )	return "redirect:/static/error404.html";
		
		
		//데이터가 없으면
		PetInfo petInfoData = petInfoService.selectPetInfo(petSrl);
		if(petInfoData == null) return "redirect:/static/error404.html";

		model.addAttribute("petInfoData", petInfoData);
		model.addAttribute("petTitle","품종 - " + petInfoData.getPetNm() );
		 return "front:pet/petBreedView";
	} 

}