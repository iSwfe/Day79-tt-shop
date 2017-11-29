package com.iswfe.ttshop.web;

import com.iswfe.ttshop.common.dto.Page;
import com.iswfe.ttshop.common.dto.Result;
import com.iswfe.ttshop.pojo.po.TbItemParam;
import com.iswfe.ttshop.pojo.vo.TbItemParamCustom;
import com.iswfe.ttshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemParamAction {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ItemParamService itemParamService;

	//保存一个itemParam参数模板
	@ResponseBody
	@RequestMapping(value = "itemParam/{cid}", method = RequestMethod.POST)
	public int saveItemParam(@PathVariable("cid") Long cid, TbItemParam itemParam) {
		return itemParamService.saveItemParam(cid, itemParam);
	}

	@ResponseBody
	@RequestMapping(value = "itemParam/{cid}", method = RequestMethod.GET)
	public TbItemParam getByCid(@PathVariable("cid") Long cid) {
		return itemParamService.getByCid(cid);
	}

	//分页查询itemParamCustom(自定义的)参数模板
	@ResponseBody
	@RequestMapping(value = "itemParams", method = RequestMethod.POST)
	public Result<TbItemParamCustom> listItemParams(Page page) {
		return itemParamService.listItemParams(page);
	}
}
