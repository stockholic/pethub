package kr.pethub.webapp.admin.batch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pethub.webapp.admin.batch.dao.SiteLinkDataDao;
import kr.pethub.webapp.admin.batch.model.SiteInfo;
import kr.pethub.webapp.admin.batch.model.SiteLinkData;

@Service
public class SiteLinkDataService {

	@Autowired
	private SiteLinkDataDao siteLinkDataDao;
	
	/**
	 * 목록
	 * @return
	 */
	public List<SiteInfo> selectSiteInfoList(){
		return siteLinkDataDao.selectSiteInfoList();
	}
	
	/**
	 * 목록 수	
	 * @param siteLinkData
	 * @return
	 */
	public int selectSiteLinkDataCount(SiteLinkData siteLinkData) {
		return siteLinkDataDao.selectSiteLinkDataCount(siteLinkData);
	}

	/**
	 * 목록
	 * @param siteLinkData
	 * @return
	 */
	public List<SiteLinkData> selectSiteLinkDataList(SiteLinkData siteLinkData) {
		
		siteLinkData.setTotalRow(selectSiteLinkDataCount(siteLinkData));
		List<SiteLinkData> list =  siteLinkDataDao.selectSiteLinkDataList(siteLinkData);
		
		int num = siteLinkData.getTotalRow() - siteLinkData.getRowStart();
		
		for (SiteLinkData si : list) {
			si.setNum(num);
			num--;
		}
		
		return list;
	}
	
	/**
	 * 조회
	 * @param siteLinkData
	 * @return
	 */
	public SiteLinkData selectSiteLinkData(String siteSrl) {
		return  siteLinkDataDao.selectSiteLinkData(siteSrl);
	}

	/**
	 * 삭제
	 * @param siteLinkData
	 * @return
	 */
	public int  deleteSiteLinkData(SiteLinkData siteLinkData) {
		
		siteLinkDataDao.deleteSiteLinkData(siteLinkData);
		
		return 1;
	}

}
