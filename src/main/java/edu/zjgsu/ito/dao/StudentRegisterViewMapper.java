package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.StudentRegisterView;
import edu.zjgsu.ito.model.StudentRegisterViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentRegisterViewMapper {
    long countByExample(StudentRegisterViewExample example);

    int deleteByExample(StudentRegisterViewExample example);

    int insert(StudentRegisterView record);

    int insertSelective(StudentRegisterView record);

    List<StudentRegisterView> selectByExample(StudentRegisterViewExample example);

    int updateByExampleSelective(@Param("record") StudentRegisterView record, @Param("example") StudentRegisterViewExample example);

    int updateByExample(@Param("record") StudentRegisterView record, @Param("example") StudentRegisterViewExample example);
}