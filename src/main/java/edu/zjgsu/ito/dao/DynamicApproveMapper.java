package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.DynamicApprove;
import edu.zjgsu.ito.model.DynamicApproveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DynamicApproveMapper {
    long countByExample(DynamicApproveExample example);

    int deleteByExample(DynamicApproveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DynamicApprove record);

    int insertSelective(DynamicApprove record);

    List<DynamicApprove> selectByExample(DynamicApproveExample example);

    DynamicApprove selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DynamicApprove record, @Param("example") DynamicApproveExample example);

    int updateByExample(@Param("record") DynamicApprove record, @Param("example") DynamicApproveExample example);

    int updateByPrimaryKeySelective(DynamicApprove record);

    int updateByPrimaryKey(DynamicApprove record);
}