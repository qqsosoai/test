package com.bdqn.market.util;

public class PageUtil {
	private Integer pageIndex=1;//当前页码
	private Integer pageSize=1;//页面显示数量
	private Integer pageCount=1;//总页数
	private Integer sqlCount=0;//总记录数
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
			this.pageCount = pageCount;
	}
	public Integer getSqlCount() {
		return sqlCount;
	}
	public void setSqlCount(Integer sqlCount) {
		if (sqlCount>0) {
			this.sqlCount = sqlCount;
			pageCount=(sqlCount%pageSize)==0?(sqlCount/pageSize):(sqlCount/pageSize+1);
		}
	}
	@Override
	public String toString() {
		return "PageUtil [pageIndex=" + pageIndex + ", pageSize=" + pageSize
				+ ", pageCount=" + pageCount + ", sqlCount=" + sqlCount + "]";
	}
	public PageUtil() {
		super();
	}
	
}
