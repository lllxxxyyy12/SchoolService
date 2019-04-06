package com.weixin.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.weixin.common.result.WxResult;
import com.weixin.commons.exception.ServerException;
import com.weixin.mapper.UserMapper;
import com.weixin.pojo.User;
import com.weixin.pojo.WeChatInfo;
import com.weixin.service.UserService;
import com.xeixin.commom.untils.JsonUtils;

import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisCluster jedisCluster;

	/*
	 * 根据openId获得user实列对象
	 * 
	 * @see com.weixin.service.UserService#getUserByOpenid(java.lang.String)
	 */
	@Override
	public User getUserByOpenid(String openId) {
		return userMapper.selectByOpenid(openId);
	}

	/*
	 * 更新用户的信息
	 * 
	 * @see com.weixin.service.UserService#updateUserInfo(com.weixin.pojo.User)
	 */
	@Override
	public WxResult updateUserInfo(User user, String ASE) {
		// 根据第三方登录态查询redis
		String info = jedisCluster.get(ASE);
		WeChatInfo weChatInfo = null;
		if (StringUtils.isNotBlank(info)) {
			weChatInfo = JsonUtils.jsonToPojo(info, WeChatInfo.class);
		} else {
			return WxResult.build(400, "用户登录过期");
		}
		// 补全用用户的信息
		user.setOpenid(weChatInfo.getOpenId());
		user.setUpdated(new Date());
		// 数据库插入用户信息
		try {
			userMapper.insertSelective(user);
		} catch (Exception e) {
			throw new ServerException("用户信息更新失败");
		}
		user.setOpenid(null);
		return WxResult.ok(user);
	}

	/*
	 * 通过第三方登陆态获得用户信息
	 */
	@Override
	public WxResult getUserInfo(String ASE) {
		// 查询redis
		String info = jedisCluster.get(ASE);
		WeChatInfo weChatInfo = null;
		if(StringUtils.isNotBlank(info)) {
			weChatInfo = JsonUtils.jsonToPojo(info, WeChatInfo.class);
		} else {
			return WxResult.build(400, "用户登录过期");
		}
		User user = null;
		try {
			user = userMapper.selectByOpenid(weChatInfo.getOpenId());
		} catch (Exception e) {
			throw new ServerException("根据id查询用户信息失败");
		}
		user.setOpenid(null);
		return WxResult.ok(user);
	}

}
