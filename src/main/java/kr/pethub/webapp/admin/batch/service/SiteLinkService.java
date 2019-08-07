package kr.pethub.webapp.admin.batch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pethub.core.module.model.SiteLinkData;
import kr.pethub.core.utils.JsoupUtil;
import kr.pethub.webapp.admin.batch.dao.SiteLinkDao;
import kr.pethub.webapp.admin.batch.model.SiteInfo;
import kr.pethub.webapp.admin.batch.model.SiteLink;
import kr.pethub.webapp.admin.batch.model.SiteLinkDataList;

@Service
public class SiteLinkService {

	@Autowired
	private SiteLinkDao siteLinkDao;
	
	/**
	 * 사이트 목록
	 * @return
	 */
	public List<SiteInfo> selectSiteInfoList(){
		return siteLinkDao.selectSiteInfoList();
	}
	
	/**
	 * 목록 수	
	 * @param siteLink
	 * @return
	 */
	public int selectSiteLinkCount(SiteLink siteLink) {
		return siteLinkDao.selectSiteLinkCount(siteLink);
	}

	/**
	 * 목록
	 * @param siteLink
	 * @return
	 */
	public List<SiteLink> selectSiteLinkList(SiteLink siteLink) {
		
		siteLink.setTotalRow(selectSiteLinkCount(siteLink));
		List<SiteLink> list =  siteLinkDao.selectSiteLinkList(siteLink);
		
		int num = siteLink.getTotalRow() - siteLink.getRowStart();
		
		for (SiteLink si : list) {
			si.setNum(num);
			num--;
		}
		
		return list;
	}
	
	/**
	 * 조회
	 * @param siteLink
	 * @return
	 */
	public SiteLink selectSiteLink(String siteSrl) {
		return  siteLinkDao.selectSiteLink(siteSrl);
	}

	/**
	 * 등록
	 * @param siteLink
	 * @return
	 */
	public int  insertSiteLink(SiteLink siteLink) {
		return  siteLinkDao.insertSiteLink(siteLink);
	}
	
	/**
	 * 수정
	 * @param siteLink
	 * @return
	 */
	public int  updateSiteLink(SiteLink siteLink) {
		return  siteLinkDao.updateSiteLink(siteLink);
	}
	
	/**
	 * 삭제
	 * @param siteLink
	 * @return
	 */
	public int  deleteSiteLink(SiteLink siteLink) {
		
		siteLinkDao.deleteSiteLink(siteLink);
		
		return 1;
	}

	/**
	 * 사이트 데이터 등록
	 * @param siteLink
	 * @return
	 */
	public int insertSiteLinkData(SiteLinkDataList siteLinkDataList) {
		
		int linkCnt = 0;
		for  ( SiteLinkData siteLinkData :  siteLinkDataList.getDataList()  ) {
			
			//키값(ID)이 올바른것 만 저장, 숫자 이여야 함 
			if(  JsoupUtil.isRegex("^[0-9]+$", siteLinkData.getDataId() ) ) { 
				
				siteLinkData.setSiteSrl(siteLinkDataList.getSiteSrl());
				siteLinkData.setLinkSrl(siteLinkDataList.getLinkSrl());
				
				if( siteLinkDao.updateSiteLinkData(siteLinkData) == 0 ) {
					siteLinkDao.insertSiteLinkData(siteLinkData);
				}
				
				linkCnt++;
				
			}
			
		}
		
		//등록 후 사이트링크 처리 업데이트
		if(siteLinkDataList.getDataList().size() > 0) {
			
			SiteLink siteLink = new SiteLink();
			siteLink.setLinkCnt( Integer.toString(linkCnt) );
			siteLink.setLinkSrl(siteLinkDataList.getLinkSrl());
			
			siteLinkDao.updateSiteLinkCnt(siteLink);
		}
		
		return  1;
	}
	
	
	

}
