package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.CheckNum;
import edu.zjgsu.ito.model.CheckNumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckNumMapper {
    long countByExample(CheckNumExample example);

    int deleteByExample(CheckNumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CheckNum record);

    int insertSelective(CheckNum record);

    List<CheckNum> selectByExample(CheckNumExample example);

    CheckNum selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CheckNum record, @Param("example") CheckNumExample example);

    int updateByExample(@Param("record") CheckNum record, @Param("example") CheckNumExample example);

    int updateByPrimaryKeySelective(CheckNum record);

    int updateByPrimaryKey(CheckNum record);
}