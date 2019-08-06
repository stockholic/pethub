package kr.pethub.webapp.admin.batch.model;

import java.util.Date;

import kr.pethub.core.module.model.Pagination;

public class SiteLink extends Pagination{
	
	
	private Integer num;					// 번호
	private String linkSrl;				// 일련번호
	private String siteSrl;					// site_info 참조번호
	private String siteNm;				// 사이트 명
	private String linkCd;				// 링크코드
	private String linkNm;				// 링크명
	private String linkUrl;				// 링크 url
	private String linkCls;				// 실행클래스
	private String linkMtdLst;			// 목록 실행메소드
	private String linkMtdCts;			// 내용 실행메소드
	private String linkCnt;				// 링크수
	private String batchItv;				// 배치 간격
	private String useYn;					// 사용여부
	private Date regDt;					// 등록일
	private Date uptDt;					// 수정일
	private Date excDt;					// 실행일
	
	private String searchString;			// 검색어	
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getLinkSrl() {
		return linkSrl;
	}
	public void setLinkSrl(String linkSrl) {
		this.linkSrl = linkSrl;
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
	public String getLinkCd() {
		return linkCd;
	}
	public void setLinkCd(String linkCd) {
		this.linkCd = linkCd;
	}
	public String getLinkNm() {
		return linkNm;
	}
	public void setLinkNm(String linkNm) {
		this.linkNm = linkNm;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getLinkCls() {
		return linkCls;
	}
	public void setLinkCls(String linkCls) {
		this.linkCls = linkCls;
	}
	public String getLinkMtdLst() {
		return linkMtdLst;
	}
	public void setLinkMtdLst(String linkMtdLst) {
		this.linkMtdLst = linkMtdLst;
	}
	public String getLinkMtdCts() {
		return linkMtdCts;
	}
	public void setLinkMtdCts(String linkMtdCts) {
		this.linkMtdCts = linkMtdCts;
	}
	public String getLinkCnt() {
		return linkCnt;
	}
	public void setLinkCnt(String linkCnt) {
		this.linkCnt = linkCnt;
	}
	public String getBatchItv() {
		return batchItv;
	}
	public void setBatchItv(String batchItv) {
		this.batchItv = batchItv;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Date getUptDt() {
		return uptDt;
	}
	public void setUptDt(Date uptDt) {
		this.uptDt = uptDt;
	}
	public Date getExcDt() {
		return excDt;
	}
	public void setExcDt(Date excDt) {
		this.excDt = excDt;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
} 
