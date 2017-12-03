package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.service.StudentService;
import edu.zjgsu.ito.dao.StudentMapper;
import edu.zjgsu.ito.model.Student;
import edu.zjgsu.ito.model.StudentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override

    public long countByExample(StudentExample example) {

        return 0;
    }

    @Override
    public int deleteByExample(StudentExample example) {
        return 0;
    }

    @Override
    public int insert(Student record) {
        int status;
        status = studentMapper.insert(record);

        return status;
    }

    @Override
    public int insertSelective(Student record) {
        return 0;
    }

    @Override
    public List<Student> selectByExample(StudentExample example) {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public Student selectByPrimaryKey(Integer id) {
        Student student = studentMapper.selectByPrimaryKey(id);

        return student;
    }

    @Override
    public int updateByExampleSelective(Student record, StudentExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Student record, StudentExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        int status;
        status = studentMapper.updateByPrimaryKey(record);
        return status;
    }
}
