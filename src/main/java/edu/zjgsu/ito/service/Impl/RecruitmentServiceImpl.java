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
    public int deleteByPrimaryKey(Integer id) {
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
    public Recruitment selectByPrimaryKey(Integer id) {
        Recruitment recruitment=recruitmentMapper.selectByPrimaryKey(id);
        return recruitment;
    }



    @Override
    public List<Recruitment> selectByCompanyId(Integer companyId) {
        List<Recruitment> recruitmentList= recruitmentMapper.selectByCompanyId(companyId);
        return recruitmentList;
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
        int status;

        status = recruitmentMapper.updateByPrimaryKey(record);

        return status;
    }

    @Override
    public long countByExample(RecruitmentExample example) {
        long count;
        count = recruitmentMapper.countByExample(example);

        return count;
    }
}
