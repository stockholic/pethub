package kr.pethub.webapp.api.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import kr.pethub.core.module.dao.MultiSqlSessionDaoSupport;
import kr.pethub.webapp.api.model.SiteLinkData;


@Repository
public class PetDao extends MultiSqlSessionDaoSupport{	
	
	public String selectPetUpdatedTime(){
		return  getString("selectPetUpdatedTime");
	}

	public int selectPetCount(SiteLinkData siteLinkData){
		return  getInt("selectPetCount",siteLinkData);
	}

	public List<SiteLinkData> selectPetList(SiteLinkData siteLinkData){
		return  selectList("selectPetList",siteLinkData);
	}

}
