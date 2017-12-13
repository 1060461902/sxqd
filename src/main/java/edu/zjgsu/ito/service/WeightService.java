package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.Weight;
import edu.zjgsu.ito.model.WeightExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeightService {
    long countByExample(WeightExample example);

    int deleteByExample(WeightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Weight record);

    int insertSelective(Weight record);

    List<Weight> selectByExample(WeightExample example);

    Weight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Weight record, @Param("example") WeightExample example);

    int updateByExample(@Param("record") Weight record, @Param("example") WeightExample example);

    int updateByPrimaryKeySelective(Weight record);

    int updateByPrimaryKey(Weight record);
}
