package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.StudentClub;
import edu.zjgsu.ito.model.StudentClubExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentClubService {
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
