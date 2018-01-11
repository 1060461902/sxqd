package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.DailyCheck;
import edu.zjgsu.ito.model.DailyCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyCheckMapper {
    long countByExample(DailyCheckExample example);

    int deleteByExample(DailyCheckExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DailyCheck record);

    int insertSelective(DailyCheck record);

    List<DailyCheck> selectByExample(DailyCheckExample example);

    DailyCheck selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DailyCheck record, @Param("example") DailyCheckExample example);

    int updateByExample(@Param("record") DailyCheck record, @Param("example") DailyCheckExample example);

    int updateByPrimaryKeySelective(DailyCheck record);

    int updateByPrimaryKey(DailyCheck record);
}