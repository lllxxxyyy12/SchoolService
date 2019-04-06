package com.weixin.service;
/*
 *  维修层接口
 */
import com.weixin.common.result.WxResult;
import com.weixin.pojo.MaintenanceTable;

public interface ServerService {
	// 添加维修信息
	WxResult addMainTenance(MaintenanceTable product, String ASE);
	// 获得自己的发布信息
	WxResult getselfMainTenance(Long card);
	// 更新维修信息
	WxResult updateMainTenance(MaintenanceTable product, String ASE);
	// 获得所有维修信息
	WxResult getAllMainTenance();
	// 删除维修信息
	WxResult delMainTenance(Long uids, String ASE);
	// 根据地点获得维修信息
	WxResult getAllMainTenanceByTitle(String title);
}
