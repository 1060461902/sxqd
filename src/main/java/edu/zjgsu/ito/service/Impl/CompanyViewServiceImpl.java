package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.CompanyViewMapper;
import edu.zjgsu.ito.model.CompanyView;
import edu.zjgsu.ito.model.CompanyViewExample;
import edu.zjgsu.ito.service.CompanyViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CompanyViewServiceImpl implements CompanyViewService{
    @Autowired
    CompanyViewMapper companyViewMapper;
    @Override
    public long countByExample(CompanyViewExample example) {

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
    public CompanyView selectByName(String companyName) {
        CompanyView companyView=companyViewMapper.selectByName(companyName);
        return companyView;
    }

    @Override
    public List<CompanyView> selectByExample(CompanyViewExample example) {

        List<CompanyView> companyViewList = companyViewMapper.selectByExample(example);
        return  companyViewList;

     }

    @Override
    public CompanyView selectByKey(Integer id) {
        CompanyView companyView=companyViewMapper.selectByKey(id);
        return companyView;
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
