package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.CompanyImageMapper;
import edu.zjgsu.ito.model.CompanyImage;
import edu.zjgsu.ito.model.CompanyImageExample;
import edu.zjgsu.ito.service.CompanyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyImageServiceImpl implements CompanyImageService{
    @Autowired
    CompanyImageMapper companyImageMapper;
    @Override
    public long countByExample(CompanyImageExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(CompanyImageExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(CompanyImage record) {
        return 0;
    }

    @Override
    public int insertSelective(CompanyImage record) {
        return 0;
    }

    @Override
    public List<CompanyImage> selectByExample(CompanyImageExample example) {
        List<CompanyImage> companyImageList=companyImageMapper.selectByExample(example);
        return companyImageList;
    }

    @Override
    public CompanyImage selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(CompanyImage record, CompanyImageExample example) {
        return 0;
    }

    @Override
    public int updateByExample(CompanyImage record, CompanyImageExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(CompanyImage record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(CompanyImage record) {
        return 0;
    }
}

