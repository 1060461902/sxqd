package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.CompanyInfo;
import edu.zjgsu.ito.model.CompanyInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyInfoMapper {
    long countByExample(CompanyInfoExample example);

    int deleteByExample(CompanyInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(CompanyInfo record);

    int insertSelective(CompanyInfo record);

    List<CompanyInfo> selectByExample(CompanyInfoExample example);

    CompanyInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CompanyInfo record, @Param("example") CompanyInfoExample example);

    int updateByExample(@Param("record") CompanyInfo record, @Param("example") CompanyInfoExample example);

    int updateByPrimaryKeySelective(CompanyInfo record);

    int updateByPrimaryKey(CompanyInfo record);
}