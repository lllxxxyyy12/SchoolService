package com.wexin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weixin.common.result.WxResult;
import com.weixin.service.WxService;

/*
 *  微信登录授权，校验Controller
 */
@RestController
@RequestMapping("/api/wx")
public class WxController {
	@Autowired
	private WxService wxService;
	
	@RequestMapping("/login/user")
	public WxResult userLogin(@RequestParam(required=true, value="code") String code) {
		return wxService.getSessionKey_U(code);
	}
	
	@RequestMapping("/login/manager")
	public WxResult managerLogin(@RequestParam(required=true, value="code") String code) {
		return wxService.getSessionKey_M(code);
	}
	
	@RequestMapping("/authCode")
	public WxResult authCode(@RequestParam String authCode) {
		return null;
	}
}
