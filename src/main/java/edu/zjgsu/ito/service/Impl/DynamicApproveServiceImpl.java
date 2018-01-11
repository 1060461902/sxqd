package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.DynamicApproveMapper;
import edu.zjgsu.ito.model.DynamicApprove;
import edu.zjgsu.ito.model.DynamicApproveExample;
import edu.zjgsu.ito.service.DynamicApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DynamicApproveServiceImpl implements DynamicApproveService{
    @Autowired
    DynamicApproveMapper dynamicApproveMapper;
    @Override
    public long countByExample(DynamicApproveExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(DynamicApproveExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(DynamicApprove record) {
        return 0;
    }

    @Override
    public int insertSelective(DynamicApprove record) {
        return 0;
    }

    @Override
    public List<DynamicApprove> selectByExample(DynamicApproveExample example) {
        List<DynamicApprove> dynamicApproveList= dynamicApproveMapper.selectByExample(example);
        return dynamicApproveList;
    }

    @Override
    public DynamicApprove selectByPrimaryKey(Integer id) {
            DynamicApprove dynamicApprove=dynamicApproveMapper.selectByPrimaryKey(id);
            return dynamicApprove;
    }

    @Override
    public int updateByExampleSelective(DynamicApprove record, DynamicApproveExample example) {
        return 0;
    }

    @Override
    public int updateByExample(DynamicApprove record, DynamicApproveExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(DynamicApprove record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(DynamicApprove record) {
        int status;

        status = dynamicApproveMapper.updateByPrimaryKey(record);

        return status;
    }
}
