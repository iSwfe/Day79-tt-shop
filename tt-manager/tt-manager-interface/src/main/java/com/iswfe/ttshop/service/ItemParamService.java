package com.iswfe.ttshop.service;

import com.iswfe.ttshop.common.dto.Page;
import com.iswfe.ttshop.common.dto.Result;
import com.iswfe.ttshop.pojo.po.TbItemParam;
import com.iswfe.ttshop.pojo.vo.TbItemParamCustom;

public interface ItemParamService {

	int saveItemParam(Long cid, TbItemParam itemParam);

	TbItemParam getByCid(Long cid);

	Result<TbItemParamCustom> listItemParams(Page page);
}
