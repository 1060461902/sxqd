package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.CompanyView;
import edu.zjgsu.ito.model.CompanyViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompanyViewMapper {
    long countByExample(CompanyViewExample example);

    int deleteByExample(CompanyViewExample example);

    int insert(CompanyView record);

    int insertSelective(CompanyView record);


    CompanyView selectByKey(Integer id);

    CompanyView selectByName(String companyName);

    List<CompanyView> selectByExample(CompanyViewExample example);

    int updateByExampleSelective(@Param("record") CompanyView record, @Param("example") CompanyViewExample example);

    int updateByExample(@Param("record") CompanyView record, @Param("example") CompanyViewExample example);
}