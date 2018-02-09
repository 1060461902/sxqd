package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.TableData;
import edu.zjgsu.ito.model.TableDataExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 宿舍 on 2017/12/14.
 */
public interface TableDataService {

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
