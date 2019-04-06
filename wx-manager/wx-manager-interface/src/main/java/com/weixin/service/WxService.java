package com.weixin.service;

import com.weixin.common.result.WxResult;

public interface WxService {
	WxResult getSessionKey_U(String code);
	WxResult getSessionKey_M(String code);
	WxResult authCode(String code);
}
