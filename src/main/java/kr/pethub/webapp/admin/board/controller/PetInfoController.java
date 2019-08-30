package kr.pethub.webapp.admin.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import kr.pethub.core.authority.Auth;
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
	
	/**
	 * 펫 정보 목록
	 * @param request
	 * @param petinfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/board/petInfolist")
	public String petInfoList() {
		
		 return "admin:site/pet/petInfoList";
	}
	
	/**
	 * 펫 정보 데이터
	 * @param petinfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/board/petInfoJson", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object>  siteListJson(@ModelAttribute PetInfo petinfo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<PetInfo> list = petInfoService.selectPetInfoList(petinfo);
		
		map.put("page", petinfo.getPage());
		map.put("totalRow", petinfo.getTotalRow());
		map.put("totalPage", petinfo.getTotalPage());
		map.put("dataList", list);
		
		return map;
	} 
	
	/**
	 * 펫 정보 조회
	 * @param petinfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/board/petInfoView")
	public String petInfoView(@ModelAttribute PetInfo petinfo, Model model) {
		
		model.addAttribute("petInfo", petInfoService.selectPetInfo(petinfo.getPetSrl()));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fileTp", "petinfo");
		map.put("fileRefSrl", petinfo.getPetSrl());
		model.addAttribute("fileList", fileService.selectFileList(map));
		
		return "admin:site/pet/petInfoView";
	} 
	
	/**
	 * 펫 정보 폼
	 * @param petinfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/board/petInfoForm")
	public String inserPetInfoForm(@ModelAttribute PetInfo petinfo,Model model) {
		
		//수정 폼
		if("update".equals("")){
			model.addAttribute("petInfo", petInfoService.selectPetInfo(petinfo.getPetSrl()));
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fileTp", "petinfo");
			map.put("fileRefSrl", petinfo.getPetSrl());
			model.addAttribute("fileList", fileService.selectFileList(map));
		}
		
		return "admin:site/pet/petInfoForm";
	} 

	/**
	 * 펫 정보 등록
	 * @param request
	 * @param petinfo
	 * @param auth
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/board/insertPetInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object>  insertPetInfo(HttpServletRequest request, PetInfo petinfo, Auth auth) throws JsonParseException, JsonMappingException, IOException {
		
		petInfoService.insertPetInfo(petinfo);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("petinfoSrl", petinfo.getPetSrl());	
		
		return map;
		
	} 
	

	
	/**
	 * 펫 정보 수정
	 * @param request
	 * @param petInfo
	 * @param auth
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/board/updatePetInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> updatePetInfo(HttpServletRequest request, PetInfo petInfo, Auth auth) throws JsonParseException, JsonMappingException, IOException {
		
		petInfoService.updatePetInfo(petInfo);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("petInfoSrl", petInfo.getPetSrl());	
		
		return map;
		
	} 
	
	/**
	 * 펫 정보 삭제
	 * @param petInfo
	 * @param auth
	 */
	@ResponseBody
	@RequestMapping(value="/board/deletePetInfo", method = RequestMethod.POST)
	public void deletePetInfo(PetInfo petInfo, Auth auth) {
		petInfoService.deletePetInfo(petInfo);
	} 
	

}