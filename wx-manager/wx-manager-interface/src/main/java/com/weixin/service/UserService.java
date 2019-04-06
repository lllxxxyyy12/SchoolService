package com.weixin.service;

import com.weixin.common.result.WxResult;
import com.weixin.pojo.User;

public interface UserService {
	User getUserByOpenid(String openId);
	WxResult updateUserInfo(User user, String ASE);
	WxResult getUserInfo(String ASE);
}
