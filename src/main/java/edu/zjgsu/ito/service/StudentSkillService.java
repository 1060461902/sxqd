package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.StudentSkill;
import edu.zjgsu.ito.model.StudentSkillExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentSkillService {
    long countByExample(StudentSkillExample example);

    int deleteByExample(StudentSkillExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentSkill record);

    int insertSelective(StudentSkill record);

    List<StudentSkill> selectByExample(StudentSkillExample example);

    StudentSkill selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentSkill record, @Param("example") StudentSkillExample example);

    int updateByExample(@Param("record") StudentSkill record, @Param("example") StudentSkillExample example);

    int updateByPrimaryKeySelective(StudentSkill record);

    int updateByPrimaryKey(StudentSkill record);
}
