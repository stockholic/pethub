package kr.pethub.webapp.admin.site.model;

import java.util.List;

public class Role{

	private String roleSrl;
	private String roleCd;
	private String roleNm;
	private String useYn;
	private List<String> arrRoleSrl;
	
	public String getRoleSrl() {
		return roleSrl;
	}
	public void setRoleSrl(String roleSrl) {
		this.roleSrl = roleSrl;
	}
	public String getRoleCd() {
		return roleCd;
	}
	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}
	public String getRoleNm() {
		return roleNm;
	}
	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public List<String> getArrRoleSrl() {
		return arrRoleSrl;
	}
	public void setArrRoleSrl(List<String> arrRoleSrl) {
		this.arrRoleSrl = arrRoleSrl;
	}
	
}
