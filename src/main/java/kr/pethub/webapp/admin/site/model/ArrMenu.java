package kr.pethub.webapp.admin.site.model;

import java.util.List;

public class ArrMenu{

	private String authCd;
	private List<String> arrMenuSrl;
	private List<String> arrMenuStp;
	private List<String> arrMenuUseYn;
	
	public List<String> getArrMenuSrl() {
		return arrMenuSrl;
	}
	public void setArrMenuSrl(List<String> arrMenuSrl) {
		this.arrMenuSrl = arrMenuSrl;
	}
	public List<String> getArrMenuStp() {
		return arrMenuStp;
	}
	public void setArrMenuStp(List<String> arrMenuStp) {
		this.arrMenuStp = arrMenuStp;
	}
	public List<String> getArrMenuUseYn() {
		return arrMenuUseYn;
	}
	public void setArrMenuUseYn(List<String> arrMenuUseYn) {
		this.arrMenuUseYn = arrMenuUseYn;
	}
	public String getAuthCd() {
		return authCd;
	}
	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}
	
}
