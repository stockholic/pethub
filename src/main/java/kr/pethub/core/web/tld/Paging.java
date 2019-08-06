package kr.pethub.core.web.tld;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("serial")
public class Paging extends TagSupport{

	private int page;						//현재페이지
	private int totalPage;					//총 페이지 수
	private int pageCount = 0;			// 표시할 페이지 개수
	private String searchString;		// 검색어

	public int doStartTag(){
		
		JspWriter out = pageContext.getOut();
		
		int pageCount = (this.pageCount == 0) ? 10 : this.pageCount;

		int leftCnt = (int)Math.floor( (double)pageCount / 2);		//기준페이지 좌측 페이지 개수
		//System.out.println( "leftCnt : " + leftCnt );
		
		int rightCnt = pageCount - leftCnt -1;		//기준페이지 우측 페이지 개수
		//System.out.println( "rightCnt : " + rightCnt );

		int startPage = 1;									//페이지 표시 시작
		int endPage = 0;									//페이지 표시 종료
		
		//첫번째 페이지 그룹처리, 좌측 페이지 표시 여부 조건
		if(page - leftCnt > 0) {
			startPage = page - leftCnt;
			//System.out.println( "startPage : " + startPage );
		}
		endPage = startPage + pageCount; 
		//System.out.println( "endPage 1 : " + endPage );
		
		//마지막 페이지 그룹처리, 우측 페이지 표시안함
		if( totalPage - page <  rightCnt || totalPage == page) {
			endPage = totalPage + 1;
			//System.out.println( "endPage 2 : " + endPage );
		}
		
		/*
		for(int i = startPage; i < endPage; i++) {
			System.out.println( i + ", " );
		}
		*/
		
		StringBuffer dataList = new StringBuffer();
		
		String searchString = StringUtils.isNotEmpty(this.searchString) ? "/" + this.searchString : ""; 
		
		// 이전 pageCount 만큼 이동
		int prev = (page - pageCount  > 0) ? page - pageCount : page;
		dataList.append("<li class=\"page-item " + (prev == page ? "disabled" : "")  + "\"><a class=\"page-link \" href=\"/search/list/" + prev + searchString +"\" title='이전 " + pageCount + "페이지 이등'><span class=\"fa fa-arrow-left\"></span></a></li>");
	
		for(int i = startPage; i < endPage ;i++){
			 dataList.append("<li class=\"page-item "  + (i == page ? "active":"") + "\"><a class=\"page-link \" href=\"/search/list/" + i + searchString + "\">" + i + "</a></li>");
		}
		
		// 다음 pageCount 만큼 이동
		int next = (page + pageCount <= totalPage) ? page + pageCount  : totalPage;
		dataList.append("<li class=\"page-item " + (endPage >= totalPage ? "disabled":"") + "\"><a class=\"page-link \" href=\"/search/list/" + next + searchString + "\" title='다음 " + pageCount + "페이지 이동'><span class=\"fa fa-arrow-right\"></span></a></li>");
		
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
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
}
