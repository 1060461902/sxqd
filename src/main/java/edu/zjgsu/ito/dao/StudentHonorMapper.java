package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.StudentHonor;
import edu.zjgsu.ito.model.StudentHonorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentHonorMapper {
    long countByExample(StudentHonorExample example);

    int deleteByExample(StudentHonorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentHonor record);

    int insertSelective(StudentHonor record);

    List<StudentHonor> selectByExample(StudentHonorExample example);

    StudentHonor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentHonor record, @Param("example") StudentHonorExample example);

    int updateByExample(@Param("record") StudentHonor record, @Param("example") StudentHonorExample example);

    int updateByPrimaryKeySelective(StudentHonor record);

    int updateByPrimaryKey(StudentHonor record);
}