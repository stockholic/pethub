package kr.pethub.core.web.tld;

import java.io.IOException;

import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


@SuppressWarnings("serial")
public class SelectCodeList extends TagSupport{
	
	private String id;
	private String grpCd;
	private String selectKey;
	private String style;
	private String onChange;
	private String checkMsg;
	private String all;

	public int doStartTag(){
		
//		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext( pageContext.getServletContext());
//		CommonService commonService = context.getBean(CommonService.class);
//		
//		JspWriter out = pageContext.getOut();
//		StringBuffer dataList = new StringBuffer();
//		
//		String style = (this.style != null) ? " style='" + this.style + "'" : "";
//		String onChange = (this.onChange != null) ? " onChange='" + this.onChange + "'" : "";
//		String checkMsg = (this.checkMsg != null) ? " checkMsg='" + this.checkMsg + "'" : "";
//		String all = (this.all != null) ? "<option value=''>" + this.all + "</option>" : "";
//		dataList.append("<select name='" + this.id + "' id='" + this.id + "'" + onChange + style + checkMsg +">");
//		dataList.append(all);
//		
//		CodeVO code = new CodeVO();
//		code.setGrpCd(this.grpCd);
//		List<CodeVO>codeList =  commonService.selectCodeList(code);
//		for(CodeVO lst : codeList){
//			String selected = lst.getCdCd().equals(this.selectKey) ? " selected" : "";
//			dataList.append("<option value='" + lst.getCdCd() + "'" + selected + ">" + lst.getCdNm() + "</option>");
//		}
//		
//		dataList.append("</select>");
//		
//		try {
//			out.println(dataList);
//		} catch(IOException ioe){          
//			ioe.printStackTrace(); 
//		}
		return SKIP_BODY; 
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setGrpCd(String grpCd) {
		this.grpCd = grpCd;
	}
	public void setSelectKey(String selectKey) {
		this.selectKey = selectKey;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}
	public void setCheckMsg(String checkMsg) {
		this.checkMsg = checkMsg;
	}
	public void setAll(String all) {
		this.all = all;
	}

}
