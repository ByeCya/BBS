package com.javalec.spring.service;

public class BPage {
	
	private int page;
	private int pageNum;
	private int pageStart;
	
	public BPage() {
		this.page = 1;
		this.pageNum = 10;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		if(pageNum <= 0 || pageNum > 100) {
			this.pageNum = 10;
			return;
		}
		this.pageNum = pageNum;
	}
	
	public int getPageStart() {
		this.pageStart = (this.page -1) * pageNum;
		return pageStart;
	}
	
	@Override
	public String toString() {
		return "BPage [page=" + page + ", pageNum" + pageNum + "]";
	}
}
