package com.weixin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.weixin.common.result.WxResult;
import com.weixin.commons.exception.ServerException;
import com.weixin.mapper.InContentClassifyMapper;
import com.weixin.mapper.InContentMapper;
import com.weixin.pojo.InContent;
import com.weixin.pojo.InContentClassify;
import com.weixin.pojo.InContentClassifyExample;
import com.weixin.pojo.InContentClassifyExample.Criteria;
import com.weixin.service.ContentService;
import com.xeixin.commom.untils.WxTreeNode;

@Service
public class ContentServiceImpl implements ContentService {
	@Value("${SHOW_IMAGE_ID}")
	private Long SHOW_IMAGE_ID;
	
	@Autowired
	private InContentMapper inContentMapper; 
	@Autowired
	private InContentClassifyMapper inContentClassifyMapper;
	/*
	 * 获得首页轮播图
	 * @see com.weixin.service.ContentService#getAllImage()
	 */
	@Override
	public WxResult getAllImage() {
		InContent inContent = null;
		try {
			inContent = inContentMapper.selectByCategoryId(SHOW_IMAGE_ID);
		} catch (Exception e) {
			throw new ServerException("轮播图获取失败");
		}
		String[] images = inContent.getPic().split(",");
		return WxResult.ok(images);
	}
	
	/*
	 *  根据id获得子节点
	 * @see com.weixin.service.ContentService#getChildren(java.lang.Long)
	 */
	@Override
	public WxResult getChildren(Long id) {
		InContentClassifyExample inContentClassifyExample = new InContentClassifyExample();
		Criteria criteria = inContentClassifyExample.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<InContentClassify> list = null;
		List<WxTreeNode> results = new ArrayList<>();
		try {
			list = inContentClassifyMapper.selectByExample(inContentClassifyExample);
			for(InContentClassify i:list) {
				WxTreeNode node = new WxTreeNode();
				node.setId(i.getId());
				node.setText(i.getContentTitle());
				node.setState(i.getIsParent().toString());
				results.add(node);
			}
		} catch (Exception e) {
			throw new ServerException("子节点获取失败");
		}
		return WxResult.ok(results);
	}

	/*
	 * 根据前端的固定的设施name查询子节点
	 * @see com.weixin.service.ContentService#getContentByTitle(java.lang.String)
	 */
	@Override
	public WxResult getContentByTitle(String title) {
		InContentClassifyExample inContentClassifyExample = new InContentClassifyExample();
		Criteria criteria = inContentClassifyExample.createCriteria();
		criteria.andContentTitleEqualTo(title);
		List<InContentClassify> list = null;
		try {
			list = inContentClassifyMapper.selectByExample(inContentClassifyExample);
		} catch (Exception e) {
			throw new ServerException("根据name获取子节点失败");
		}
		InContentClassify inContentClassify = list.get(0);
		InContentClassifyExample inClidrenContentClassifyExample = new InContentClassifyExample();
		Criteria childernCriteria = inClidrenContentClassifyExample.createCriteria();
		childernCriteria.andParentIdEqualTo(inContentClassify.getId());
		List<InContentClassify> childrenList = null;
		try {
			childrenList = inContentClassifyMapper.selectByExample(inClidrenContentClassifyExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return WxResult.ok(childrenList);
	}

}
