package com.weixin.mapper;

import com.weixin.pojo.InContent;
import com.weixin.pojo.InContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InContentMapper {
    int countByExample(InContentExample example);

    int deleteByExample(InContentExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(InContent record);

    int insertSelective(InContent record);

    List<InContent> selectByExample(InContentExample example);

    InContent selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") InContent record, @Param("example") InContentExample example);

    int updateByExample(@Param("record") InContent record, @Param("example") InContentExample example);

    int updateByPrimaryKeySelective(InContent record);

    int updateByPrimaryKey(InContent record);
    
    InContent selectByCategoryId(Long categoryId);
}