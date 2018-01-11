package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.StudentRecruitmentViewMapper;
import edu.zjgsu.ito.model.StudentRecruitmentView;
import edu.zjgsu.ito.model.StudentRecruitmentViewExample;
import edu.zjgsu.ito.service.StudentRecruitmentViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentRecruitmentViewServiceImpl implements StudentRecruitmentViewService{
    @Autowired
    StudentRecruitmentViewMapper studentRecruitmentViewMapper;
    @Override
    public long countByExample(StudentRecruitmentViewExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(StudentRecruitmentViewExample example) {
        return 0;
    }

    @Override
    public int insert(StudentRecruitmentView record) {
        return 0;
    }

    @Override
    public int insertSelective(StudentRecruitmentView record) {
        return 0;
    }

    @Override
    public List<StudentRecruitmentView> selectByExample(StudentRecruitmentViewExample example) {
        List<StudentRecruitmentView> studentRecruitmentViews=studentRecruitmentViewMapper.selectByExample(example);
        return studentRecruitmentViews;

    }

    @Override
    public int updateByExampleSelective(StudentRecruitmentView record, StudentRecruitmentViewExample example) {
        return 0;
    }

    @Override
    public int updateByExample(StudentRecruitmentView record, StudentRecruitmentViewExample example) {
        return 0;
    }
}
