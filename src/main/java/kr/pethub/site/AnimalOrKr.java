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
 * 유기견보호센터 http://www.animal.or.kr
 * @author shkr
 *
 */
public class AnimalOrKr {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 강이지 목록 추출
	 * @return
	 * @throws IOException 
	 * @throws URISyntaxException 
	 */
	
	public List<SiteLinkData> getDogList(String linkUrl, ConsoleLog consoleLog) throws IOException, URISyntaxException {
		
		
		List<SiteLinkData> list = new ArrayList<SiteLinkData>();
		
		String domain = "http://www.animal.or.kr";
		String selector = "#fboardlist table .mw_basic_list_subject_m";
		String patternId ="(.*)(wr_id=)([0-9]+)";

		Elements elements = JsoupUtil.getElements(linkUrl, "euc-kr", selector);
		Collections.reverse(elements);
		
		int k = 1;
		for( Element ele :  elements) {

			//--------------------------------------------------------------------------------------------------------------- Start
			
			SiteLinkData cli  = new SiteLinkData();
			
			//제목 추출
			String dataTitle = ele.getElementsByClass("mw_basic_list_subject_desc").text();
			logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
			cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle));


			//링크 추출
			String dataLink = ele.select("a").attr("href").replace("..", ""); 
			logger.debug( "LINK : {}" , domain + dataLink );
			cli.setDataLink(domain + dataLink);

			//이미지 추출
			String dataImg = ele.getElementsByTag("img").attr("src").replace("..", ""); ;
			logger.debug( "IMAGE : {}" , domain + dataImg );
			cli.setDataImg(domain + dataImg);	
			
			//아이디 추출
			String dataId = dataLink.replaceAll(patternId, "$3");
			logger.debug( "ID : {}" , dataId );
			cli.setDataId( dataId );
			
			//내용 접근 URL
			cli.setDataLink(cli.getDataLink());	
			
			list.add(cli);

			//--------------------------------------------------------------------------------------------------------------- End
			
			ObjectMapper mapper = new ObjectMapper(); 
			cli.setNum(k++);
			getDogContent(cli, consoleLog);
			consoleLog.getConsole().send(mapper.writeValueAsString(cli) );
			
		}
		


		return list;
	}
	
	/**
	 * 강아지 내용 추출
	 * @return
	 * @throws IOException 
	 * @throws URISyntaxException 
	 */
	public void getDogContent( SiteLinkData siteLinkData, ConsoleLog consoleLog ) throws IOException, URISyntaxException {

		String selector = "#view_content";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , "euc-kr", selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
		
	}
	
	
}
