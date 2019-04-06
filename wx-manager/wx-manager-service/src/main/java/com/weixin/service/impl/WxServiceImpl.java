package com.weixin.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;
import com.weixin.common.result.WxResult;
import com.weixin.commons.exception.ServerException;
import com.weixin.mapper.InContentMapper;
import com.weixin.mapper.ManagerMapper;
import com.weixin.mapper.UserMapper;
import com.weixin.pojo.InContent;
import com.weixin.pojo.InContentExample;
import com.weixin.pojo.InContentExample.Criteria;
import com.weixin.pojo.Manager;
import com.weixin.pojo.User;
import com.weixin.pojo.WeChatInfo;
import com.weixin.service.WxService;
import com.xeixin.commom.untils.IDUtils;
import com.xeixin.commom.untils.JsonUtils;

import redis.clients.jedis.JedisCluster;
@Service
public class WxServiceImpl implements WxService {

	@Value("${WX_APPID}")
	private String WX_APPID;
	@Value("${WX_appSecret}")
	private String WX_appSecret;
	@Value("${GRANT_TYPE}")
	private String GRANT_TYPE;
	@Value("${SESSION_HOST}")
	private String SESSION_HOST;
	@Value("${SESSION_TIME}")
	private Integer SESSION_TIME;
	@Value("${SESSION_KEY}")
	private String SESSION_KEY;
	@Value("${AUTH_CODE}")
	private Long AUTH_CODE;
	@Autowired
	private JedisCluster jedisCluster;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ManagerMapper managerMapper;
	@Autowired
	private InContentMapper inContentMapper;
	/*
	 * 普通用户获得openid,sessionkey
	 * 
	 * @see com.weixin.service.WxService#getSessionKey(java.lang.String)
	 */
	@Override
	public WxResult getSessionKey_U(String code) {
		if (null == code) {
			return WxResult.build(400, "未获得code");
		}
		String URL = SESSION_HOST + "appid=" + WX_APPID + "&secret=" + WX_appSecret + "&js_code=" + code
				+ "&grant_type=" + GRANT_TYPE;
		RestTemplate client = new RestTemplate();
		String res = client.getForObject(URL, String.class);
		WeChatInfo weChatInfo = JSONObject.parseObject(res, WeChatInfo.class);
		User user = userMapper.selectByOpenid(weChatInfo.getOpenId());
		if (null == user) {
			User insertUser = new User();
			insertUser.setOpenid(weChatInfo.getOpenId());
			insertUser.setUid(IDUtils.genUserId());
			insertUser.setCreated(new Date());
			try {
				userMapper.insertSelective(insertUser);
			} catch (Exception e) {
				return WxResult.build(500, "服务器处理异常");
			}
		}
		try {
			jedisCluster.set(SESSION_KEY + weChatInfo.getOpenId(), JsonUtils.objectToJson(weChatInfo));
			jedisCluster.expire(SESSION_KEY + weChatInfo.getOpenId(), SESSION_TIME);
		} catch (Exception e) {
			throw new ServerException("redis插入用户登录session失败");
		}
		return WxResult.ok(SESSION_KEY + weChatInfo.getOpenId());
	}

	/*
	 * 维修员openid,sessionkey
	 * @see com.weixin.service.WxService#getSessionKey_M(java.lang.String)
	 */
	@Override
	public WxResult getSessionKey_M(String code) {
		if (null == code) {
			return WxResult.build(400, "未获得code");
		}
		String URL = SESSION_HOST + "appid=" + WX_APPID + "&secret=" + WX_appSecret + "&js_code=" + code
				+ "&grant_type=" + GRANT_TYPE;
		RestTemplate client = new RestTemplate();
		String res = client.getForObject(URL, String.class);
		WeChatInfo weChatInfo = JSONObject.parseObject(res, WeChatInfo.class);
		Manager manager = managerMapper.getManagerByOpenid(weChatInfo.getOpenId());
		if(null == manager) {
			Manager insertManager = new Manager();
			insertManager.setUid(IDUtils.genUserId());
			insertManager.setOpenid(weChatInfo.getOpenId());
			insertManager.setCerated(new Date());
			try {
				managerMapper.insertSelective(insertManager);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			// 如果redis未过期则更新时间
			String info = jedisCluster.get(SESSION_KEY + weChatInfo.getOpenId()) ;
			if(StringUtils.isNotBlank(info)) {
				jedisCluster.expire(SESSION_KEY + weChatInfo.getOpenId(), SESSION_TIME);
				System.out.println("666");
				return WxResult.ok(SESSION_KEY + weChatInfo.getOpenId());
			}
			jedisCluster.set(SESSION_KEY + weChatInfo.getOpenId(), JsonUtils.objectToJson(weChatInfo));
			jedisCluster.expire(SESSION_KEY + weChatInfo.getOpenId(), SESSION_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return WxResult.ok(SESSION_KEY + weChatInfo.getOpenId());
	}

	// 管理员验证
	@Override
	public WxResult authCode(String code) {
		InContent inContent = null;
		try {
			inContent = inContentMapper.selectByCategoryId(AUTH_CODE);
		} catch (Exception e) {
			throw new ServerException("获取验证码失败");
		}
		String finalCode = inContent.getTitle();
		if(!code.equals(finalCode)) {
			return WxResult.build(400, "邀请码错误");
		}
		return WxResult.ok();
	}

}
