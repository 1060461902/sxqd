package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.ExcelView;
import edu.zjgsu.ito.model.ExcelViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExcelViewMapper {
    long countByExample(ExcelViewExample example);

    int deleteByExample(ExcelViewExample example);

    int insert(ExcelView record);

    int insertSelective(ExcelView record);

    List<ExcelView> selectByExample(ExcelViewExample example);

    int updateByExampleSelective(@Param("record") ExcelView record, @Param("example") ExcelViewExample example);

    int updateByExample(@Param("record") ExcelView record, @Param("example") ExcelViewExample example);
}