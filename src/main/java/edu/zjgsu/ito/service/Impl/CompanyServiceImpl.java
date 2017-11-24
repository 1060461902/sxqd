package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.CompanyMapper;
import edu.zjgsu.ito.model.CompanyExample;
import edu.zjgsu.ito.model.Company;
import edu.zjgsu.ito.model.CompanyExample;
import edu.zjgsu.ito.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyMapper CompanyMapper;

    @Override
    public long countByExample(CompanyExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(CompanyExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(Company record) {
        return 0;
    }

    @Override
    public int insertSelective(Company record) {
        return 0;
    }

    @Override
    public List<Company> selectByExample(CompanyExample example) {
        List<Company> companyList=CompanyMapper.selectByExample(example);
        for(Company company:companyList){


        }
        return companyList;
    }

    @Override
    public Company selectByPrimaryKey(String id) {
        Company Company = CompanyMapper.selectByPrimaryKey(id);
        return Company;
    }

    @Override
    public int updateByExampleSelective(Company record, CompanyExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Company record, CompanyExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Company record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Company record) {
        int status;

        status = CompanyMapper.updateByPrimaryKey(record);

        return status;
    }
}
