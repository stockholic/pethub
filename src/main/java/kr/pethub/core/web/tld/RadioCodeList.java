package kr.pethub.core.web.tld;

import java.io.IOException;

import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


@SuppressWarnings("serial")
public class RadioCodeList extends TagSupport{
	
	private String name;
	private String grpCd;
	private int lineCnt;
	private String selectKey;

	public int doStartTag(){
//		
//		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext( pageContext.getServletContext());
//		CommonService commonService = context.getBean(CommonService.class);
//		
//		JspWriter out = pageContext.getOut();
//		StringBuffer dataList = new StringBuffer();
//		
//		CodeVO code = new CodeVO();
//		code.setGrpCd(this.grpCd);
//		List<CodeVO>codeList =  commonService.selectCodeList(code);
//		dataList.append("<table><tr>");
//		for(int i = 0; i < codeList.size(); i++){
//			String selected = codeList.get(i).getCdCd().equals(this.selectKey) ? " checked" : "";
//			dataList.append("<td><input type='radio' name='" + this.name  + "' value='" + codeList.get(i).getCdCd() + "'" + selected + ">" + codeList.get(i).getCdNm() + "</td>");
//			if((i+1) % this.lineCnt == 0) dataList.append("</tr>");
//		}
//		dataList.append("</tr></table>");
//		
//		try {
//			out.println(dataList);
//		} catch(IOException ioe){          
//			ioe.printStackTrace(); 
//		}
		return SKIP_BODY; 
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setGrpCd(String grpCd) {
		this.grpCd = grpCd;
	}
	public void setLineCnt(int lineCnt) {
		this.lineCnt = lineCnt;
	}
	public void setSelectKey(String selectKey) {
		this.selectKey = selectKey;
	}
}
