package kr.pethub.core.web.tld;

import java.io.IOException;




import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import kr.pethub.core.utils.SysUtil;

@SuppressWarnings("serial")
public class FileSize extends TagSupport{

	private String fileSize;

	public int doStartTag(){
		
		JspWriter out = pageContext.getOut();
		
		try {
			out.print(SysUtil.getFileSize(Long.parseLong(fileSize)));
		} catch(IOException ioe){          
			ioe.printStackTrace(); 
		}
		return SKIP_BODY; 
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

}
