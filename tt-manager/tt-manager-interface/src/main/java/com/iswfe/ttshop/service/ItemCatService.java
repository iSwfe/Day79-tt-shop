package com.iswfe.ttshop.service;

import com.iswfe.ttshop.common.dto.TreeNode;

import java.util.List;

public interface ItemCatService {
	//通过parentId查找该父类下的所有子分类
	List<TreeNode> listItemCats(Long parentId);
}
