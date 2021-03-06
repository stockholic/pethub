package kr.pethub.webapp.admin.board.model;

import java.util.Date;

import kr.pethub.core.module.model.Pagination;


public class PetInfo extends Pagination{

	private Integer petSrl;
	private int num;
	private String petNm;
	private String petCd;
	private String petSize;
	private String intro;
	private String feature;
	private String care;
	private String feed;
	private String spec1;
	private String spec2;
	private String spec3;
	private String spec4;
	private String spec5;
	private Date regDt;
	private Date uptDt;
	private String link;
	private String petImg;
	private int fileCnt;
	private String fileInfo;
	private String searchString;
	private String orderBy;
	
	public Integer getPetSrl() {
		return petSrl;
	}
	public void setPetSrl(Integer petSrl) {
		this.petSrl = petSrl;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPetNm() {
		return petNm;
	}
	public void setPetNm(String petNm) {
		this.petNm = petNm;
	}
	public String getPetCd() {
		return petCd;
	}
	public void setPetCd(String petCd) {
		this.petCd = petCd;
	}
	public String getPetSize() {
		return petSize;
	}
	public void setPetSize(String petSize) {
		this.petSize = petSize;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getCare() {
		return care;
	}
	public void setCare(String care) {
		this.care = care;
	}
	public String getFeed() {
		return feed;
	}
	public void setFeed(String feed) {
		this.feed = feed;
	}
	public String getSpec1() {
		return spec1;
	}
	public void setSpec1(String spec1) {
		this.spec1 = spec1;
	}
	public String getSpec2() {
		return spec2;
	}
	public void setSpec2(String spec2) {
		this.spec2 = spec2;
	}
	public String getSpec3() {
		return spec3;
	}
	public void setSpec3(String spec3) {
		this.spec3 = spec3;
	}
	public String getSpec4() {
		return spec4;
	}
	public void setSpec4(String spec4) {
		this.spec4 = spec4;
	}
	public String getSpec5() {
		return spec5;
	}
	public void setSpec5(String spec5) {
		this.spec5 = spec5;
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPetImg() {
		return petImg;
	}
	public void setPetImg(String petImg) {
		this.petImg = petImg;
	}
	public int getFileCnt() {
		return fileCnt;
	}
	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}
	public String getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
}
