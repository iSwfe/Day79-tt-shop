package com.iswfe.ttshop.service;

import com.iswfe.ttshop.common.dto.Order;
import com.iswfe.ttshop.common.dto.Page;
import com.iswfe.ttshop.common.dto.Result;
import com.iswfe.ttshop.pojo.po.TbItem;
import com.iswfe.ttshop.pojo.vo.TbItemQuery;
import com.iswfe.ttshop.pojo.vo.TbItemCustom;

import java.util.List;

public interface ItemService {
    /**
     * 通过商品主键查询单个商品
     */
    TbItem getById(Long itemId);

    /**
     * 不带分页的查询所有商品
     */
    List<TbItem> listItems();

    /**
     * 带分页的查询所有商品
     */

    Result<TbItemCustom> listItemsCustom(Page page, Order order, TbItemQuery tbItemQuery);

    //修改商品的状态
    int itemsBatch(List<Long> ids);

	int saveItem(TbItem tbItem, String content, String paramData);
}
