package com.iswfe.ttshop.common.dto;

public class Page {

	//当前页码
	private int page;
	//每页容量
	private int rows;
	//查询的偏移量(但是不需要该实体)
	//private int offset;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getOffset() {
		return (page - 1) * rows;
	}
}
