package com.weixin.mapper;

import com.weixin.pojo.MaintenanceTable;
import com.weixin.pojo.MaintenanceTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaintenanceTableMapper {
    int countByExample(MaintenanceTableExample example);

    int deleteByExample(MaintenanceTableExample example);

    int deleteByPrimaryKey(String uid);

    int insert(MaintenanceTable record);

    int insertSelective(MaintenanceTable record);

    List<MaintenanceTable> selectByExample(MaintenanceTableExample example);

    MaintenanceTable selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") MaintenanceTable record, @Param("example") MaintenanceTableExample example);

    int updateByExample(@Param("record") MaintenanceTable record, @Param("example") MaintenanceTableExample example);

    int updateByPrimaryKeySelective(MaintenanceTable record);

    int updateByPrimaryKey(MaintenanceTable record);
    
    
}