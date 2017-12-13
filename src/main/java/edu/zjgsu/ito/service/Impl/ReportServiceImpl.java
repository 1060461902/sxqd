package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.ReportMapper;
import edu.zjgsu.ito.model.Report;
import edu.zjgsu.ito.model.ReportExample;
import edu.zjgsu.ito.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportMapper reportMapper;
    @Override
    public long countByExample(ReportExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(ReportExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Report record) {
        return 0;
    }

    @Override
    public int insertSelective(Report record) {
        return 0;
    }

    @Override
    public List<Report> selectByExample(ReportExample example) {
        List<Report> reports=reportMapper.selectByExample(example);
        return reports;
    }

    @Override
    public Report selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Report record, ReportExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Report record, ReportExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Report record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Report record) {
        return 0;
    }
}
