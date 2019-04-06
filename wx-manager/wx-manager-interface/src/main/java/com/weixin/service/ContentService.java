package com.weixin.service;

import com.weixin.common.result.WxResult;

public interface ContentService {
	WxResult getAllImage();
	WxResult getChildren(Long id);
	WxResult getContentByTitle(String title);
}
