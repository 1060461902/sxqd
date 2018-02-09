package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.Report;
import edu.zjgsu.ito.model.ReportExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportService {
    long countByExample(ReportExample example);

    int deleteByExample(ReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Report record);

    int insertSelective(Report record);

    List<Report> selectByExample(ReportExample example);

    Report selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Report record, @Param("example") ReportExample example);

    int updateByExample(@Param("record") Report record, @Param("example") ReportExample example);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKey(Report record);
}
