package com.weixin.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class WeChatInfo implements Serializable{
	private String openid;
	@JSONField(name = "session_key")
	private String sessionKey;
	public String getOpenId() {
		return openid;
	}
	public void setOpenId(String openId) {
		this.openid = openId;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
}
