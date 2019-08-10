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
 * 종합유기견보호센터  http://www.zooseyo.or.kr https://mobile.zooseyo.or.kr:444
 * @author shkr
 *
 */

public class ZooseyoOrKr {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 강이지 무료  목록 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogList(String linkUrl, HttpServletResponse response) throws IOException {
		
		PrintWriter writer = null;
		
		String selector = "body > div > div.section08 li";
		String domain = "https://mobile.zooseyo.or.kr:444";
		String patternId ="(.*)(no=)([0-9]+)(.*)";
		String patternImg ="(background-image: url\\()'([^\\s']+)'(.*)";;

		Elements elements = JsoupUtil.getElements(linkUrl, selector);
		Collections.reverse(elements);
		
		int k = 1;
		try {
			writer = response.getWriter();
			for( Element ele :  elements) {
					
				if( ele.getElementsByTag("div").size() > 0 ) {
	
					//--------------------------------------------------------------------------------------------------------------- Start
				
					SiteLinkData cli  = new SiteLinkData();
					
					//제목 추출
					String dataTitle = ele.getElementsByTag("div").get(0).text() + " " + ele.getElementsByTag("div").get(1).getElementsByTag("p").get(0).text();
					logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
					cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle)) ;
					
					//링크 추출
					String dataLink = ele.getElementsByTag("div").get(0).getElementsByTag("a").attr("href");
					logger.debug( "LINK : {}" , dataLink );
					cli.setDataLink(dataLink);
					
					//이미지 추출
					
					String dataImg =  ele.getElementsByTag("div").get(0).getElementsByTag("a").attr("style");
					dataImg = domain + dataImg.replaceAll(patternImg, "$2");
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
	 * 강아지 무료 내용 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogContent( SiteLinkData siteLinkData ) throws IOException {

		String selector = "body > div > div.find_animal_get_view_area > div.viewer_area > div:nth-child(6) > table > tbody > tr > td";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
	}
	
	
	
	
}
