package com.weixin.service;

import com.weixin.common.result.WxResult;
import com.weixin.pojo.SugSort;
import com.weixin.pojo.SugTable;

public interface SugService {
	// 初始化界面
	WxResult initPage();
	// 根据Id查询子节点
	WxResult getChildrenById(Long id);
	// 添加意见信息
	WxResult addSugInfo(SugTable sugTable, String ASE);
	// 根据意见类型进行查询
	WxResult getSugInfoBySortId(String sortId);
	// 添加意见类型
	WxResult addSugSort(SugSort sort);
}
