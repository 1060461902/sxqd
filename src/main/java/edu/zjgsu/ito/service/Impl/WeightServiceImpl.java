package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.WeightMapper;
import edu.zjgsu.ito.model.Weight;
import edu.zjgsu.ito.model.WeightExample;
import edu.zjgsu.ito.service.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WeightServiceImpl implements WeightService{
    @Autowired
    WeightMapper weightMapper;
    @Override
    public long countByExample(WeightExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(WeightExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Weight record) {
        return 0;
    }

    @Override
    public int insertSelective(Weight record) {
        return 0;
    }

    @Override
    public List<Weight> selectByExample(WeightExample example) {
        return null;
    }

    @Override
    public Weight selectByPrimaryKey(Integer id) {
        Weight weight=weightMapper.selectByPrimaryKey(id);
        return weight;
    }

    @Override
    public int updateByExampleSelective(Weight record, WeightExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Weight record, WeightExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Weight record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Weight record) {
        return 0;
    }
}
