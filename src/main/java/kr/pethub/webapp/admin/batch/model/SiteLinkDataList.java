package kr.pethub.webapp.admin.batch.model;

import java.util.List;

import kr.pethub.core.module.model.SiteLinkData;

public class SiteLinkDataList{
	
	private String siteSrl;
	private String linkSrl;
	private List<SiteLinkData> dataList;
	
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
	public List<SiteLinkData> getDataList() {
		return dataList;
	}
	public void setDataList(List<SiteLinkData> dataList) {
		this.dataList = dataList;
	}
	
} 
