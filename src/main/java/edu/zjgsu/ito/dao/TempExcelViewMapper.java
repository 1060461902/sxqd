package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.TempExcelView;
import edu.zjgsu.ito.model.TempExcelViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TempExcelViewMapper {
    long countByExample(TempExcelViewExample example);

    int deleteByExample(TempExcelViewExample example);

    int insert(TempExcelView record);

    int insertSelective(TempExcelView record);

    List<TempExcelView> selectByExample(TempExcelViewExample example);

    int updateByExampleSelective(@Param("record") TempExcelView record, @Param("example") TempExcelViewExample example);

    int updateByExample(@Param("record") TempExcelView record, @Param("example") TempExcelViewExample example);
}