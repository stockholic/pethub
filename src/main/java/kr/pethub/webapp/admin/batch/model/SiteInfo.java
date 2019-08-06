package kr.pethub.webapp.admin.batch.model;

import java.util.Date;

import kr.pethub.core.module.model.Pagination;

public class SiteInfo extends Pagination{
	
	private Integer num;					// 번호
	private String siteSrl;					// 사이트 일련번호
	private String siteNm;				// 사이트명 
	private String siteUrl;				// 사이트 URL					
	private String siteEtc;				// 기타	
	private Date regDt;					// 등록일	

	private String searchString;			// 검색어	
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getSiteSrl() {
		return siteSrl;
	}
	public void setSiteSrl(String siteSrl) {
		this.siteSrl = siteSrl;
	}
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public String getSiteEtc() {
		return siteEtc;
	}
	public void setSiteEtc(String siteEtc) {
		this.siteEtc = siteEtc;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
} 
