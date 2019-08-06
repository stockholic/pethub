package kr.pethub.webapp.admin.site.model;

import java.util.List;

public class MenuLev1{

	private String menuSrl1;
	private String menuNm;
	private String menuUrl;
	private List<MenuLev2> menuLev2;
	
	public String getMenuSrl1() {
		return menuSrl1;
	}
	public void setMenuSrl1(String menuSrl1) {
		this.menuSrl1 = menuSrl1;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public List<MenuLev2> getMenuLev2() {
		return menuLev2;
	}
	public void setMenuLev2(List<MenuLev2> menuLev2) {
		this.menuLev2 = menuLev2;
	}
	
}
