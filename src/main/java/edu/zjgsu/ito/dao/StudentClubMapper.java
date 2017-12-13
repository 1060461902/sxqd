package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.StudentClub;
import edu.zjgsu.ito.model.StudentClubExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentClubMapper {
    long countByExample(StudentClubExample example);

    int deleteByExample(StudentClubExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentClub record);

    int insertSelective(StudentClub record);

    List<StudentClub> selectByExample(StudentClubExample example);

    StudentClub selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentClub record, @Param("example") StudentClubExample example);

    int updateByExample(@Param("record") StudentClub record, @Param("example") StudentClubExample example);

    int updateByPrimaryKeySelective(StudentClub record);

    int updateByPrimaryKey(StudentClub record);
}