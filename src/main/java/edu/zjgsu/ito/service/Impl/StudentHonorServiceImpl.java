package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.StudentHonorMapper;
import edu.zjgsu.ito.model.StudentHonor;
import edu.zjgsu.ito.model.StudentHonorExample;
import edu.zjgsu.ito.service.StudentHonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentHonorServiceImpl implements StudentHonorService {
    @Autowired
    StudentHonorMapper studentHonorMapper;

    @Override
    public long countByExample(StudentHonorExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(StudentHonorExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(StudentHonor record) {
        return 0;
    }

    @Override
    public int insertSelective(StudentHonor record) {
        return 0;
    }

    @Override
    public List<StudentHonor> selectByExample(StudentHonorExample example) {
        List<StudentHonor> studentHonors=studentHonorMapper.selectByExample(example);
        return studentHonors;
    }

    @Override
    public StudentHonor selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(StudentHonor record, StudentHonorExample example) {
        return 0;
    }

    @Override
    public int updateByExample(StudentHonor record, StudentHonorExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(StudentHonor record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StudentHonor record) {
        return 0;
    }
}
