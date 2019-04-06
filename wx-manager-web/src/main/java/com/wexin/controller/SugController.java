package com.wexin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*
 *  意见反馈层Controller
 */

import com.weixin.common.result.WxResult;
import com.weixin.pojo.SugSort;
import com.weixin.pojo.SugTable;
import com.weixin.service.SugService;
@RestController
@RequestMapping("/api/sug")
public class SugController {
	@Autowired
	private SugService sugService;
	
	/*
	 *  初始化主页面
	 */
	@RequestMapping("/initPage")
	public WxResult initPage() {
		return sugService.initPage();
	}
	
	/*
	 *  根据id查询子节点
	 */
	@RequestMapping("/getChildrenById")
	public WxResult getChildrenById(@RequestParam Long id) {
		return sugService.getChildrenById(id);
	}
	
	/*
	 * 添加意见信息
	 */
	@RequestMapping("/addSugInfo")
	public WxResult addSugInfo(SugTable sugTable, String ASE) {
		return sugService.addSugInfo(sugTable, ASE);
	}
	
	/*
	 * 根据意见类型进行查询
	 */
	@RequestMapping("/getSugInfoBySortId")
	public WxResult getSugInfoBySortId(String sortId) {
		return sugService.getSugInfoBySortId(sortId);
	}
	
	@RequestMapping("/add/sugSort")
	public WxResult addSugSort(SugSort sort) {
		return sugService.addSugSort(sort);
	}
}
