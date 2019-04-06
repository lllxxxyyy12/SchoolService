package com.wexin.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weixin.common.result.WxResult;
import com.weixin.pojo.MaintenanceTable;
import com.weixin.service.ServerService;
/*
 *  维修品操作COntroller
 */
@RequestMapping("/api/server")
@RestController
public class ServerController {
	@Autowired
	private ServerService serverService;
	
	/*
	 *  添加维修信息
	 */
	@RequestMapping("/add/mainTenance")
	public WxResult addMainTenance(MaintenanceTable product, String ASE) {
		return serverService.addMainTenance(product, ASE);
	}
	
	/*
	 *  获得自己的发布信息
	 */
	@RequestMapping("/get/selfMainTenance")
	public WxResult getselfMainTenance(Long card) {
		return serverService.getselfMainTenance(card);
	}
	
	/*
	 *  更新维修信息
	 */
	@RequestMapping("update/mainTenance")
	public WxResult updateMainTenance(MaintenanceTable product, String ASE) {
		return serverService.updateMainTenance(product, ASE);
	}
	
	/*
	 * 获得所有维修信息
	 */
	@RequestMapping("/get/allMainTenance")
	public WxResult getAllMainTenance() {
		return serverService.getAllMainTenance();
	}
	
	/*
	 *  删除维修信息
	 */
	@RequestMapping("/del/mainTenance")
	public WxResult delMainTenance(Long uids, String ASE) {
		return serverService.delMainTenance(uids, ASE);
	}
	
	/*
	 * 根据地点获得维修信息
	 */
	@RequestMapping("/get/allMainTenanceByTitle")
	public WxResult getAllMainTenanceByTitle(String title) throws UnsupportedEncodingException {
		title = new String(title.getBytes("iso8859-1"), "utf-8");
		return serverService.getAllMainTenanceByTitle(title);
	}
}
