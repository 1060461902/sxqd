package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.RoadMapper;
import edu.zjgsu.ito.model.Road;
import edu.zjgsu.ito.model.RoadExample;
import edu.zjgsu.ito.service.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoadServiceImpl implements RoadService{
    @Autowired
    RoadMapper roadMapper;
    @Override
    public long countByExample(RoadExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(RoadExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Road record) {
        return 0;
    }

    @Override
    public int insertSelective(Road record) {
        return 0;
    }

    @Override
    public List<Road> selectByExample(RoadExample example) {
        List<Road> roads= roadMapper.selectByExample(example);
        return roads;
    }

    @Override
    public Road selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Road record, RoadExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Road record, RoadExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Road record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Road record) {
        return 0;
    }
}
