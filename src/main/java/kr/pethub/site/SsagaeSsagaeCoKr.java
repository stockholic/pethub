package kr.pethub.site;

import java.io.IOException;

import java.io.PrintWriter;
import java.net.URISyntaxException;
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
 * 유기견보호센터 http://www.animal.or.kr
 * @author shkr
 *
 */
public class SsagaeSsagaeCoKr {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 강이지 목록 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogList(String linkUrl, HttpServletResponse response) throws IOException {
		
		PrintWriter writer = null;
		
		String selector = "#fboardlist > ul > li";
		String domain = "http://www.ssagae-ssagae.co.kr";
		String patternId ="(.*)(wr_id=)([0-9]+)";

		Elements elements = JsoupUtil.getElements(linkUrl, selector);
		Collections.reverse(elements);
		
		int k = 1;
		try {
			writer = response.getWriter();
			
			for( Element ele :  elements) {
				
				//--------------------------------------------------------------------------------------------------------------- Start
				
				SiteLinkData cli  = new SiteLinkData();
				
				//제목 추출
				String dataTitle = ele.select(".fz_gallery_title").text().replace("파일첨부" ,"").replace("새글" ,"");
				logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
				cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle)) ;
				
				//링크 추출
				String dataLink = ele.getElementsByTag("a").attr("href");
				logger.debug( "LINK : {}" , dataLink );
				cli.setDataLink(dataLink);
				
				//이미지 추출
				String dataImg =  ele.select(".fz_gallery_thumb").first().getElementsByTag("img").attr("src");
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
	 * @throws URISyntaxException 
	 */
	public void getDogContent( SiteLinkData siteLinkData ) throws IOException, URISyntaxException {

		String selector = ".view-content";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
		
	}
	
	
}
