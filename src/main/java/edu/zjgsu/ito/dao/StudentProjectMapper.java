package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.StudentProject;
import edu.zjgsu.ito.model.StudentProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentProjectMapper {
    long countByExample(StudentProjectExample example);

    int deleteByExample(StudentProjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentProject record);

    int insertSelective(StudentProject record);

    List<StudentProject> selectByExample(StudentProjectExample example);

    StudentProject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentProject record, @Param("example") StudentProjectExample example);

    int updateByExample(@Param("record") StudentProject record, @Param("example") StudentProjectExample example);

    int updateByPrimaryKeySelective(StudentProject record);

    int updateByPrimaryKey(StudentProject record);
}