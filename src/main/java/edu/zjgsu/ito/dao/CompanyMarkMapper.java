package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.CompanyMark;
import edu.zjgsu.ito.model.CompanyMarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompanyMarkMapper {
    long countByExample(CompanyMarkExample example);

    int deleteByExample(CompanyMarkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CompanyMark record);

    int insertSelective(CompanyMark record);

    List<CompanyMark> selectByExample(CompanyMarkExample example);

    CompanyMark selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CompanyMark record, @Param("example") CompanyMarkExample example);

    int updateByExample(@Param("record") CompanyMark record, @Param("example") CompanyMarkExample example);

    int updateByPrimaryKeySelective(CompanyMark record);

    int updateByPrimaryKey(CompanyMark record);
}