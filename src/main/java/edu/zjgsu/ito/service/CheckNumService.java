package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.CheckNum;
import edu.zjgsu.ito.model.CheckNumExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 宿舍 on 2017/12/14.
 */
public interface CheckNumService {
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
