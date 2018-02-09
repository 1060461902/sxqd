package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.FinalExcelView;
import edu.zjgsu.ito.model.FinalExcelViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FinalExcelViewMapper {
    long countByExample(FinalExcelViewExample example);

    int deleteByExample(FinalExcelViewExample example);

    int insert(FinalExcelView record);

    int insertSelective(FinalExcelView record);

    List<FinalExcelView> selectByExample(FinalExcelViewExample example);

    int updateByExampleSelective(@Param("record") FinalExcelView record, @Param("example") FinalExcelViewExample example);

    int updateByExample(@Param("record") FinalExcelView record, @Param("example") FinalExcelViewExample example);
}