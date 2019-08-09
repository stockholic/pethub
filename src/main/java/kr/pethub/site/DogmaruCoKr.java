package kr.pethub.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.pethub.core.module.model.SiteLinkData;
import kr.pethub.core.utils.JsoupUtil;

/**
 * 도그마루 https://dogmaru.co.kr
 * @author shkr
 *
 */
public class DogmaruCoKr {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 강이지 목록 추출
	 * @return
	 * @throws IOException 
	 */
	
	public void getDogList(String linkUrl, HttpServletResponse response) throws IOException {
		
		PrintWriter writer = null;
		
		String selector = "#post_card_b201807025b39d19969307 .ma-item";
		String domain = "https://dogmaru.co.kr";
		String patternImg = "(background-image: url)\\(([^\\s]+)\\)(.*)";
		String patternId ="(.*)(idx=)([0-9]+)(.*)";

		Elements elements = JsoupUtil.getElements(linkUrl, selector);
		Collections.reverse(elements);
		
		int k = 1;
		try {
			writer = response.getWriter();
			
			for( Element ele :  elements) {
				
				//--------------------------------------------------------------------------------------------------------------- Start
				
				SiteLinkData cli  = new SiteLinkData();
				
				//제목 추출
				String dataTitle = ele.select(".title span").text();
				logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
				cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle)) ;
				
				//링크 추출
				String dataLink = ele.select("a").attr("href"); 
				logger.debug( "LINK : {}" , domain + dataLink );
				cli.setDataLink(domain + dataLink);
				
				//이미지 추출
				String dataImg = ele.select(".card_wrapper").attr("style");
				dataImg = dataImg.replaceAll(patternImg, "$2");
				logger.debug( "IMAGE : {}" , dataImg );
				cli.setDataImg(dataImg);;	
				
				//아이디 추출
				String dataId = dataLink.replaceAll(patternId, "$3");
				logger.debug( "ID : {}" , dataId );
				cli.setDataId( dataId );
				
				//내용 접근 URL
				cli.setDataLink(cli.getDataLink());	
				
				//--------------------------------------------------------------------------------------------------------------- End
				
				ObjectMapper mapper = new ObjectMapper();
				cli.setNum(k++);
				getDogContent(cli);
				writer.write("data:" + mapper.writeValueAsString(cli)  + "\n\n" );
				writer.flush();
			}
			
		}catch(Exception e) {
			e.getStackTrace();
		}finally {
			writer.close();
		}


	}
	
	/**
	 * 강이지 내용 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogContent( SiteLinkData siteLinkData ) throws IOException {

		String selector = "#w201901175c3fe684e2c7c .tableStrong";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
	}
	
	/**
	 * 고양이 목록 추출
	 * @return
	 * @throws IOException 
	 */
	public void getCatList(String linkUrl, HttpServletResponse response) throws IOException {	
		
		PrintWriter writer = null;
		
		String selector = "#post_card_b201807025b39db43721d8 .ma-item";
		String domain = "https://dogmaru.co.kr";
		String patternImg = "(background-image: url)\\(([^\\s]+)\\)(.*)";
		String patternId ="(.*)(idx=)([0-9]+)(.*)";

		Elements elements = JsoupUtil.getElements(linkUrl, selector);
		Collections.reverse(elements);
		
		int k = 1;
		try {
			writer = response.getWriter();
			for( Element ele :  elements) {
				
				//--------------------------------------------------------------------------------------------------------------- Start
				
				SiteLinkData cli  = new SiteLinkData();
				
				//제목 추출
				String dataTitle = ele.select(".title span").text();
				logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
				cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle));
				
				//링크 추출
				String dataLink = ele.select("a").attr("href"); 
				logger.debug( "LINK : {}" , domain + dataLink );
				cli.setDataLink(domain + dataLink);
				
				//이미지 추출
				String dataImg = ele.select(".card_wrapper").attr("style");
				dataImg = dataImg.replaceAll(patternImg, "$2");
				logger.debug( "IMAGE : {}" , dataImg );
				cli.setDataImg(dataImg);;	
				
				//아이디 추출
				String dataId = dataLink.replaceAll(patternId, "$3");
				logger.debug( "ID : {}" , dataId );
				cli.setDataId( dataId );
				
				//내용 접근 URL
				cli.setDataLink(cli.getDataLink());	
				
				//--------------------------------------------------------------------------------------------------------------- End
				
				ObjectMapper mapper = new ObjectMapper();
				cli.setNum(k++);
				getCatContent(cli);
				writer.write("data:" + mapper.writeValueAsString(cli)  + "\n\n" );
				writer.flush();
				
			}
			
		}catch(Exception e) {
			e.getStackTrace();
		}finally {
			writer.close();
		}

	}
	
	/**
	 * 고양이 내용 추출
	 * @return
	 * @throws IOException 
	 */
	public void getCatContent( SiteLinkData siteLinkData ) throws IOException {

		String selector = "#w201901175c40056b2382a .tableStrong";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
	}
	
	
}
