package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.StudentSkillMapper;
import edu.zjgsu.ito.model.StudentSkill;
import edu.zjgsu.ito.model.StudentSkillExample;
import edu.zjgsu.ito.service.StudentSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentSkillServiceImpl implements StudentSkillService{
    @Autowired
    StudentSkillMapper studentSkillMapper;
    @Override
    public long countByExample(StudentSkillExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(StudentSkillExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(StudentSkill record) {
        return 0;
    }

    @Override
    public int insertSelective(StudentSkill record) {
        return 0;
    }

    @Override
    public List<StudentSkill> selectByExample(StudentSkillExample example) {
        List<StudentSkill> studentSkills=studentSkillMapper.selectByExample(example);
        return studentSkills;
    }

    @Override
    public StudentSkill selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(StudentSkill record, StudentSkillExample example) {
        return 0;
    }

    @Override
    public int updateByExample(StudentSkill record, StudentSkillExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(StudentSkill record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StudentSkill record) {
        return 0;
    }
}
