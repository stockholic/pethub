package kr.pethub.webapp.admin.board.dao;


import java.util.List;


import org.springframework.stereotype.Repository;

import kr.pethub.core.module.dao.MultiSqlSessionDaoSupport;
import kr.pethub.webapp.admin.board.model.PetInfo;


@Repository
public class PetInfoDao extends MultiSqlSessionDaoSupport{	
	
	public int selectPetInfoCount(PetInfo petInfo){
		return getInt("selectPetInfoCount",petInfo);
	}

	public List<PetInfo> selectPetInfoList(PetInfo petInfo){
		return  selectList("selectPetInfoList",petInfo);
	}

	public PetInfo selectPetInfo(Integer petInfoSrl){
		return selectOne("selectPetInfo",petInfoSrl);
	}
	
	public int insertPetInfo(PetInfo petInfo){
		return  insert("insertPetInfo",petInfo);
	}

	public int updatePetInfo(PetInfo petInfo){
		return  update("updatePetInfo",petInfo);
	}

	public int deletePetInfo(PetInfo petInfo){
		return  delete("deletePetInfo",petInfo);
	}
	
}
