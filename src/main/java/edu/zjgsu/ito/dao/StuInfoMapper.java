package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.StuInfo;
import edu.zjgsu.ito.model.StuInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StuInfoMapper {
    long countByExample(StuInfoExample example);

    int deleteByExample(StuInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(StuInfo record);

    int insertSelective(StuInfo record);

    List<StuInfo> selectByExample(StuInfoExample example);

    StuInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StuInfo record, @Param("example") StuInfoExample example);

    int updateByExample(@Param("record") StuInfo record, @Param("example") StuInfoExample example);

    int updateByPrimaryKeySelective(StuInfo record);

    int updateByPrimaryKey(StuInfo record);
}