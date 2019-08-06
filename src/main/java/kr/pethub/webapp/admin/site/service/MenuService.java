package kr.pethub.webapp.admin.site.service;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pethub.core.annotation.CacheEvictMenu;
import kr.pethub.core.annotation.CacheableMenu;
import kr.pethub.webapp.admin.site.dao.MenuDao;
import kr.pethub.webapp.admin.site.model.ArrMenu;
import kr.pethub.webapp.admin.site.model.Menu;
import kr.pethub.webapp.admin.site.model.MenuLev1;
import kr.pethub.webapp.admin.site.model.MenuLev2;
import kr.pethub.webapp.admin.site.model.MenuLev3;
import kr.pethub.webapp.admin.site.model.Role;


@Service
public class MenuService{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MenuDao menuDao;
		
	/**
	 * 메뉴 목록
	 * @param menu
	 * @return
	 */
	public List<Menu> selectMenuList(Menu menu){
		
		 List<Menu> list = new ArrayList<Menu>();
		 
		 //대분류
		 if(menu.getMenuSrl1() == null && menu.getMenuSrl2() == null ) {
			 list = menuDao.selectMenuList1(menu);
		
		//중분류
		 }else  if(menu.getMenuSrl1() != null && menu.getMenuSrl2() == null ) {
			 list = menuDao.selectMenuList2(menu);
			 
		//소분류	 
		 }else  if(menu.getMenuSrl1() != null && menu.getMenuSrl2() != null ) {
			 list = menuDao.selectMenuList3(menu);
		 }
		
		return  list;
	}
	
	/**
	 * 메뉴 조회
	 * @param menu
	 * @return
	 */
	public Menu selectMenu(Menu menu){
		
		return menuDao.selectMenu(menu);
	}
	
	
	/**
	 * 메뉴상태 변경
	 * @param menu
	 */
	@Transactional
	public void  updateMenuStatus(ArrMenu arrMenu){
		
		for( int i = 0; i < arrMenu.getArrMenuSrl().size(); i++ ) {
			Menu menu = new Menu();
			menu.setMenuSrl(arrMenu.getArrMenuSrl().get(i));
			menu.setUseYn(arrMenu.getArrMenuUseYn().get(i));
			menu.setMenuStp(arrMenu.getArrMenuStp().get(i));
			menuDao.updateMenuStatus(menu);
		}
		
	}

	/**
	 * 메뉴 등록
	 * @param menu
	 */
	public void  insertMenu(Menu menu){
		menuDao.insertMenu(menu);
	}

	/**
	 * 메뉴 수정
	 * @param menu
	 */
	public void  updateMenu(Menu menu){
		menuDao.updateMenu(menu);
	}
	
	/**
	 * 메뉴 전체 목록
	 * @param menu
	 * @return
	 */
	public List<Menu> selectAllMenuList(Role role){
		return  menuDao.selectAllMenuList(role);
	}
	
	/**
	 * 권한별 메뉴 목록
	 * @param menu
	 * @return
	 */
	@CacheableMenu
	public List<MenuLev1> selectAuthMenuList(Role role){
		
		List<Menu> menuList = menuDao.selectAuthMenuList(role);
		
		// 1차 메뉴 파싱
		List<MenuLev1> menuLev1 = new ArrayList<MenuLev1>();
		for(Menu m :  menuList ) {
			if( "1".equals(m.getMenuLev()) ) {
				MenuLev1 lev1 = new MenuLev1();
				lev1.setMenuSrl1(m.getMenuSrl1());
				lev1.setMenuNm(m.getMenuNm());
				lev1.setMenuUrl(m.getMenuUrl());
				menuLev1.add(lev1);
			}
		}
		
		// 2차 메뉴 파싱
		for(MenuLev1 lev1 :  menuLev1 ) {
			List<MenuLev2> menuLev2 = new ArrayList<MenuLev2>();
			for(Menu m :  menuList ) {
				if( "2".equals(m.getMenuLev()) && lev1.getMenuSrl1().equals(m.getMenuSrl1())) {
					MenuLev2 lev2 = new MenuLev2();
					lev2.setMenuSrl1(m.getMenuSrl1());
					lev2.setMenuSrl2(m.getMenuSrl2());
					lev2.setMenuNm(m.getMenuNm());
					lev2.setMenuUrl(m.getMenuUrl());
					menuLev2.add(lev2);
				}
				lev1.setMenuLev2(menuLev2);
			}
		}
		
		// 3차 메뉴 파싱
		for(MenuLev1 lev1 :  menuLev1 ) {
			for(MenuLev2 lev2 : lev1.getMenuLev2() ) {
				List<MenuLev3> menuLev3 = new ArrayList<MenuLev3>();
				for(Menu m :  menuList ) {
					if( "3".equals(m.getMenuLev()) 
							&& lev2.getMenuSrl1().equals(m.getMenuSrl1()) 
							&& lev2.getMenuSrl2().equals(m.getMenuSrl2())  ) {
						
						MenuLev3 lev3 = new MenuLev3();
						lev3.setMenuNm(m.getMenuNm());
						lev3.setMenuUrl(m.getMenuUrl());
						menuLev3.add(lev3);
					}
					lev2.setMenuLev3(menuLev3);
				}
				
			}
		}
		/*
		for(MenuLev1 lev1 : menuLev1) {
			logger.debug("1차 :::::: lev2 Size : {}, menuSrl : {}, menuNm : {}, menuUrl : {}",lev1.getMenuLev2().size(), lev1.getMenuSrl1(), lev1.getMenuNm(), lev1.getMenuUrl());
			for(MenuLev2 lev2 : lev1.getMenuLev2() ) {
				logger.debug("\t2차 :::::: lev3 Size : {}, menuSrl : {}, menuNm : {}, menuUrl : {}",lev2.getMenuLev3().size(), lev2.getMenuSrl1(), lev2.getMenuNm(), lev2.getMenuUrl());
				for(MenuLev3 lev3 : lev2.getMenuLev3() ) {
					logger.debug("\t\t3차 :::::: menuSrl : {}, menuNm : {}, menuUrl : {}", lev3.getMenuNm(), lev3.getMenuUrl());
				}
			}
		}*/
		
		return  menuLev1;
	}
	
	/**
	 * 메뉴 삭제 
	 * @param menu
	 */
	@Transactional
	public void  deleteMenu(Menu menu){
		
		// 3 lev 삭제
		if("menu3".equals(menu.getMenuNm())) {
			menuDao.deleteMenu(menu.getMenuSrl());
		
		// 2, 3 lev 삭제
		}else if("menu2".equals(menu.getMenuNm())) {
			
			menuDao.deleteMenu3(menu);
			
			menuDao.deleteMenu(menu.getMenuSrl());
			
		// 1, 2, 3 lev 삭제
		}else if("menu1".equals(menu.getMenuNm())) {
			
			menuDao.deleteMenu3(menu);
			
			menuDao.deleteMenu2(menu);
			
			menuDao.deleteMenu(menu.getMenuSrl());
		}
		
	}
	
	/**
	 * 권한별 메뉴 등록
	 * @param arrMenu
	 */
	@CacheEvictMenu
	@Transactional
	public void  insertMenuRole(ArrMenu arrMenu){
		
		menuDao.deleteMenuRole(arrMenu.getAuthCd());
		
		for( int i = 0; i < arrMenu.getArrMenuSrl().size(); i++ ) {
			Role role = new Role();
			role.setRoleCd(arrMenu.getAuthCd());
			role.setRoleSrl(arrMenu.getArrMenuSrl().get(i));
			
			menuDao.insertMenuRole(role);
		}
		
	}
	
	
}
