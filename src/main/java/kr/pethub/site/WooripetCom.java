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
 * 우리펫 http://www.우리펫.com
 * @author shkr
 *
 */
public class WooripetCom {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 강이지 목록 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogList(String linkUrl, HttpServletResponse response) throws IOException {
		
		PrintWriter writer = null;
		
		String selector = "#wrap .sct_li";
		String patternId ="(.*)(it_id=)([0-9]+)";

		Elements elements = JsoupUtil.getElements(linkUrl, selector);
		Collections.reverse(elements);
		
		int k = 1;
		try {
			writer = response.getWriter();
			for( Element ele :  elements) {
				
				//--------------------------------------------------------------------------------------------------------------- Start
				
				SiteLinkData cli  = new SiteLinkData();
				
				//제목 추출
				String dataTitle = ele.getElementsByClass("sct_txt").text() + " " +  ele.getElementsByClass("sct_cost").text();
				logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
				cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle));
				
				//링크 추출
				String dataLink = ele.select("a").attr("href"); 
				logger.debug( "LINK : {}" , dataLink );
				cli.setDataLink(dataLink);
				
				//이미지 추출
				String dataImg = ele.getElementsByTag("img").attr("src");
				logger.debug( "IMAGE : {}" , dataImg );
				cli.setDataImg(dataImg);	
				
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
	 * 강아지 내용 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogContent( SiteLinkData siteLinkData ) throws IOException {

		String selector = "#sit  section";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
	}
	
	
}
