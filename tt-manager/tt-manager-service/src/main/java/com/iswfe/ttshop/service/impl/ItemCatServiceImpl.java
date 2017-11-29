package com.iswfe.ttshop.service.impl;

import com.iswfe.ttshop.common.dto.TreeNode;
import com.iswfe.ttshop.dao.TbItemCatMapper;
import com.iswfe.ttshop.pojo.po.TbItemCat;
import com.iswfe.ttshop.pojo.po.TbItemCatExample;
import com.iswfe.ttshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	TbItemCatMapper itemCatDao;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<TreeNode> listItemCats(Long parentId) {
		//创建空的treeNodes
		List<TreeNode> treeNodes = new ArrayList<>();
		try {
			//创建模板
			TbItemCatExample example = new TbItemCatExample();
			//创建查询对象
			TbItemCatExample.Criteria criteria = example.createCriteria();
			//添加查询条件
			criteria.andParentIdEqualTo(parentId);

			//执行查询
			List<TbItemCat> itemCats = itemCatDao.selectByExample(example);

			//遍历itemCats,使其转化为treeNodes
			for (TbItemCat itemCat : itemCats) {
				//新建一个node并从表的数据中去设值
				TreeNode node = new TreeNode();
				node.setId(itemCat.getId());
				node.setText(itemCat.getName());
				node.setState(itemCat.getIsParent()?"closed":"open");

				//最后添加到treeNodes里面
				treeNodes.add(node);
			}
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return treeNodes;
	}
}
