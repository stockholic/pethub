package kr.pethub.webapp.admin.site.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.pethub.webapp.admin.site.model.Role;
import kr.pethub.webapp.admin.site.service.RoleService;


@Controller
@RequestMapping("/adm")
public class RoleController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private SessionRegistryImpl sessionRegistry;

	/**
	 * 권한 목록
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/role/list")
	public String roleList(@ModelAttribute Role role, Model model) {
		/*
		List<Object> principals = this.sessionRegistry.getAllPrincipals();
		
		for (Object principal: principals) {
		    	System.out.println( ">> " +  ((AuthInfo)principal).getUser().getUserId());
		    	System.out.println(">> " +  ((AuthInfo)principal).getUser().getSessionId() + ":" + ((AuthInfo)principal).getUser().getLoginDate());
		}
		*/
		
		 return "admin:site/role/roleList";
	} 
	
	/**
	 * 권한 등록/수정 폼
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/role/form")
	public String roleForm(@ModelAttribute Role role, Model model) {
		
		if(role.getRoleSrl() != null){
			model.addAttribute("role", roleService.selectRole(role.getRoleSrl()));
		}
		
		 return "ajax:admin/site/role/roleForm";
		 
	} 
	
	/**
	 * 권한 목록 데이터
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/role/listJson", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object>  roleListJson(@ModelAttribute Role role) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Role> list = roleService.selectRoleList(role);
		
		map.put("page", role.getPage());
		map.put("totalRow", role.getTotalRow());
		map.put("totalPage", role.getTotalPage());
		map.put("dataList", list);

		return map;
		
	} 
	

	
	/**
	 * 권한 등록/수정	
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/role/insert")
	public void insertRole(Role role) {
		
		if(StringUtils.isNotEmpty(role.getRoleSrl()))
			roleService.updateRole(role);
		else 
			roleService.insertRole(role);
	} 

	/**
	 * 권한 사용여부 수정
	 * @param role
	 */
	@ResponseBody
	@RequestMapping(value="/role/updateStatus")
	public void updateStatus(Role role) {
		roleService.updateStatus(role);
	} 

	/**
	 * 권한 삭제
	 * @param role
	 */
	@ResponseBody
	@RequestMapping(value="/role/delete")
	public void deleteRole(Role role) {
		roleService.deleteRole(role);
	} 
	
	
}