package kr.pethub.webapp.admin.batch.dao;


import java.util.List;


import org.springframework.stereotype.Repository;

import kr.pethub.core.module.dao.MultiSqlSessionDaoSupport;
import kr.pethub.core.module.model.SiteLinkData;
import kr.pethub.webapp.admin.batch.model.SiteInfo;
import kr.pethub.webapp.admin.batch.model.SiteLink;


@Repository
public class SiteLinkDao extends MultiSqlSessionDaoSupport{	
	
	public List<SiteInfo> selectSiteInfoList(){
		return  selectList("selectSiteInfoList");
	}


	public int selectSiteLinkCount(SiteLink siteLink){
		return  getInt("selectSiteLinkCount",siteLink);
	}

	public List<SiteLink> selectSiteLinkList(SiteLink siteLink){
		return  selectList("selectSiteLinkList",siteLink);
	}

	public SiteLink selectSiteLink(String siteSrl){
		return  selectOne("selectSiteLink",siteSrl);
	}
	
	public int insertSiteLink(SiteLink siteLink){
		return insert("insertSiteLink",siteLink);
	}
	
	public int updateSiteLink(SiteLink siteLink){
		return  update("updateSiteLink",siteLink);
	}
	
	public int deleteSiteLink(SiteLink siteLink){
		return  delete("deleteSiteLink",siteLink);
	}
	
	public int updateSiteLinkData(SiteLinkData siteLinkData){
		return insert("updateSiteLinkData",siteLinkData);
	}

	public int insertSiteLinkData(SiteLinkData siteLinkData){
		return insert("insertSiteLinkData",siteLinkData);
	}

}
