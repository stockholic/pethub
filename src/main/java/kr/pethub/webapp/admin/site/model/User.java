package kr.pethub.webapp.admin.site.model;

import java.util.Date;

import kr.pethub.core.module.model.Pagination;

public class User extends Pagination{

	private Integer userSrl;
	private String userId;
	private String password;
	private String userNm;
	private String email;
	private String useYn;
	private String roleCd;
	private String roleNm;
	private Date regDate;
	
	public Integer getUserSrl() {
		return userSrl;
	}
	public void setUserSrl(Integer userSrl) {
		this.userSrl = userSrl;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
	
}
