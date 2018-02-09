package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.Road;
import edu.zjgsu.ito.model.RoadExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoadService {
    long countByExample(RoadExample example);

    int deleteByExample(RoadExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Road record);

    int insertSelective(Road record);

    List<Road> selectByExample(RoadExample example);

    Road selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Road record, @Param("example") RoadExample example);

    int updateByExample(@Param("record") Road record, @Param("example") RoadExample example);

    int updateByPrimaryKeySelective(Road record);

    int updateByPrimaryKey(Road record);
}
