package kr.pethub.core.web.tld;

import java.io.IOException;


import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SuppressWarnings("serial")
public class SelectCode extends TagSupport{
	
	private String grpCd;
	private String cdCd;

	public int doStartTag(){
//		
//		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext( pageContext.getServletContext());
//		CommonService commonService = context.getBean(CommonService.class);
//		
//		JspWriter out = pageContext.getOut();
//		
//		CodeVO vo = new CodeVO();
//		vo.setGrpCd(this.grpCd);
//		vo.setCdCd(this.cdCd);
//		
//		String codeName = "";
//		CodeVO code =  commonService.selectCode(vo);
//		if(code != null) codeName = code.getCdNm();
//		
//		try {
//			out.print(codeName);
//		} catch(IOException ioe){          
//			ioe.printStackTrace(); 
//		}
		return SKIP_BODY; 
	}

	public void setGrpCd(String grpCd) {
		this.grpCd = grpCd;
	}
	public void setCdCd(String cdCd) {
		this.cdCd = cdCd;
	}

}
