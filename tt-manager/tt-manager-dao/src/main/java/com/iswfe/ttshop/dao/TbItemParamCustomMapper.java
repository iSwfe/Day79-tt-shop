package com.iswfe.ttshop.dao;

import com.iswfe.ttshop.common.dto.Page;
import com.iswfe.ttshop.pojo.vo.TbItemParamCustom;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TbItemParamCustomMapper {
	List<TbItemParamCustom> listItemParamByPage(Page page);

	long countItemParams();
}
