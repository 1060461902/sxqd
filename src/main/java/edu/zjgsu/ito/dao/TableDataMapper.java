package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.TableData;
import edu.zjgsu.ito.model.TableDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TableDataMapper {
    long countByExample(TableDataExample example);

    int deleteByExample(TableDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TableData record);

    int insertSelective(TableData record);

    List<TableData> selectByExample(TableDataExample example);

    TableData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TableData record, @Param("example") TableDataExample example);

    int updateByExample(@Param("record") TableData record, @Param("example") TableDataExample example);

    int updateByPrimaryKeySelective(TableData record);

    int updateByPrimaryKey(TableData record);
}