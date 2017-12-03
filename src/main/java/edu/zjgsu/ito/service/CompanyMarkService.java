package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.CompanyMarkExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyMarkService {
    long countByExample(CompanyMarkExample example);

    int deleteByExample(CompanyMarkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(edu.zjgsu.ito.model.CompanyMark record);

    int insertSelective(edu.zjgsu.ito.model.CompanyMark record);

    List<edu.zjgsu.ito.model.CompanyMark> selectByExample(CompanyMarkExample example);

    edu.zjgsu.ito.model.CompanyMark selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") edu.zjgsu.ito.model.CompanyMark record, @Param("example") CompanyMarkExample example);

    int updateByExample(@Param("record") edu.zjgsu.ito.model.CompanyMark record, @Param("example") CompanyMarkExample example);

    int updateByPrimaryKeySelective(edu.zjgsu.ito.model.CompanyMark record);

    int updateByPrimaryKey(edu.zjgsu.ito.model.CompanyMark record);
}
