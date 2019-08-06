package kr.pethub.webapp.admin.site.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.pethub.webapp.admin.site.model.Role;
import kr.pethub.webapp.admin.site.model.User;
import kr.pethub.webapp.admin.site.service.RoleService;
import kr.pethub.webapp.admin.site.service.UserService;


@Controller
@RequestMapping("/adm")
public class UserController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	/**
	 * 사용자 목록
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/user/list")
	public String userList(@ModelAttribute User user, Model model) {
		
		List<User> list = userService.selectUserList(user);
		model.addAttribute("list", list);
		
		 return "admin:site/user/userList";
	} 

	
	/**
	 * 사용자 등록/수정 폼
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/user/form")
	public String userForm(@ModelAttribute User user, Model model) {
		
		Role role = new Role();
		role.setUseYn("Y");
		model.addAttribute("roleList", roleService.selectRoleList(role));
		
		if(user.getUserSrl() != null){
			model.addAttribute("userInfo", userService.selectUser(user.getUserSrl()));
		}
		
		 return "admin:site/user/userForm";
		 
	} 
	
	/**
	 * 사용자 등록/수정
	 * @param user
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value="/user/insert")
	public void insertUser(User user) throws ParseException {
		if(user.getUserSrl() != null)
			userService.updateUser(user);
		else 
			userService.insertUser(user);
	} 
	
	
}