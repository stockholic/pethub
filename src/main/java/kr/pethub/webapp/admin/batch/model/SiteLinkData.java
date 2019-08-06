package kr.pethub.webapp.admin.batch.model;

import java.util.Date;
import java.util.List;

import kr.pethub.core.module.model.Pagination;

public class SiteLinkData extends Pagination{
	
	
	private Integer num;					// 번호
	private String dataId;				// 데이터 아이디
	private String dataSrl;				// 데이터 일련번호z
	private String linkSrl;				// 일련번호
	private String siteSrl;					// site_info 참조번호
	private String siteNm;				// 사이트 명
	private String linkCd;				// 링크코드
	private String linkNm;				// 링크명
	private String dataTitle;				// 데이터 제목
	private String dataLink;				// 데이터 링크
	private String dataImg;				// 데이터 이미지
	private String dataContent;		// 데이터  내용
	private Date regDt;					// 등록일
	private Date uptDt;					// 수정일
	
	private String searchString;			// 검색어
	private List<String> searchStringList;			// 검색어 리스트
	
	public Integer getNum() {
		return num;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getDataSrl() {
		return dataSrl;
	}
	public void setDataSrl(String dataSrl) {
		this.dataSrl = dataSrl;
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
	public String getDataTitle() {
		return dataTitle;
	}
	public void setDataTitle(String dataTitle) {
		this.dataTitle = dataTitle;
	}
	public String getDataLink() {
		return dataLink;
	}
	public void setDataLink(String dataLink) {
		this.dataLink = dataLink;
	}
	public String getDataImg() {
		return dataImg;
	}
	public void setDataImg(String dataImg) {
		this.dataImg = dataImg;
	}
	public String getDataContent() {
		return dataContent;
	}
	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
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
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public List<String> getSearchStringList() {
		return searchStringList;
	}
	public void setSearchStringList(List<String> searchStringList) {
		this.searchStringList = searchStringList;
	}
	
} 
