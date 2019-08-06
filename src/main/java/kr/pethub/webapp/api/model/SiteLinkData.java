package kr.pethub.webapp.api.model;

import java.util.Date;
import java.util.List;

import kr.pethub.core.module.model.Pagination;

public class SiteLinkData extends Pagination{
	
	private String dataSrl;				// 데이터 일련번호
	private String siteSrl;					// 사이트 일련번호
	private String linkSrl;				// 링크 일련번호
	private String dataId;				// 데이터  아이디
	private String dataTitle;				// 데이터 제목
	private String dataLink;				// 데이터 링크
	private String dataImg;				// 데이터 이미지
	private String dataContent;		// 데이터  내용
	private Date regDt;					// 등록일

	private String siteNm;				//사인트 명
	private List<String> searchString;		//검색어
	
	public String getDataSrl() {
		return dataSrl;
	}
	public void setDataSrl(String dataSrl) {
		this.dataSrl = dataSrl;
	}
	public String getSiteSrl() {
		return siteSrl;
	}
	public void setSiteSrl(String siteSrl) {
		this.siteSrl = siteSrl;
	}
	public String getLinkSrl() {
		return linkSrl;
	}
	public void setLinkSrl(String linkSrl) {
		this.linkSrl = linkSrl;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
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
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
	public List<String> getSearchString() {
		return searchString;
	}
	public void setSearchString(List<String> searchString) {
		this.searchString = searchString;
	}
	

}
