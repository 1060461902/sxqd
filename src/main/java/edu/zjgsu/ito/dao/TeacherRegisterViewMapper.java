package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.TeacherRegisterView;
import edu.zjgsu.ito.model.TeacherRegisterViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherRegisterViewMapper {
    long countByExample(TeacherRegisterViewExample example);

    int deleteByExample(TeacherRegisterViewExample example);

    int insert(TeacherRegisterView record);

    int insertSelective(TeacherRegisterView record);

    List<TeacherRegisterView> selectByExample(TeacherRegisterViewExample example);

    int updateByExampleSelective(@Param("record") TeacherRegisterView record, @Param("example") TeacherRegisterViewExample example);

    int updateByExample(@Param("record") TeacherRegisterView record, @Param("example") TeacherRegisterViewExample example);
}