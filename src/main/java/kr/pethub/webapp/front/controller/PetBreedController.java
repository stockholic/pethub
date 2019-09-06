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

import kr.pethub.webapp.admin.board.model.PetInfo;
import kr.pethub.webapp.admin.board.service.PetInfoService;


@Controller
@RequestMapping("/breed")
public class PetBreedController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PetInfoService petInfoService;
	
	/**
	 * 품종
	 */
	@RequestMapping(value= {"list", "list/{searchString}"}, method = RequestMethod.GET)
	public String list( @ModelAttribute PetInfo petInfo,  Model model, @PathVariable(value="searchString", required = false) String searchString) {
		
		petInfo.setSearchString(searchString);
		List<PetInfo> list = petInfoService.selectPetInfoList(petInfo);
		
		for( PetInfo vo  : list) {
			vo.setPetNm(vo.getPetNm() + ("S".equals(vo.getPetSize())?" - 소형견":"M".equals(vo.getPetSize())?" - 중형견":"L".equals(vo.getPetSize())?"- 대형견" : "" )  );
			vo.setIntro( StringUtils.left(vo.getIntro(), 70) + " ..." );
		}
		
		
		model.addAttribute("list", list);
		
		 return "front:pet/petBreedList";
	} 

}