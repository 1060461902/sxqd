package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.StudentRecruitmentMapper;
import edu.zjgsu.ito.model.StudentRecruitment;
import edu.zjgsu.ito.model.StudentRecruitmentExample;
import edu.zjgsu.ito.service.StudentRecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentRecruitmentServiceImpl implements StudentRecruitmentService{
    @Autowired
    StudentRecruitmentMapper studentRecruitmentMapper;
    @Override
    public long countByExample(StudentRecruitmentExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(StudentRecruitmentExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(StudentRecruitment record) {
        return 0;
    }

    @Override
    public int insertSelective(StudentRecruitment record) {
        return 0;
    }

    @Override
    public List<StudentRecruitment> selectByExample(StudentRecruitmentExample example) {
        List<StudentRecruitment> studentRecruitments=studentRecruitmentMapper.selectByExample(example);
        return studentRecruitments;
    }

    @Override
    public StudentRecruitment selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(StudentRecruitment record, StudentRecruitmentExample example) {
        return 0;
    }

    @Override
    public int updateByExample(StudentRecruitment record, StudentRecruitmentExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(StudentRecruitment record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StudentRecruitment record) {
        return 0;
    }
}
