package com.weixin.mapper;

import com.weixin.pojo.SugSort;
import com.weixin.pojo.SugSortExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SugSortMapper {
    int countByExample(SugSortExample example);

    int deleteByExample(SugSortExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SugSort record);

    int insertSelective(SugSort record);

    List<SugSort> selectByExample(SugSortExample example);

    SugSort selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SugSort record, @Param("example") SugSortExample example);

    int updateByExample(@Param("record") SugSort record, @Param("example") SugSortExample example);

    int updateByPrimaryKeySelective(SugSort record);

    int updateByPrimaryKey(SugSort record);
}