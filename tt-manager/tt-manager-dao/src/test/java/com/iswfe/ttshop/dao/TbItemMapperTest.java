package com.iswfe.ttshop.dao;

import com.iswfe.ttshop.pojo.po.TbItem;
import com.iswfe.ttshop.pojo.po.TbItemExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao-test.xml"})
public class TbItemMapperTest {

	@Autowired
	private TbItemMapper itemDao;

	@Test
	public void selectByExample() throws Exception {
		TbItemExample itemExample = new TbItemExample();
		TbItemExample.Criteria criteria = itemExample.createCriteria();

		criteria.andSellPointLike("%清仓%");

		List<TbItem> tbItems = itemDao.selectByExample(itemExample);
		System.out.println(tbItems);
	}

}