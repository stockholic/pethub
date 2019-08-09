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
 * KaKao Dog  http://www.kakaodog.co.kr
 * @author shkr
 *
 */
public class KakaodogCoKr {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 강이지 목록 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogList(String linkUrl, HttpServletResponse response) throws IOException {
		
		PrintWriter writer = null;
		
		String domain = "http://www.kakaodog.co.kr";
		String selector = ".left tr";
		String patternLink ="(javascript:VipView\\()'([0-9]+)'(.*)";

		Elements elements = JsoupUtil.getElements(linkUrl, "euc-kr", selector);
		Collections.reverse(elements);
		
		int k = 1;
		try {
			writer = response.getWriter();
			for( Element ele :  elements) {
				//--------------------------------------------------------------------------------------------------------------- Start
				
				SiteLinkData cli  = new SiteLinkData();
				
				if( ele.getElementsByTag("tr").hasAttr("onmouseover")  ) {
					//제목 추출
					String dataTitle = ele.getElementsByTag("td").get(5).text() + " " + ele.getElementsByTag("td").get(6).text();
					logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
					cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle));
		
					//링크 추출
					String dataLink = ele.getElementsByTag("td").get(5).getElementsByTag("a").attr("href");
					dataLink = dataLink.replaceAll(patternLink, "$2");
					logger.debug( "LINK : {}" , domain + "/gnu/vip_view.php?id=" + dataLink );
					cli.setDataLink(domain + "/gnu/vip_view.php?id=" + dataLink);
		
					//이미지 추출
					String dataImg = ele.getElementsByTag("td").get(1).getElementsByTag("img").attr("src");
					dataImg = dataImg.contains("camera4.gif") ? "" : domain + "/gnu/" + dataImg; 
					logger.debug( "IMAGE : {}" , dataImg);
					cli.setDataImg(dataImg);	
					
					//아이디 추출
					logger.debug( "ID : {}" , dataLink );
					cli.setDataId( dataLink );
					
					//내용 접근 URL
					cli.setDataLink(cli.getDataLink());	
					
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

		String selector = "body > table > tbody > tr:nth-child(6) > td > table:nth-child(3) > tbody > tr:nth-child(2) > td";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , "euc-kr", selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
		
	}
	
	
}
