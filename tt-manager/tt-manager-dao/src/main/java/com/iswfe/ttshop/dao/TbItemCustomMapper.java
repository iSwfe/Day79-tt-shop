package com.iswfe.ttshop.dao;

import com.iswfe.ttshop.common.dto.Order;
import com.iswfe.ttshop.common.dto.Page;
import com.iswfe.ttshop.pojo.po.TbItem;
import com.iswfe.ttshop.pojo.vo.TbItemQuery;
import com.iswfe.ttshop.pojo.vo.TbItemCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbItemCustomMapper {

    //查询(不分页时)总记录数
    long countItems(@Param("tbItemQuery") TbItemQuery tbItemQuery);

    //查询(分页时)记录的集合
    List<TbItem> listItems(Page page);

    //查询TbItemCustom
    List<TbItemCustom> listItemsCustom(@Param("page") Page page, @Param("order") Order order, @Param("tbItemQuery") TbItemQuery tbItemQuery);
}
