package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.RecruitmentMapper;
import edu.zjgsu.ito.model.Recruitment;
import edu.zjgsu.ito.model.RecruitmentExample;
import edu.zjgsu.ito.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {
    @Autowired
    RecruitmentMapper recruitmentMapper;

    @Override
    public int deleteByExample(RecruitmentExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(Recruitment record) {
        return 0;
    }

    @Override
    public int insertSelective(Recruitment record) {
        return 0;
    }

    @Override
    public List<Recruitment> selectByExample(RecruitmentExample example) {
                List<Recruitment> recruitmentList= recruitmentMapper.selectByExample(example);
                return recruitmentList;
    }

    @Override
    public Recruitment selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Recruitment record, RecruitmentExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Recruitment record, RecruitmentExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Recruitment record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Recruitment record) {
        return 0;
    }

    @Override
    public long countByExample(RecruitmentExample example) {
        return 0;
    }
}
