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
 * 도그시장 http://www.dogsijang.co.kr
 * @author shkr
 *
 */

public class DogsijangCoKr {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 강이지 목록 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogList(String linkUrl, HttpServletResponse response) throws IOException {
		
		PrintWriter writer = null;
		
		String selector = ".list_table tr";
		String domain = "http://www.dogsijang.co.kr";
		String patternId ="(.*)(no=)([0-9]+)";

		Elements elements = JsoupUtil.getElements(linkUrl,"euc-kr", selector);
		Collections.reverse(elements);
		
		int k = 1;
		try {
			writer = response.getWriter();
			for( Element ele :  elements) {
				
				if( ele.getElementsByTag("tr").hasAttr("onmouseover")  ) {
					
					//--------------------------------------------------------------------------------------------------------------- Start
					
					SiteLinkData cli  = new SiteLinkData();
					
					//제목 추출
					String dataTitle = ele.getElementsByTag("td").get(2).text() + " " + ele.getElementsByTag("td").get(3).text() + " " + ele.getElementsByTag("td").get(6).text()+ " " + ele.getElementsByTag("td").get(7).text();
					logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
					cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle)); 
					
					//링크 추출
					String dataLink = domain +"/board_dog"+ ele.getElementsByTag("td").get(1).getElementsByTag("a").attr("href").replace("./", "/");
					logger.debug( "LINK : {}" , dataLink );
					cli.setDataLink(dataLink);
					
					//이미지 추출 NO image
					String dataImg = "";
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
	 * 강아지 내용 추출, 이미지 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogContent( SiteLinkData siteLinkData ) throws IOException {
		
		String domain = "http://www.dogsijang.co.kr";
		String selector = ".list_table .contents";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() ,"euc-kr", selector );
		
		Elements imgElements = JsoupUtil.getElements(siteLinkData.getDataLink() ,"euc-kr", ".list_table img");
		
		String dataImg = "";
		for( Element ele :  imgElements ) {
			if( (ele.attr("src").contains("upload_photo")) )  {
				dataImg = ele.attr("src").replace("..", "");;
				break;
			}
		}
		
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());
		dataImg =  ("".equals(dataImg)) ? "" :  domain + dataImg;
		
		siteLinkData.setDataContent(dataContent);
		siteLinkData.setDataImg(dataImg);
		
		logger.debug( "CONTENTS : {}" , dataContent );
		logger.debug( "DATAIMG : {}" , dataImg);
		
	}
	
	
	
}
