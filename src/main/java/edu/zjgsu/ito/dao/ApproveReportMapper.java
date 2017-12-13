package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.ApproveReport;
import edu.zjgsu.ito.model.ApproveReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApproveReportMapper {
    long countByExample(ApproveReportExample example);

    int deleteByExample(ApproveReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApproveReport record);

    int insertSelective(ApproveReport record);

    List<ApproveReport> selectByExample(ApproveReportExample example);

    ApproveReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApproveReport record, @Param("example") ApproveReportExample example);

    int updateByExample(@Param("record") ApproveReport record, @Param("example") ApproveReportExample example);

    int updateByPrimaryKeySelective(ApproveReport record);

    int updateByPrimaryKey(ApproveReport record);
}