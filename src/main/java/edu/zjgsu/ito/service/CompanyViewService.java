package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.CompanyView;
import edu.zjgsu.ito.model.CompanyViewExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyViewService {
    long countByExample(CompanyViewExample example);

    int deleteByExample(CompanyViewExample example);

    int insert(CompanyView record);

    int insertSelective(CompanyView record);

    List<CompanyView> selectByExample(CompanyViewExample example);

    int updateByExampleSelective(@Param("record") CompanyView record, @Param("example") CompanyViewExample example);

    int updateByExample(@Param("record") CompanyView record, @Param("example") CompanyViewExample example);

}
