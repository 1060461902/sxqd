package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.Summary;
import edu.zjgsu.ito.model.SummaryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SummaryService {

    long countByExample(SummaryExample example);

    int deleteByExample(SummaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Summary record);

    int insertSelective(Summary record);

    List<Summary> selectByExample(SummaryExample example);

    Summary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Summary record, @Param("example") SummaryExample example);

    int updateByExample(@Param("record") Summary record, @Param("example") SummaryExample example);

    int updateByPrimaryKeySelective(Summary record);

    int updateByPrimaryKey(Summary record);
}
