package com.wexin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weixin.common.result.WxResult;
import com.weixin.pojo.User;
import com.weixin.service.UserService;
/*
 *  用户操作Controller
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/*
	 *  用户信息更新
	 */
	@RequestMapping("/updateUserInfo")
	public WxResult upateUserInfo(@RequestParam User user, @RequestParam String ASE) {
		return userService.updateUserInfo(user, ASE);
	}
	
	/*
	 *  获得用户信息
	 */
	@RequestMapping("/getUserInfo")
	public WxResult getUserInfo(@RequestParam String ASE) {
		return userService.getUserInfo(ASE);
	}
}
