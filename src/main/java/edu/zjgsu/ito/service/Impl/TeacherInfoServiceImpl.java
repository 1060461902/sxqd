package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.TeacherInfoMapper;
import edu.zjgsu.ito.model.TeacherInfo;
import edu.zjgsu.ito.model.TeacherInfoExample;
import edu.zjgsu.ito.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherInfoServiceImpl implements TeacherInfoService {
    @Autowired
    TeacherInfoMapper teacherInfoMapper;

    @Override
    public long countByExample(TeacherInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(TeacherInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(TeacherInfo record) {
        return 0;
    }

    @Override
    public int insertSelective(TeacherInfo record) {
        return 0;
    }

    @Override
    public List<TeacherInfo> selectByExample(TeacherInfoExample example) {
        List<TeacherInfo> teacherInfos = teacherInfoMapper.selectByExample(example);
        return teacherInfos;
    }

    @Override
    public TeacherInfo selectByPrimaryKey(String id) {
        TeacherInfo teacherInfo = teacherInfoMapper.selectByPrimaryKey(id);
        return teacherInfo;
    }

    @Override
    public int updateByExampleSelective(TeacherInfo record, TeacherInfoExample example) {
        return 0;
    }

    @Override
    public int updateByExample(TeacherInfo record, TeacherInfoExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(TeacherInfo record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TeacherInfo record) {
        return 0;
    }
}
