package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.CompanyImage;
import edu.zjgsu.ito.model.CompanyImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyImageMapper {
    long countByExample(CompanyImageExample example);

    int deleteByExample(CompanyImageExample example);

    int deleteByPrimaryKey(String id);

    int insert(CompanyImage record);

    int insertSelective(CompanyImage record);

    List<CompanyImage> selectByExample(CompanyImageExample example);

    CompanyImage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CompanyImage record, @Param("example") CompanyImageExample example);

    int updateByExample(@Param("record") CompanyImage record, @Param("example") CompanyImageExample example);

    int updateByPrimaryKeySelective(CompanyImage record);

    int updateByPrimaryKey(CompanyImage record);
}