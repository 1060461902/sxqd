package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.CompanyInfoMapper;
import edu.zjgsu.ito.model.CompanyInfo;
import edu.zjgsu.ito.model.CompanyInfoExample;
import edu.zjgsu.ito.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {
    @Autowired
    CompanyInfoMapper companyInfoMapper;

    @Override
    public long countByExample(CompanyInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(CompanyInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(CompanyInfo record) {
        return 0;
    }

    @Override
    public int insertSelective(CompanyInfo record) {
        return 0;
    }

    @Override
    public List<CompanyInfo> selectByExample(CompanyInfoExample example) {
        return null;
    }

    @Override
    public CompanyInfo selectByPrimaryKey(String id) {
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(id);
        return companyInfo;
    }

    @Override
    public int updateByExampleSelective(CompanyInfo record, CompanyInfoExample example) {
        return 0;
    }

    @Override
    public int updateByExample(CompanyInfo record, CompanyInfoExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(CompanyInfo record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(CompanyInfo record) {
        int status;

        status = companyInfoMapper.updateByPrimaryKey(record);

        return status;
    }
}
