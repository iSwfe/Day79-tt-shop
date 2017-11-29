package com.iswfe.ttshop.service.impl;

import com.iswfe.ttshop.common.dto.Order;
import com.iswfe.ttshop.common.dto.Page;
import com.iswfe.ttshop.common.dto.Result;
import com.iswfe.ttshop.common.util.IDUtils;
import com.iswfe.ttshop.dao.TbItemCustomMapper;
import com.iswfe.ttshop.dao.TbItemDescMapper;
import com.iswfe.ttshop.dao.TbItemMapper;
import com.iswfe.ttshop.dao.TbItemParamItemMapper;
import com.iswfe.ttshop.pojo.po.TbItem;
import com.iswfe.ttshop.pojo.po.TbItemDesc;
import com.iswfe.ttshop.pojo.po.TbItemExample;
import com.iswfe.ttshop.pojo.po.TbItemParamItem;
import com.iswfe.ttshop.pojo.vo.TbItemQuery;
import com.iswfe.ttshop.pojo.vo.TbItemCustom;
import com.iswfe.ttshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbItemCustomMapper itemCustomDao;
    @Autowired
    private TbItemDescMapper itemDescDao;
    @Autowired
    private TbItemParamItemMapper itemParamItemDao;

    @Override
    public TbItem getById(Long itemId) {
        return itemDao.selectByPrimaryKey(itemId);
    }

    @Override
    public List<TbItem> listItems() {
        List<TbItem> tbItemList = null;
        try {
            //查询所有商品
            tbItemList = itemDao.selectByExample(null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return tbItemList;
    }

    @Override
    public Result<TbItemCustom> listItemsCustom(Page page, Order order, TbItemQuery tbItemQuery) {
        Result<TbItemCustom> result = null;
        try {
            //1 先查总记录数 int--Long
            long total = itemCustomDao.countItems(tbItemQuery);
            //2 查询指定页码的记录集合
            List<TbItemCustom> list = itemCustomDao.listItemsCustom(page, order, tbItemQuery);
            //3 存放result中
            result = new Result<>();
            result.setTotal(total);
            result.setRows(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

	@Override
	public int itemsBatch(List<Long> ids) {
        int i = 0;
        try {
            //创建record,作为要批量修改的目标值
            TbItem record = new TbItem();
            record.setStatus((byte)3);

            //创建模板
            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();

            //直接将ids集合添加到example条件中
            criteria.andIdIn(ids);

            //最后将其删除(其实是更新操作)
            i = itemDao.updateByExampleSelective(record, example);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
	}

    @Override
    @Transactional
    public int saveItem(TbItem tbItem, String content, String paramData) {
        int result = 0;
        try {
            //先保存tb_item表
            tbItem.setId(IDUtils.getItemId());
            tbItem.setStatus((byte) 1); //默认给上架状态
            tbItem.setCreated(new Date());
            tbItem.setUpdated(new Date());
            result = itemDao.insert(tbItem);

            //再保存tb_item_desc表
            TbItemDesc itemDesc = new TbItemDesc();
            itemDesc.setItemId(tbItem.getId());
            itemDesc.setItemDesc(content);
            itemDesc.setCreated(new Date());
            itemDesc.setUpdated(new Date());
            result += itemDescDao.insert(itemDesc);

            //最后保存tb_item_param_item表
	        TbItemParamItem itemParamItem = new TbItemParamItem();
	        itemParamItem.setItemId(tbItem.getId());
	        itemParamItem.setParamData(paramData);
	        itemParamItem.setCreated(new Date());
	        itemParamItem.setUpdated(new Date());
	        result += itemParamItemDao.insertSelective(itemParamItem);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

}
