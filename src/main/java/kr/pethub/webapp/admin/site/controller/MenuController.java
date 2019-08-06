package kr.pethub.webapp.admin.site.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.pethub.webapp.admin.site.model.ArrMenu;
import kr.pethub.webapp.admin.site.model.Menu;
import kr.pethub.webapp.admin.site.model.Role;
import kr.pethub.webapp.admin.site.service.MenuService;
import kr.pethub.webapp.admin.site.service.RoleService;


@Controller
@RequestMapping("/adm")
public class MenuController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleService roleService;

	/**
	 *  메뉴 목록
	 * @return
	 */
	@RequestMapping(value="/menu/list")
	public String menuList() {
		 return "admin:site/menu/menuList";
	} 
	
	@RequestMapping(value="/menu/ajaxList")
	public String  ajaxList(@ModelAttribute Menu menu, Model model) {
		
		List<Menu> list = menuService.selectMenuList(menu);
		model.addAttribute("list", list);
		
		 return "ajax:admin/site/menu/menuAjaxList";
	} 

	/**
	 * 메뉴상태 변경
	 * @param mList
	 */
	@ResponseBody
	@RequestMapping(value="/menu/updateStatus")
	public void  updateMenuStatus(ArrMenu arrMenu) {
		menuService.updateMenuStatus(arrMenu);
	} 
	
	/**
	 * 메뉴 등록/수정 폼
	 * @param menu
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/menu/form")
	public String menuForm(@ModelAttribute Menu menu, Model model) {
		
		if(StringUtils.isNotEmpty(menu.getMenuSrl())){
			model.addAttribute("menuData", menuService.selectMenu(menu));
		}
		 return "none:admin/site/menu/menuForm";
	}
	
	/**
	 *  메뉴 등록/수정
	 * @param menu
	 */
	@ResponseBody
	@RequestMapping(value="/menu/insert")
	public void insertMenu(Menu menu) {
		
		if(StringUtils.isNotEmpty(menu.getMenuSrl())) {
			menuService.updateMenu(menu);
		}else {
			
			// 3차 메뉴 등록
			if(StringUtils.isNotEmpty(menu.getMenuSrl1()) && StringUtils.isNotEmpty(menu.getMenuSrl2()) ) {
				menu.setParentSrl(menu.getMenuSrl2());
			
			// 2차 메뉴 등록
			}else if(StringUtils.isNotEmpty(menu.getMenuSrl1()) && StringUtils.isEmpty(menu.getMenuSrl2()) ) {
				menu.setParentSrl(menu.getMenuSrl1());
				
			//1차 메뉴 등록	
			}else {
				menu.setParentSrl("0");
			}
			
			menuService.insertMenu(menu);
		}
	} 
	
	/**
	 *  메뉴 삭제
	 * @param menu
	 */
	@ResponseBody
	@RequestMapping(value="/menu/delete")
	public void deleteMenu(Menu menu) {
		menuService.deleteMenu(menu);
	}
	
	
	/**
	 *  메뉴 권한 목록
	 * @return
	 */
	@RequestMapping(value="/menu/role/list")
	public String menuRoleList(Model model) {
		
		Role role  = new Role();
		role.setUseYn("Y");;
		model.addAttribute("authList", roleService.selectRoleList(role));
		
		 return "admin:site/menu/menuRoleList";
	} 
	
	/**
	 *  메뉴 권한 목록
	 * @return
	 */
	@RequestMapping(value="/menu/role/ajaxList")
	public String menuRoleAjaxList(@ModelAttribute Role role, Model model) {
		
		model.addAttribute("menuList", menuService.selectAllMenuList(role));
		return "ajax:admin/site/menu/menuRoleAjaxList";
		
	} 
	
	/**
	 *  메뉴 권한 등록
	 * @param menu
	 */
	@ResponseBody
	@RequestMapping(value="/menu/role/insert")
	public void insertMenuRole(ArrMenu arrMenu) {
		menuService.insertMenuRole(arrMenu);
	} 

	
}