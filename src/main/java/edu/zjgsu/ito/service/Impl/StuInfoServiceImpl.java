package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.StuInfoMapper;
import edu.zjgsu.ito.model.StuInfo;
import edu.zjgsu.ito.model.StuInfoExample;
import edu.zjgsu.ito.service.StuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuInfoServiceImpl implements StuInfoService{
    @Autowired
    StuInfoMapper stuInfoMapper;

    @Override
    public long countByExample(StuInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(StuInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(StuInfo record) {
        return 0;
    }

    @Override
    public int insertSelective(StuInfo record) {
        return 0;
    }

    @Override
    public List<StuInfo> selectByExample(StuInfoExample example) {
        return null;
    }

    @Override
    public StuInfo selectByPrimaryKey(String id) {
        StuInfo stuInfo = stuInfoMapper.selectByPrimaryKey(id);

        return stuInfo;
    }

    @Override
    public int updateByExampleSelective(StuInfo record, StuInfoExample example) {
        return 0;
    }

    @Override
    public int updateByExample(StuInfo record, StuInfoExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(StuInfo record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StuInfo record) {
        int status;
        status = stuInfoMapper.updateByPrimaryKey(record);
        return status;
    }
}
