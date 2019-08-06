package kr.pethub.webapp.admin.site.model;

import java.util.List;

public class MenuLev2{

	private String menuSrl1;
	private String menuSrl2;
	private String menuNm;
	private String menuUrl;
	private List<MenuLev3> menuLev3;
	
	public String getMenuSrl1() {
		return menuSrl1;
	}
	public void setMenuSrl1(String menuSrl1) {
		this.menuSrl1 = menuSrl1;
	}
	public String getMenuSrl2() {
		return menuSrl2;
	}
	public void setMenuSrl2(String menuSrl2) {
		this.menuSrl2 = menuSrl2;
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
	public List<MenuLev3> getMenuLev3() {
		return menuLev3;
	}
	public void setMenuLev3(List<MenuLev3> menuLev3) {
		this.menuLev3 = menuLev3;
	}
	
}
