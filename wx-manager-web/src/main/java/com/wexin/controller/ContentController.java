package com.wexin.controller;

import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.weixin.common.result.WxResult;
import com.weixin.service.ContentService;

/*
 *  首页，设施内容Controller
 */
@RequestMapping("/api/content")
@RestController
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	/*
	 *  获得首页轮播图
	 */
	@RequestMapping("/getImage")
	public WxResult getAllImages() {
		return contentService.getAllImage();
	}
	
	
	/*
	 *  根据分类ID获得子ID
	 */
	@RequestMapping("/getChildren")
	public WxResult getChildren(@RequestParam Long id) {
		WxResult result = contentService.getChildren(id);
		return result;
	}
	
	/*
	 *  获得主页面的基础设施
	 */
	@RequestMapping("/getMainFacility")
	public WxResult getMainFacility(@RequestParam String title) throws UnsupportedEncodingException {
		title = new String(title.getBytes("iso8859-1"), "utf-8"); 
		WxResult result = contentService.getContentByTitle(title);
		return result;
	}
	
	
}
