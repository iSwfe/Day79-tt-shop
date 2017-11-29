package com.iswfe.ttshop.common.dto;

import java.util.List;

public class Result<T> {

	//不分页时查询的总记录数
	private Long total;
	//当前分页的结果集合
	private List<T> rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
