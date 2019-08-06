package kr.pethub.webapp.admin.batch.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import kr.pethub.core.module.dao.MultiSqlSessionDaoSupport;
import kr.pethub.webapp.admin.batch.model.SiteInfo;
import kr.pethub.webapp.admin.batch.model.SiteLinkData;


@Repository
public class SiteLinkDataDao extends MultiSqlSessionDaoSupport{	
	
	public List<SiteInfo> selectSiteInfoList(){
		return  selectList("selectSiteInfoList");
	}


	public int selectSiteLinkDataCount(SiteLinkData siteLinkData){
		return  getInt("selectSiteLinkDataCount",siteLinkData);
	}

	public List<SiteLinkData> selectSiteLinkDataList(SiteLinkData siteLinkData){
		return  selectList("selectSiteLinkDataList",siteLinkData);
	}

	public SiteLinkData selectSiteLinkData(String siteSrl){
		return  selectOne("selectSiteLinkData",siteSrl);
	}
	
	public int deleteSiteLinkData(SiteLinkData siteLinkData){
		return  delete("deleteSiteLinkData",siteLinkData);
	}

}
