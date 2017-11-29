package com.iswfe.ttshop.web;

import com.iswfe.ttshop.common.dto.Order;
import com.iswfe.ttshop.common.dto.Page;
import com.iswfe.ttshop.common.dto.Result;
import com.iswfe.ttshop.pojo.po.TbItem;
import com.iswfe.ttshop.pojo.vo.TbItemQuery;
import com.iswfe.ttshop.pojo.vo.TbItemCustom;
import com.iswfe.ttshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Scope("prototype")
public class ItemAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public TbItem printJsonById(@PathVariable("itemId") Long itemId) {
        return itemService.getById(itemId);
    }

    @ResponseBody
    @RequestMapping(value = "/items-custom", method = RequestMethod.GET)
    public Result<TbItemCustom> listItemsCustom(Page page, Order order, TbItemQuery tbItemQuery) {
        Result<TbItemCustom> result = null;
        try {
            result = itemService.listItemsCustom(page, order, tbItemQuery);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
    }
        return result;
    }

    @RequestMapping(value = "/items/batch", method = RequestMethod.POST)
    @ResponseBody
    public int batchItem(@RequestParam("ids[]") List<Long> ids) {
        int i = 0;
        try {
            i = itemService.itemsBatch(ids);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }

    @ResponseBody
    @RequestMapping(value = "item", method = RequestMethod.POST)
    public int saveItem(TbItem tbItem, String content, String paramData) {
        return itemService.saveItem(tbItem, content, paramData);
    }
}
