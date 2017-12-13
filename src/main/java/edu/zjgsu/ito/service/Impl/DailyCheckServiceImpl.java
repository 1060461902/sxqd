package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.DailyCheckMapper;
import edu.zjgsu.ito.model.DailyCheck;
import edu.zjgsu.ito.model.DailyCheckExample;
import edu.zjgsu.ito.service.DailyCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DailyCheckServiceImpl implements DailyCheckService{
    @Autowired
    DailyCheckMapper dailyCheckMapper;
    @Override
    public long countByExample(DailyCheckExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(DailyCheckExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(DailyCheck record) {
        return 0;
    }

    @Override
    public int insertSelective(DailyCheck record) {
        return 0;
    }

    @Override
    public List<DailyCheck> selectByExample(DailyCheckExample example) {
        List<DailyCheck> dailyChecks=dailyCheckMapper.selectByExample(example);
        return dailyChecks;
    }

    @Override
    public DailyCheck selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(DailyCheck record, DailyCheckExample example) {
        return 0;
    }

    @Override
    public int updateByExample(DailyCheck record, DailyCheckExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(DailyCheck record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(DailyCheck record) {
        return 0;
    }
}
