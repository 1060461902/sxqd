package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.StudentProjectMapper;
import edu.zjgsu.ito.model.StudentProject;
import edu.zjgsu.ito.model.StudentProjectExample;
import edu.zjgsu.ito.service.StudentProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentProjectServiceImpl implements StudentProjectService{
    @Autowired
    StudentProjectMapper studentProjectMapper;

    @Override
    public long countByExample(StudentProjectExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(StudentProjectExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(StudentProject record) {
        return 0;
    }

    @Override
    public int insertSelective(StudentProject record) {
        return 0;
    }

    @Override
    public List<StudentProject> selectByExample(StudentProjectExample example) {
        List<StudentProject> studentProjects=studentProjectMapper.selectByExample(example);
        return studentProjects;
    }

    @Override
    public StudentProject selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(StudentProject record, StudentProjectExample example) {
        return 0;
    }

    @Override
    public int updateByExample(StudentProject record, StudentProjectExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(StudentProject record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StudentProject record) {
        return 0;
    }
}
