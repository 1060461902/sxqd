package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.StudentRecruitmentView;
import edu.zjgsu.ito.model.StudentRecruitmentViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentRecruitmentViewMapper {
    long countByExample(StudentRecruitmentViewExample example);

    int deleteByExample(StudentRecruitmentViewExample example);

    int insert(StudentRecruitmentView record);

    int insertSelective(StudentRecruitmentView record);

    List<StudentRecruitmentView> selectByExample(StudentRecruitmentViewExample example);

    int updateByExampleSelective(@Param("record") StudentRecruitmentView record, @Param("example") StudentRecruitmentViewExample example);

    int updateByExample(@Param("record") StudentRecruitmentView record, @Param("example") StudentRecruitmentViewExample example);
}