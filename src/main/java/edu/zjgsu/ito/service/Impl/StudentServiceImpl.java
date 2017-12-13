package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.service.StudentService;
import edu.zjgsu.ito.dao.StudentMapper;
import edu.zjgsu.ito.model.Student;
import edu.zjgsu.ito.model.StudentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import edu.zjgsu.ito.dao.StudentMapper;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override

    public long countByExample(StudentExample example) {
        long count;
        count=studentMapper.countByExample(example);
        return count;

    }

    @Override
    public int deleteByExample(StudentExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Student record) {
        return 0;
    }

    @Override
    public int insertSelective(Student record) {
        return 0;
    }

    @Override
    public List<Student> selectByExample(StudentExample example) {
        List<Student> students=studentMapper.selectByExample(example);
        return students;
    }

    @Override
    public Student selectByPrimaryKey(Integer id) {
        Student Student = studentMapper.selectByPrimaryKey(id);
        return Student;
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
