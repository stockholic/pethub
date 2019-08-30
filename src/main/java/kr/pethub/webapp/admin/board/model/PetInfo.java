package kr.pethub.webapp.admin.board.model;

import java.util.Date;

import kr.pethub.core.module.model.Pagination;


public class PetInfo extends Pagination{

	private Integer petSrl;
	private int num;
	private String title;
	private String cts1;
	private Date regDt;
	private int fileCnt;
	private String fileInfo;
	
	private String searchString;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCts1() {
		return cts1;
	}
	public void setCts1(String cts1) {
		this.cts1 = cts1;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
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
	
	
}
