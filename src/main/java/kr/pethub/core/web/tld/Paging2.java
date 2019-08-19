package kr.pethub.core.web.tld;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class Paging2 extends TagSupport{

	private int totalPage;
	private int page;
	private int pageCount = 0;

	public int doStartTag(){
		
		JspWriter out = pageContext.getOut();
		
		int x = (this.pageCount == 0) ? 10 : this.pageCount;
		int p = this.page;
		int s = 0;
		int e = 0;
		int d = p % x;										// 현재 페이지 위치
	
		switch(d){											// 루프의 처음 s 과 마지막 e	
			case 1:											// 현재 페이지가 네비게이션의 처음에 있을때..
				s = p;
				e = p + (x - 1);
			break;
	
			case 0:	// 현재 페이지가 마지막에 있을때.
				s = p - (x - 1);
				e = p; 
			break;
	
			default:	// 중간에 꼈을때...
				s = p - (d - 1);
				e = p + (x - d);
		}	
		
		StringBuffer dataList = new StringBuffer();
		
		if(e > totalPage) e = totalPage;		// 루프의 마지막이 총페이지를 넘는지 체크
	
		if(p != 1 ){									//이전
			int step1 = p - 1;
			dataList.append("<li><a href=\"javascript:goPage(" + step1 + ")\"  aria-label=\"Previous\"><span aria-hidden=\"true\">《 </span></a></li>");
		}else{
			dataList.append("<li><span aria-hidden=\"true\">《 </span></li>");
		}
	
		for(int i = s; i <= e ;i++){
			 if(i == p){
				 dataList.append("<li class=\"active\"><span>" + i + "<span class=\"sr-only\">(current)</span></span></li>");
			 } else{ 	
				 dataList.append(" <li><a href=\"javascript:goPage(" + i + ")\">"+ i +"</a></li>");
			 } 	
		}
	
		if(p != totalPage){					//다음
			int step2 = p + 1;
			dataList.append("<li><a href=\"javascript:goPage(" + step2 + ")\"  aria-label=\"Next\"><span aria-hidden=\"true\"> 》</span></a></li>");
		}else{
			dataList.append("<li><span aria-hidden=\"true\"> 》</span></li>");
		}
		
		try {
			out.println(dataList);
		} catch(IOException ioe){          
			ioe.printStackTrace(); 
		}
		return SKIP_BODY; 
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
		 
}
