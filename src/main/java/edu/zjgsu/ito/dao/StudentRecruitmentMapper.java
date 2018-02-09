package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.StudentRecruitment;
import edu.zjgsu.ito.model.StudentRecruitmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentRecruitmentMapper {
    long countByExample(StudentRecruitmentExample example);

    int deleteByExample(StudentRecruitmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentRecruitment record);

    int insertSelective(StudentRecruitment record);

    List<StudentRecruitment> selectByExample(StudentRecruitmentExample example);

    StudentRecruitment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentRecruitment record, @Param("example") StudentRecruitmentExample example);

    int updateByExample(@Param("record") StudentRecruitment record, @Param("example") StudentRecruitmentExample example);

    int updateByPrimaryKeySelective(StudentRecruitment record);

    int updateByPrimaryKey(StudentRecruitment record);
}