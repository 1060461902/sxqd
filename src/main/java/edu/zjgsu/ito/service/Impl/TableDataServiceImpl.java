package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.TableDataMapper;
import edu.zjgsu.ito.model.TableData;
import edu.zjgsu.ito.model.TableDataExample;
import edu.zjgsu.ito.service.TableDataService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 宿舍 on 2017/12/14.
 */
@Service
public class TableDataServiceImpl implements TableDataService {
    @Autowired
    TableDataMapper tableDataMapper;
    @Override
    public long countByExample(TableDataExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(TableDataExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(TableData record) {
        return 0;
    }

    @Override
    public int insertSelective(TableData record) {
        return 0;
    }

    @Override
    public List<TableData> selectByExample(TableDataExample example) {
        List<TableData> tableDatas=tableDataMapper.selectByExample(example);
        return tableDatas;
    }

    @Override
    public TableData selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(@Param("record") TableData record, @Param("example") TableDataExample example) {
        return 0;
    }

    @Override
    public int updateByExample(@Param("record") TableData record, @Param("example") TableDataExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(TableData record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TableData record) {
        return 0;
    }
}
