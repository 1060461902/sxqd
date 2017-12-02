package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.Company;
import edu.zjgsu.ito.model.CompanyView;
import edu.zjgsu.ito.model.CompanyViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyViewMapper {
    long countByExample(CompanyViewExample example);

    int deleteByExample(CompanyViewExample example);

    int insert(CompanyView record);

    int insertSelective(CompanyView record);

    List<CompanyView> selectByExample(CompanyViewExample example);

    CompanyView selectByKey(String id);

    CompanyView selectByName(String companyName);


    int updateByExampleSelective(@Param("record") CompanyView record, @Param("example") CompanyViewExample example);

    int updateByExample(@Param("record") CompanyView record, @Param("example") CompanyViewExample example);
}