package com.weixin.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.weixin.common.result.WxResult;
import com.weixin.commons.exception.ServerException;
import com.weixin.mapper.MaintenanceTableMapper;
import com.weixin.mapper.UserMapper;
import com.weixin.pojo.MaintenanceTable;
import com.weixin.pojo.MaintenanceTableExample;
import com.weixin.pojo.MaintenanceTableExample.Criteria;
import com.weixin.pojo.User;
import com.weixin.pojo.WeChatInfo;
import com.weixin.service.ServerService;
import com.xeixin.commom.untils.IDUtils;
import com.xeixin.commom.untils.JsonUtils;
import redis.clients.jedis.JedisCluster;
/*
 * 维修层Service
 */
@Service
public class ServerServiceImpl implements ServerService {
	@Value("${SESSION_KEY}")
	private String SESSION_KEY;
	@Autowired
	private JedisCluster jedisCluster;
	@Autowired
	private MaintenanceTableMapper MaintenanceTableMapper;
	@Autowired
	private UserMapper userMapper;
	/*
	 *  添加维修信息
	 * @see com.weixin.service.ServerService#addMainTenance(com.weixin.pojo.MaintenanceTable, java.lang.String)
	 */
	@Override
	public WxResult addMainTenance(MaintenanceTable product, String ASE) {
		// 根据ASE判断用户是否过期
		String info = null;
		try {
			info = jedisCluster.get(ASE);
		} catch (Exception e) {
			throw new ServerException("redis查询ASE失败");
		}
		WeChatInfo weChatInfo = null;
		if(StringUtils.isNotBlank(info)) {
			weChatInfo = JsonUtils.jsonToPojo(info, WeChatInfo.class);
		} else {
			return WxResult.build(400, "用户登录过期");
		}
		// 在redis中根据ASE查询appid,sessionkey
		User user = userMapper.selectByOpenid(weChatInfo.getOpenId());
		// 判断用户信息是否完善
		if(!isAllInfomation(user)) {
			return WxResult.build(400, "请完善用户信息");
		}
		// 完善维修品信息
		product.setUid(String.valueOf(IDUtils.genUserId()));
		product.setName(user.getName());
		product.setStatus(String.valueOf(0));
		product.setCard(user.getCard());
		product.setCreated(new Date());
		product.setUpdated(new Date());
		try {
			MaintenanceTableMapper.insertSelective(product);
		} catch (Exception e) {
			throw new ServerException("维修品插入失败");
		}
		return WxResult.ok();
	}

	/*
	 * 查询个人提交的维修信息
	 * @see com.weixin.service.ServerService#getselfMainTenance(java.lang.Long)
	 */
	@Override
	public WxResult getselfMainTenance(Long card) {
		MaintenanceTableExample maintenanceTableExample = new MaintenanceTableExample();
	    Criteria criteria = maintenanceTableExample.createCriteria();
	    criteria.andCardEqualTo(card);
	    List<MaintenanceTable> list = null;
	    try {
	    	list = MaintenanceTableMapper.selectByExample(maintenanceTableExample);
		} catch (Exception e) {
			throw new ServerException("根据card查询个人维修信息失败");
		}
	    return WxResult.ok(list);
	}

	/*
	 * 更新维修信息
	 * @see com.weixin.service.ServerService#updateMainTenance(com.weixin.pojo.MaintenanceTable, java.lang.String)
	 */
	@Override
	public WxResult updateMainTenance(MaintenanceTable product, String ASE) {
		// 根据ASE判断用户是否过期
		String info = null;
		try {
			info = jedisCluster.get(ASE);
		} catch (Exception e) {
			throw new ServerException("redis查询ASE失败");
		}
		WeChatInfo weChatInfo = null;
		if(StringUtils.isNotBlank(info)) {
			weChatInfo = JsonUtils.jsonToPojo(info, WeChatInfo.class);
		} else {
			return WxResult.build(400, "用户登录过期");
		}
		// 根据opid获得user
		User user = userMapper.selectByOpenid(weChatInfo.getOpenId());
		product.setUpdated(new Date());
		try {
			MaintenanceTableExample maintenanceTableExample = new MaintenanceTableExample();
			Criteria criteria = maintenanceTableExample.createCriteria();
			criteria.andCardEqualTo(user.getCard());
			MaintenanceTableMapper.updateByExampleSelective(product, maintenanceTableExample);
		} catch (Exception e) {
			throw new ServerException("更新维修品信息失败");
		}
		return WxResult.ok();
	}

	/*
	 * 获得所有的维修信息
	 * @see com.weixin.service.ServerService#getAllMainTenance()
	 */
	@Override
	public WxResult getAllMainTenance() {
		MaintenanceTableExample maintenanceTableExample = new MaintenanceTableExample();
		Criteria criteria = maintenanceTableExample.createCriteria();
		List<MaintenanceTable> list = null;
		try {
			list =  MaintenanceTableMapper.selectByExample(maintenanceTableExample);
		} catch (Exception e) {
			throw new ServerException("获取所有维修品信息失败");
		}
		return WxResult.ok(list);
	}

	/*
	 *  删除维修信息
	 * 	 * @see com.weixin.service.ServerService#delMainTenance(java.lang.Long, java.lang.String)
	 */
	@Override
	public WxResult delMainTenance(Long uids, String ASE) {
		// 根据ASE判断用户是否过期
		String info = null;
		try {
			info = jedisCluster.get(ASE);
		} catch (Exception e) {
			throw new ServerException("redis查询ASE失败");
		}
		WeChatInfo weChatInfo = null;
		if(StringUtils.isNotBlank(info)) {
			weChatInfo = JsonUtils.jsonToPojo(info, WeChatInfo.class);
		} else {
			return WxResult.build(400, "用户登录过期");
		}
		// 根据ids对数据库进行操作
		String[] ids = uids.toString().split(",");
		for(String str:ids) {
			MaintenanceTableExample maintenanceTableExample = new MaintenanceTableExample();
			Criteria criteria = maintenanceTableExample.createCriteria();
			criteria.andUidEqualTo(str);
			// 删除
			try {
				MaintenanceTableMapper.deleteByExample(maintenanceTableExample);
			} catch (Exception e) {
				throw new ServerException("根据id删除维修品信息失败");
			}
		}
		return WxResult.ok();
	}

	/*
	 * 根据地点获取id信息
	 * @see com.weixin.service.ServerService#getAllMainTenanceByTitle(java.lang.String)
	 */
	@Override
	public WxResult getAllMainTenanceByTitle(String title) {
		MaintenanceTableExample maintenanceTableExample = new MaintenanceTableExample();
		Criteria criteria = maintenanceTableExample.createCriteria();
		criteria.andLocationTitleEqualTo(title);
		List<MaintenanceTable> list = null;
		try {
			list = MaintenanceTableMapper.selectByExample(maintenanceTableExample);
		} catch (Exception e) {
			throw new ServerException("根据地点名称获取id信息失败");
		}
		return WxResult.ok(list);
	}
	
	/*
	 *  判断用户的信息是否完善
	 */
	public boolean isAllInfomation(User user) {
		if(user.getName() == null || user.getCard() == null || user.getClassId() == null
				|| user.getCollege() == null || user.getPhoneNumber() == null
				|| user.getDomain() == null || user.getPic() == null || user.getSex() == null 
				|| user.getDormitory() == null) {
			return false;
		}
		return true;
	}
	
	
}
