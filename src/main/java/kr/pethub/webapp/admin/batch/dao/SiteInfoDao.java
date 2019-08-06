package kr.pethub.webapp.admin.batch.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import kr.pethub.core.module.dao.MultiSqlSessionDaoSupport;
import kr.pethub.webapp.admin.batch.model.SiteInfo;


@Repository
public class SiteInfoDao extends MultiSqlSessionDaoSupport{	
	
	public int selectSiteInfoCount(SiteInfo siteInfo){
		return  getInt("selectSiteInfoCount",siteInfo);
	}

	public List<SiteInfo> selectSiteInfoList(SiteInfo siteInfo){
		return  selectList("selectSiteInfoList",siteInfo);
	}

	public SiteInfo selectSiteInfo(String siteSrl){
		return  selectOne("selectSiteInfo",siteSrl);
	}
	
	public int insertSiteInfo(SiteInfo siteInfo){
		return insert("insertSiteInfo",siteInfo);
	}
	
	public int updateSiteInfo(SiteInfo siteInfo){
		return  update("updateSiteInfo",siteInfo);
	}
	
	public int deleteSiteInfo(SiteInfo siteInfo){
		return  delete("deleteSiteInfo",siteInfo);
	}

	public int deleteSiteLink(SiteInfo siteInfo){
		return  delete("deleteSiteLink",siteInfo);
	}

}
