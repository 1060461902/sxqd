package edu.zjgsu.ito.service.Impl;


import edu.zjgsu.ito.model.CompanyView;
import edu.zjgsu.ito.model.CompanyViewExample;
import edu.zjgsu.ito.service.CompanyViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyViewServiceImpl implements CompanyViewService{
    @Override
    public long countByExample(CompanyViewExample example) {
        ;
        return 0;
    }

    @Override
    public int deleteByExample(CompanyViewExample example) {
        return 0;
    }

    @Override
    public int insert(CompanyView record) {
        return 0;
    }

    @Override
    public int insertSelective(CompanyView record) {
        return 0;
    }

    @Override
    public List<CompanyView> selectByExample(CompanyViewExample example) {
        return null;
    }

    @Override
    public int updateByExampleSelective(CompanyView record, CompanyViewExample example) {
        return 0;
    }

    @Override
    public int updateByExample(CompanyView record, CompanyViewExample example) {
        return 0;
    }
}
