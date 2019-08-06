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
		int d = p % x;	// 현재 페이지 위치
	
		switch(d){		// 루프의 처음 s 과 마지막 e	
			case 1:		// 현재 페이지가 네비게이션의 처음에 있을때..
				s = p;
				e = p + (x - 1);
			break;
	
			case 0:		// 현재 페이지가 마지막에 있을때.
				s = p - (x - 1);
				e = p; 
			break;
	
			default:	// 중간에 꼈을때...
				s = p - (d - 1);
				e = p + (x - d);
		}	
		
		StringBuffer dataList = new StringBuffer();
		
		if(e > totalPage) e = totalPage;		// 루프의 마지막이 총페이지를 넘는지 체크
		
		// 이전 pageCount 만큼 이동
		int prev = (p - x  > 0) ? p - x : p;
		dataList.append("<li class=\"page-item " + (prev == p ? "disabled" : "")  + "\"><a class=\"page-link \" href=\"/search/list/" + prev + "\"><span class=\"fa fa-angle-double-left\"></span></a></li>");
		// 이전 1개씩 
		dataList.append("<li class=\"page-item " + (p == 1 ? "disabled":"")  + "\"><a class=\"page-link \" href=\"/search/list/" + (p-1) + "\"><span class=\"fa fa-angle-left\"></span></a></li>");
	
		for(int i = s; i <= e ;i++){
			 dataList.append("<li class=\"page-item "  + (i == p ? "active":"") + "\"><a class=\"page-link \" href=\"/search/list/" + i + "\">" + i + "</a></li>");
		}
		
		//다음 1개씩
		dataList.append("<li class=\"page-item " + (p == totalPage ? "disabled":"") + "\"><a class=\"page-link \" href=\"/search/list/" + (p+1) + "\"><span class=\"fa fa-angle-right\"></span></a></li>");
		// 다음 pageCount 만큼 이동
		int next = (p + x <= totalPage) ? p + x  : totalPage;
		dataList.append("<li class=\"page-item " + (e >= totalPage ? "disabled":"") + "\"><a class=\"page-link \" href=\"/search/list/" + next + "\"><span class=\"fa fa-angle-double-right\"></span></a></li>");
		
		
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
