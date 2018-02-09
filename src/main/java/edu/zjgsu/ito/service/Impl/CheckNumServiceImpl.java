package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.CheckNumMapper;
import edu.zjgsu.ito.model.CheckNum;
import edu.zjgsu.ito.model.CheckNumExample;
import edu.zjgsu.ito.service.CheckNumService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 宿舍 on 2017/12/14.
 */
@Service
public class CheckNumServiceImpl implements CheckNumService {
    @Autowired
    CheckNumMapper checkNumMapper
            ;
    @Override
    public long countByExample(CheckNumExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(CheckNumExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(CheckNum record) {
        return 0;
    }

    @Override
    public int insertSelective(CheckNum record) {
        return 0;
    }

    @Override
    public List<CheckNum> selectByExample(CheckNumExample example) {
        List<CheckNum> checkNumList=checkNumMapper.selectByExample(example);
        return checkNumList;
    }

    @Override
    public CheckNum selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(@Param("record") CheckNum record, @Param("example") CheckNumExample example) {
        return 0;
    }

    @Override
    public int updateByExample(@Param("record") CheckNum record, @Param("example") CheckNumExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(CheckNum record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(CheckNum record) {
        return 0;
    }
}
