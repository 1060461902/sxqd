package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.StudentClubMapper;
import edu.zjgsu.ito.model.StudentClub;
import edu.zjgsu.ito.model.StudentClubExample;
import edu.zjgsu.ito.service.StudentClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentClubServiceImpl implements StudentClubService{
    @Autowired
    StudentClubMapper studentClubMapper;
    @Override
    public long countByExample(StudentClubExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(StudentClubExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(StudentClub record) {
        return 0;
    }

    @Override
    public int insertSelective(StudentClub record) {
        return 0;
    }

    @Override
    public List<StudentClub> selectByExample(StudentClubExample example) {
        List<StudentClub> studentClubs=studentClubMapper.selectByExample(example);
        return studentClubs;
    }

    @Override
    public StudentClub selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(StudentClub record, StudentClubExample example) {
        return 0;
    }

    @Override
    public int updateByExample(StudentClub record, StudentClubExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(StudentClub record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StudentClub record) {
        return 0;
    }
}
