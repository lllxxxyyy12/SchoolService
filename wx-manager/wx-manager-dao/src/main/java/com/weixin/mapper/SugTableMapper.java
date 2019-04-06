package com.weixin.mapper;

import com.weixin.pojo.SugTable;
import com.weixin.pojo.SugTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SugTableMapper {
    int countByExample(SugTableExample example);

    int deleteByExample(SugTableExample example);

    int deleteByPrimaryKey(String uid);

    int insert(SugTable record);

    int insertSelective(SugTable record);

    List<SugTable> selectByExample(SugTableExample example);

    SugTable selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") SugTable record, @Param("example") SugTableExample example);

    int updateByExample(@Param("record") SugTable record, @Param("example") SugTableExample example);

    int updateByPrimaryKeySelective(SugTable record);

    int updateByPrimaryKey(SugTable record);
}