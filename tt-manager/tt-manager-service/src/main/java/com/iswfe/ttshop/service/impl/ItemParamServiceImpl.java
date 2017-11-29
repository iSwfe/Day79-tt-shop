package com.iswfe.ttshop.service.impl;

import com.iswfe.ttshop.common.dto.Page;
import com.iswfe.ttshop.common.dto.Result;
import com.iswfe.ttshop.dao.TbItemParamCustomMapper;
import com.iswfe.ttshop.dao.TbItemParamMapper;
import com.iswfe.ttshop.pojo.po.TbItem;
import com.iswfe.ttshop.pojo.po.TbItemParam;
import com.iswfe.ttshop.pojo.po.TbItemParamExample;
import com.iswfe.ttshop.pojo.vo.TbItemParamCustom;
import com.iswfe.ttshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TbItemParamMapper itemParamDao;

	@Autowired
	TbItemParamCustomMapper itemParamCustomDao;

	@Transactional
	@Override
	public int saveItemParam(Long cid, TbItemParam itemParam) {
		int result = 0;
		try {
			itemParam.setItemCatId(cid);
			itemParam.setCreated(new Date());
			itemParam.setUpdated(new Date());
			result = itemParamDao.insertSelective(itemParam);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public TbItemParam getByCid(Long cid) {
		TbItemParam itemParam = null;
		try {
			//创建查询模板
			TbItemParamExample example = new TbItemParamExample();

			//添加查询条件
			TbItemParamExample.Criteria criteria = example.createCriteria();
			criteria.andItemCatIdEqualTo(cid);

			//执行查询
			List<TbItemParam> itemParams = itemParamDao.selectByExampleWithBLOBs(example);

			//处理结果
			if (itemParams != null && itemParams.size() > 0)
				itemParam = itemParams.get(0);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return itemParam;
	}

	@Override
	public Result<TbItemParamCustom> listItemParams(Page page) {
		Result<TbItemParamCustom> itemParamCustomResult = new Result<>();
		try {
			//获取
			Long total = itemParamCustomDao.countItemParams();
			List<TbItemParamCustom> itemParamsCustom = itemParamCustomDao.listItemParamByPage(page);
			//放值
			itemParamCustomResult.setRows(itemParamsCustom);
			itemParamCustomResult.setTotal(total);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return itemParamCustomResult;
	}

}
