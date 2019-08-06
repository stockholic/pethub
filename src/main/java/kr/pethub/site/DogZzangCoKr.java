package kr.pethub.site;

import java.io.IOException;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.pethub.core.module.model.SiteLinkData;
import kr.pethub.core.module.service.ConsoleLog;
import kr.pethub.core.utils.JsoupUtil;

/**
 * 도그짱 http://www.dog-zzang.co.kr 
 * @author shkr
 *
 */

public class DogZzangCoKr {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 강이지 안심문양 목록 추출
	 * @return
	 * @throws IOException 
	 */
	
	public List<SiteLinkData> getDogList1(String linkUrl, ConsoleLog consoleLog) throws IOException, URISyntaxException {
		
		List<SiteLinkData> list = new ArrayList<SiteLinkData>();
		
		String selector = "body > table > tbody > tr > td > table:nth-child(6) > tbody > tr > td:nth-child(2) > table:nth-child(2) > tbody > tr > td > table";
		String domain = "http://www.dog-zzang.co.kr";
		String patternLink ="(window\\.open\\()'([^\\s']+)'(.*)";
		String patternId ="(.*)(no=)([0-9]+)(.*)";

		Elements elements = JsoupUtil.getElements(linkUrl,"euc-kr", selector);
		Collections.reverse(elements);
		
		int k = 1;
		for( Element ele :  elements) {
			
			
			if( ele.getElementsByTag("tr").hasAttr("onmouseover")  ) {
				//--------------------------------------------------------------------------------------------------------------- Start
				
				SiteLinkData cli  = new SiteLinkData();
				
				//제목 추출
				String dataTitle = ele.getElementsByTag("td").get(2).text() + " " + ele.getElementsByTag("td").get(3).text() + " " + ele.getElementsByTag("td").get(4).text() + " " + ele.getElementsByTag("td").get(5).text() + " " + ele.getElementsByTag("td").get(6).text();
				logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
				cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle)); 
				
				//링크 추출
				String dataLink = domain + ele.getElementsByTag("td").get(1).getElementsByTag("a").attr("onclick");
				dataLink = dataLink.replaceAll(patternLink, "$2");
				logger.debug( "LINK : {}" , dataLink );
				cli.setDataLink(dataLink);
				
				//이미지 추출
				String dataImg = domain +"/dog_sale/"+ ele.getElementsByTag("td").get(1).getElementsByTag("img").attr("src").replace("./", "");
				dataImg = (dataImg.contains("free_no_image.gif"))  ? "" : dataImg;
				logger.debug( "IMAGE : {}" , dataImg );
				cli.setDataImg(dataImg);	
				
				//아이디 추출
				String dataId = dataLink.replaceAll(patternId, "$3");
				logger.debug( "ID : {}" , dataId );
				cli.setDataId( dataId );
				
				//내용 접근 URL
				cli.setDataLink(dataLink);
				
				list.add(cli);

				//--------------------------------------------------------------------------------------------------------------- End
				
				ObjectMapper mapper = new ObjectMapper(); 
				cli.setNum(k++);
				getDogContent1(cli, consoleLog);
				consoleLog.getConsole().send(mapper.writeValueAsString(cli) );
				
			}
			
		}
		

		return list;
	}
	
	/**
	 * 강아지 안심분양 내용 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogContent1( SiteLinkData siteLinkData, ConsoleLog consoleLog ) throws IOException, URISyntaxException {
		
		String selector = "body > div.mask > table:nth-child(2) > tbody > tr:nth-child(2) > td > table p";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() ,"euc-kr", selector );
		
		String str = "";
		for( Element ele :  contents) {
			if( ele.attr("style").contains("line-height") ) {
				str = ele.text();
			}
		}
		
		String dataContent = JsoupUtil.specialCharacterRemove(str);		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
	}
	
	
	/**
	 * 강이지 무료분양 목록 추출
	 * @return
	 * @throws IOException 
	 */
	
	public List<SiteLinkData> getDogList2(String linkUrl) throws IOException {
		
		List<SiteLinkData> list = new ArrayList<SiteLinkData>();
		
		String selector = "body > table > tbody > tr > td > table:nth-child(6) > tbody > tr > td:nth-child(2) > table:nth-child(6) > tbody > tr > td > table";
		String domain = "http://www.dog-zzang.co.kr";
		String patternId ="(.*)(no=)([0-9]+)(.*)";

		Elements elements = JsoupUtil.getElements(linkUrl,"euc-kr", selector);
		Collections.reverse(elements);
		
		int k = 1;
		for( Element ele :  elements) {
			
			
			if( ele.getElementsByTag("tr").hasAttr("onmouseover")  ) {
				logger.debug("--------------------------------------------------------------------------------------------------------------- " + (k++));
				
				SiteLinkData cli  = new SiteLinkData();
				
				//제목 추출
				String dataTitle = ele.getElementsByTag("td").get(2).text() + " " + ele.getElementsByTag("td").get(3).text() + " " + ele.getElementsByTag("td").get(4).text() + " " + ele.getElementsByTag("td").get(5).text() + " " + ele.getElementsByTag("td").get(6).text();
				logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
				cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle)); 
				
				//링크 추출
				String dataLink = domain + ele.getElementsByTag("td").get(2).getElementsByTag("a").attr("href");
				logger.debug( "LINK : {}" , dataLink );
				cli.setDataLink(dataLink);
				
				//이미지 추출
				String dataImg = domain +"/dog_sale/"+ ele.getElementsByTag("td").get(1).getElementsByTag("img").attr("src").replace("./", "");
				dataImg = (dataImg.contains("free_no_image.gif"))  ? "" : dataImg;
				logger.debug( "IMAGE : {}" , dataImg );
				cli.setDataImg(dataImg);	
				
				//아이디 추출
				String dataId = dataLink.replaceAll(patternId, "$3");
				logger.debug( "ID : {}" , dataId );
				cli.setDataId( dataId );
				
				//내용 접근 URL
				cli.setDataLink(dataLink);	
				
				list.add(cli);
				
			}
			
		}
		
		return list;
	}
	
	
	/**
	 * 강아지 무료분양 내용 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogContent2( SiteLinkData siteLinkData ) throws IOException {
		
		String selector = "body > div.mask > table:nth-child(2) > tbody > tr:nth-child(3) > td > table  p";

		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() ,"euc-kr", selector );
		
		String str = "";
		for( Element ele :  contents) {
			if( ele.attr("style").contains("line-height") ) {
				str = ele.text();
			}
		}
		
		String dataContent = JsoupUtil.specialCharacterRemove(str);		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
	}
	
	
}
