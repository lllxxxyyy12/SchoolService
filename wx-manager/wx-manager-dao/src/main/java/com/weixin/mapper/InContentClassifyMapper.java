package com.weixin.mapper;

import com.weixin.pojo.InContentClassify;
import com.weixin.pojo.InContentClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InContentClassifyMapper {
    int countByExample(InContentClassifyExample example);

    int deleteByExample(InContentClassifyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InContentClassify record);

    int insertSelective(InContentClassify record);

    List<InContentClassify> selectByExample(InContentClassifyExample example);

    InContentClassify selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InContentClassify record, @Param("example") InContentClassifyExample example);

    int updateByExample(@Param("record") InContentClassify record, @Param("example") InContentClassifyExample example);

    int updateByPrimaryKeySelective(InContentClassify record);

    int updateByPrimaryKey(InContentClassify record);
}