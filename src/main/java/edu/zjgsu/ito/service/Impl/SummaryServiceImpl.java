package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.SummaryMapper;
import edu.zjgsu.ito.model.Summary;
import edu.zjgsu.ito.model.SummaryExample;
import edu.zjgsu.ito.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SummaryServiceImpl implements SummaryService {
    @Autowired
    SummaryMapper summaryMapper;
    @Override
    public long countByExample(SummaryExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(SummaryExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Summary record) {
        return 0;
    }

    @Override
    public int insertSelective(Summary record) {
        return 0;
    }

    @Override
    public List<Summary> selectByExample(SummaryExample example) {
            List<Summary>  summaries=summaryMapper.selectByExample(example);
            return summaries;
    }

    @Override
    public Summary selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Summary record, SummaryExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Summary record, SummaryExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Summary record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Summary record) {
        return 0;
    }
}
