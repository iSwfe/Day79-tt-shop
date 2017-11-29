package com.iswfe.ttshop.common.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

//用于接收GET请求的排序参数
public class Order {
	//要排序的字段的类别:title,id,price...
	private String sort;
	//排序方式:asc/desc...
	private String order;
	//多列排序的所有条件,隐藏属性,计算给xxxMapper.xml
	//private List<String> orderParams;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<String> getOrderParams() {
		List<String> orderParams = new ArrayList<>();
		//非空判断
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			//解析出排序字段[]和排序方式[]
			String[] sorts = sort.split(",");
			String[] orders = order.split(",");

			//遍历得到每个"字段 排序"的集合
			for (int i = 0; i < sorts.length; i++) {
				if ("priceView".equals(sorts[i]))
					sorts[i] = "price";
				if ("statusView".equals(sorts[i]))
					sorts[i] = "status";
				if ("catView".equals(sorts[i]))
					sorts[i] = "cid";

				orderParams.add(sorts[i] + " " + orders[i]);
			}
		}
		return orderParams;
	}

}
