package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.TeacherMapper;
import edu.zjgsu.ito.model.Teacher;
import edu.zjgsu.ito.model.TeacherExample;
import edu.zjgsu.ito.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public long countByExample(TeacherExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(TeacherExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Teacher record) {
        return 0;
    }

    @Override
    public int insertSelective(Teacher record) {
        return 0;
    }

    @Override
    public List<Teacher> selectByExample(TeacherExample example) {
        List<Teacher> Teachers = teacherMapper.selectByExample(example);
        return Teachers;
    }

    @Override
    public Teacher selectByPrimaryKey(Integer id) {
        Teacher Teacher = teacherMapper.selectByPrimaryKey(id);
        return Teacher;
    }

    @Override
    public int updateByExampleSelective(Teacher record, TeacherExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Teacher record, TeacherExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Teacher record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Teacher record) {
        return 0;
    }
}
