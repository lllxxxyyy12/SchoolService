package com.weixin.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.weixin.common.result.WxResult;
import com.weixin.commons.exception.ServerException;
import com.weixin.mapper.SugSortMapper;
import com.weixin.mapper.SugTableMapper;
import com.weixin.mapper.UserMapper;
import com.weixin.pojo.SugSort;
import com.weixin.pojo.SugSortExample;
import com.weixin.pojo.SugSortExample.Criteria;
import com.weixin.pojo.SugTable;
import com.weixin.pojo.SugTableExample;
import com.weixin.pojo.User;
import com.weixin.pojo.WeChatInfo;
import com.weixin.service.SugService;
import com.xeixin.commom.untils.IDUtils;
import com.xeixin.commom.untils.JsonUtils;

import redis.clients.jedis.JedisCluster;

@Service
public class SugServiceImpl implements SugService {
	@Value("${INIT_ID}")
	private Long INIT_ID;
	@Autowired
	private SugTableMapper sugTableMapper;
	@Autowired
	private SugSortMapper sugSortMapper;
	@Autowired
	private JedisCluster jedisCluster;
	@Autowired
	private UserMapper userMapper;
	
	/*
	 * 初始化页面
	 * 
	 * @see com.weixin.service.SugService#initPage()
	 */
	@Override
	public WxResult initPage() {
		SugSortExample sugSortExample = new SugSortExample();
		Criteria criteria = sugSortExample.createCriteria();
		criteria.andParentIdEqualTo(INIT_ID);
		List<SugSort> list = null;
		try {
			list = sugSortMapper.selectByExample(sugSortExample);
		} catch (Exception e) {
			throw new ServerException("初始化页面失败");
		}
		return WxResult.ok(list);
	}

	/*
	 * 根据id查询子节点
	 * 
	 * @see com.weixin.service.SugService#getChildrenById(java.lang.Long)
	 */
	@Override
	public WxResult getChildrenById(Long id) {
		SugSortExample sugSortExample = new SugSortExample();
		Criteria criteria = sugSortExample.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<SugSort> list = null;
		try {
			list = sugSortMapper.selectByExample(sugSortExample);
		} catch (Exception e) {
			throw new ServerException("根据id查询意见分类信息失败");
		}
		return WxResult.ok(list);
	}

	/*
	 * 添加建议信息
	 * 
	 * @see com.weixin.service.SugService#addSugInfo(com.weixin.pojo.SugTable)
	 */
	@Override
	public WxResult addSugInfo(SugTable sugTable, String ASE) {
		// 在redis查询用户信息
		String info = jedisCluster.get(ASE);
		WeChatInfo weChatInfo = null;
		if (StringUtils.isNotBlank(info)) {
			weChatInfo = JsonUtils.jsonToPojo(info, WeChatInfo.class);
		} else {
			return WxResult.build(400, "用户登录过期");
		}
		// 根据id查询Name
		User user = userMapper.selectByOpenid(weChatInfo.getOpenId());
		if(!isAllInfomation(user)) {
			return WxResult.build(401, "请问完善您的资料信息");
		}
		// 补全sugTable信息
		sugTable.setUid(String.valueOf(IDUtils.genUserId()));
		sugTable.setName(user.getName());
		sugTable.setCreated(new Date());
		// 添加到数据库中
		try {
			sugTableMapper.insert(sugTable);
		} catch (Exception e) {
			throw new ServerException("建议信息插入失败");
		}
		
		return WxResult.ok();
	}

	/*
	 * 判断用户的信息是否完善
	 */
	public boolean isAllInfomation(User user) {
		if (user.getName() == null || user.getCard() == null || user.getClassId() == null || user.getCollege() == null
				|| user.getPhoneNumber() == null || user.getDomain() == null || user.getPic() == null
				|| user.getSex() == null || user.getDormitory() == null) {
			return false;
		}
		return true;
	}

	/*
	 * 根据意见类型查询建议信息
	 * @see com.weixin.service.SugService#getSugInfoBySortId(java.lang.String)
	 */
	@Override
	public WxResult getSugInfoBySortId(String sortId) {
		SugTableExample sugTableExample = new SugTableExample();
		com.weixin.pojo.SugTableExample.Criteria criteria = sugTableExample.createCriteria();
		criteria.andSortIdEqualTo(sortId);
		List<SugTable> list = null;
		try {
			list = sugTableMapper.selectByExample(sugTableExample);
		} catch (Exception e) {
			throw new ServerException("根据意见类型查询建议信息失败");
		}
		return WxResult.ok(list);
	}

	/*
	 * 添加反馈类型
	 * @see com.weixin.service.SugService#addSugSort(com.weixin.pojo.SugSort)
	 */
	@Override
	public WxResult addSugSort(SugSort sort) {
		// 补全属性
		sort.setId(IDUtils.genUserId());
		try {
			sugSortMapper.insertSelective(sort);
		} catch (Exception e) {
			throw new ServerException("添加反馈信息失败");
		}
		return WxResult.ok(sort);
	}
}
