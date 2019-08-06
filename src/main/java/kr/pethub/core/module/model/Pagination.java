package kr.pethub.core.module.model;

public class Pagination  {
    
	private Integer page;			//현제 페이지
	private Integer rowSize;		//로우 수
	private int totalRow;			//전체 목록수
	
	
	public Integer getPage() {
		return page == null ? 1 : page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public Integer getRowSize() {
		return rowSize == null ? 15 : rowSize;
	}
	public void setRowSize(Integer rowSize) {
		this.rowSize = rowSize;
	}
	/**
	 * 총페이지 수
	 * @return
	 */
	public int getTotalPage() {
		return (int)Math.ceil( (double)getTotalRow() / (double)getRowSize());
	}
	/**
	 * 시작 로우
	 * @return
	 */ 
	public int getRowStart() {
		return (getPage() -1) * getRowSize();
	}
	
    
}
