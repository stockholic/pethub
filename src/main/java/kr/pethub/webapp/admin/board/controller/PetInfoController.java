package kr.pethub.webapp.admin.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import kr.pethub.core.authority.Auth;
import kr.pethub.core.utils.StringUtil;
import kr.pethub.webapp.admin.board.model.PetInfo;
import kr.pethub.webapp.admin.board.service.FileService;
import kr.pethub.webapp.admin.board.service.PetInfoService;


@Controller
@RequestMapping("/adm")
public class PetInfoController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PetInfoService petInfoService;

	@Autowired
	private FileService fileService;
	
	@Value("${petInfo.file.url}") 
	private String petInfoFileUrl;
	
	@Value("${petInfo.file.path}") 
	private String petInfoFilePath;
	
	/**
	 * 펫 정보 목록
	 * @param request
	 * @param petInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/board/petInfoList")
	public String petInfoList(@ModelAttribute PetInfo petInfo) {
		
		 return "admin:site/pet/petInfoList";
	}
	
	/**
	 * 펫 정보 데이터
	 * @param petInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/board/petInfoJson", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object>  siteListJson(@ModelAttribute PetInfo petInfo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<PetInfo> list = petInfoService.selectPetInfoList(petInfo);
		
		map.put("page", petInfo.getPage());
		map.put("totalRow", petInfo.getTotalRow());
		map.put("totalPage", petInfo.getTotalPage());
		map.put("dataList", list);
		
		return map;
	} 
	
	/**
	 * 펫 정보 조회
	 * @param petInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/board/petInfoView")
	public String petInfoView(@ModelAttribute PetInfo petInfo, Model model) {
		
		model.addAttribute("petInfoData", petInfoService.selectPetInfo(petInfo.getPetSrl()));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fileTp", "petInfo");
		map.put("fileRefSrl", petInfo.getPetSrl());
		model.addAttribute("fileList", fileService.selectFileList(map));

		
		
		model.addAttribute("petInfoFileUrl", petInfoFilePath);
		
		
		System.out.println(">>>>>>>>>>>> " + petInfoFileUrl);
		
		return "admin:site/pet/petInfoView";
	} 
	
	/**
	 * 펫 정보 폼
	 * @param petInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/board/petInfoForm")
	public String inserPetInfoForm(@ModelAttribute PetInfo petInfo, Model model) {
		
		//수정 폼
		if(  petInfo.getPetSrl() !=null && StringUtil.isNumber(petInfo.getPetSrl().toString()) )   {
			
			model.addAttribute("petInfoData", petInfoService.selectPetInfo(petInfo.getPetSrl()));
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fileTp", "petInfo");
			map.put("fileRefSrl", petInfo.getPetSrl());
			model.addAttribute("fileList", fileService.selectFileList(map));
		}
		
		
		return "admin:site/pet/petInfoForm";
	} 

	/**
	 * 펫 정보 등록
	 * @param request
	 * @param petInfo
	 * @param auth
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/board/insertPetInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object>  insertPetInfo(PetInfo petInfo) throws JsonParseException, JsonMappingException, IOException {
		
		petInfoService.insertPetInfo(petInfo);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("petSrl", petInfo.getPetSrl());	
		
		return map;
		
	} 
	
	/**
	 * 펫 정보 수정
	 * @param petInfo
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/board/updatePetInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> updatePetInfo( PetInfo petInfo) throws JsonParseException, JsonMappingException, IOException {
		
		petInfoService.updatePetInfo(petInfo);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("petSrl", petInfo.getPetSrl());	
		
		return map;
		
	} 
	
	/**
	 * 펫 정보 삭제
	 * @param petInfo
	 */
	@ResponseBody
	@RequestMapping(value="/board/deletePetInfo", method = RequestMethod.POST)
	public void deletePetInfo(PetInfo petInfo) {
		petInfoService.deletePetInfo(petInfo);
	} 
	

}