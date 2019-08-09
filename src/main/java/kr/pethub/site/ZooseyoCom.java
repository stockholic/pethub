package kr.pethub.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.pethub.core.module.model.SiteLinkData;
import kr.pethub.core.utils.JsoupUtil;

/**
 * 주세요닷컴 http://www.zooseyo.com
 * @author shkr
 *
 */

public class ZooseyoCom {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 강이지 목록 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogList(String linkUrl, HttpServletResponse response) throws IOException {
		
		PrintWriter writer = null;
		
		String selector = "body > table:nth-child(6) > tbody > tr > td:nth-child(2) > table:nth-child(9) > tbody > tr > td > table";
		String domain = "http://www.zooseyo.com";
		String patternId ="(.*)(no=)([0-9]+)(.*)";

		Elements elements = JsoupUtil.getElements(linkUrl, "euc-kr", selector);
		Collections.reverse(elements);
		
		int k = 1;
		try {
			writer = response.getWriter();
			for( Element ele :  elements) {
				
				if( ele.getElementsByTag("tr").hasAttr("onclick")  ) {
					
					//--------------------------------------------------------------------------------------------------------------- Start
					
					SiteLinkData cli  = new SiteLinkData();
					
					//제목 추출
					String dataTitle = ele.getElementsByTag("td").get(2).text() + " " + ele.getElementsByTag("td").get(3).text() + " " + ele.getElementsByTag("td").get(4).text()  + " " + ele.getElementsByTag("td").get(5).text();
					logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
					cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle)) ;
					
					//링크 추출
					String dataLink = domain + ele.getElementsByTag("td").get(6).getElementsByTag("a").attr("href").replace("..", "");
					logger.debug( "LINK : {}" , dataLink );
					cli.setDataLink(dataLink);
					
					//이미지 추출
					String dataImg = domain + ele.getElementsByTag("td").get(1).getElementsByTag("img").attr("src");
					logger.debug( "IMAGE : {}" , dataImg );
					cli.setDataImg(dataImg);	
					
					//아이디 추출
					String dataId = dataLink.replaceAll(patternId, "$3");
					logger.debug( "ID : {}" , dataId );
					cli.setDataId( dataId );
					
					//내용 접근 URL
					cli.setDataLink(dataLink);	
					
					//--------------------------------------------------------------------------------------------------------------- End
					
					ObjectMapper mapper = new ObjectMapper();
					cli.setNum(k++);
					getDogContent(cli);
					writer.write("data:" + mapper.writeValueAsString(cli)  + "\n\n" );
					writer.flush();
					
				}
				
			}
			
		}catch(Exception e) {
			e.getStackTrace();
		}finally {
			writer.close();
		}

	}
	
	/**
	 * 강아지 내용 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogContent( SiteLinkData siteLinkData ) throws IOException {

		String selector = "body > table:nth-child(2) > tbody > tr > td > table:nth-child(24) > tbody > tr > td";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , "euc-kr", selector );
		
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
		
		String selector = "body > table:nth-child(6) > tbody > tr > td:nth-child(2) > table:nth-child(9) > tbody > tr > td > table";
		String domain = "http://www.zooseyo.com";
		String patternId ="(.*)(no=)([0-9]+)(.*)";
		Elements elements = JsoupUtil.getElements(linkUrl, "euc-kr", selector);
		Collections.reverse(elements);
		
		int k = 1;
		try {
			writer = response.getWriter();
			for( Element ele :  elements) {
				
				if( ele.getElementsByTag("tr").hasAttr("onclick")  ) {
					
					//--------------------------------------------------------------------------------------------------------------- Start
					
					SiteLinkData cli  = new SiteLinkData();
					
					//제목 추출
					String dataTitle = ele.getElementsByTag("td").get(2).text() + " " + ele.getElementsByTag("td").get(3).text() + " " + ele.getElementsByTag("td").get(4).text()  + " " + ele.getElementsByTag("td").get(5).text();
					logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
					cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle)) ;
					
					//링크 추출
					String dataLink = domain + ele.getElementsByTag("td").get(6).getElementsByTag("a").attr("href").replace("..", "");
					logger.debug( "LINK : {}" , dataLink );
					cli.setDataLink(dataLink);
					
					//이미지 추출
					String dataImg = domain + ele.getElementsByTag("td").get(1).getElementsByTag("img").attr("src");
					logger.debug( "IMAGE : {}" , dataImg );
					cli.setDataImg(dataImg);	
					
					//아이디 추출
					String dataId = dataLink.replaceAll(patternId, "$3");
					logger.debug( "ID : {}" , dataId );
					cli.setDataId( dataId );
					
					//내용 접근 URL
					cli.setDataLink(dataLink);	
					
					//--------------------------------------------------------------------------------------------------------------- End
					
					ObjectMapper mapper = new ObjectMapper();
					cli.setNum(k++);
					getCatContent(cli);
					writer.write("data:" + mapper.writeValueAsString(cli)  + "\n\n" );
					writer.flush();
					
				}
				
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

		String selector = "body > table:nth-child(2) > tbody > tr > td > table:nth-child(24) > tbody > tr > td";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , "euc-kr", selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
	}
	
	
}
