package com.iswfe.ttshop.web;

import com.iswfe.ttshop.common.dto.TreeNode;
import com.iswfe.ttshop.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatAction {

	@Autowired
	ItemCatService itemCatService;

	@ResponseBody
	@RequestMapping(value = "/itemCats", method = RequestMethod.GET)
	public List<TreeNode> listItemCats(@RequestParam("parentId") Long parentId) {
		return itemCatService.listItemCats(parentId);
	}
}
