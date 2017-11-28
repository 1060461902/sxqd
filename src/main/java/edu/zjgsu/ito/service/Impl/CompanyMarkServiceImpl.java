package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.model.CompanyMark;
import edu.zjgsu.ito.model.CompanyMarkExample;
import edu.zjgsu.ito.service.CompanyMarkService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyMarkServiceImpl implements CompanyMarkService {
    @Override
    public long countByExample(CompanyMarkExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(CompanyMarkExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(CompanyMark record) {
        return 0;
    }

    @Override
    public int insertSelective(CompanyMark record) {
        return 0;
    }

    @Override
    public List<CompanyMark> selectByExample(CompanyMarkExample example) {
        return null;
    }

    @Override
    public CompanyMark selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(CompanyMark record, CompanyMarkExample example) {
        return 0;
    }

    @Override
    public int updateByExample(CompanyMark record, CompanyMarkExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(CompanyMark record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(CompanyMark record) {
        return 0;
    }
}
