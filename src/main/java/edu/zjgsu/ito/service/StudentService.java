package edu.zjgsu.ito.service;
import edu.zjgsu.ito.model.Student;
import edu.zjgsu.ito.model.StudentExample;
import org.apache.ibatis.annotations.Param;
//import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface StudentService {
    long countByExample(StudentExample example);


    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);


    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}
