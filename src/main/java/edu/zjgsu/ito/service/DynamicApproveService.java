package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.DynamicApprove;
import edu.zjgsu.ito.model.DynamicApproveExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DynamicApproveService {
    long countByExample(DynamicApproveExample example);

    int deleteByExample(DynamicApproveExample example);

    int deleteByPrimaryKey(String id);

    int insert(DynamicApprove record);

    int insertSelective(DynamicApprove record);

    List<DynamicApprove> selectByExample(DynamicApproveExample example);

    DynamicApprove selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DynamicApprove record, @Param("example") DynamicApproveExample example);

    int updateByExample(@Param("record") DynamicApprove record, @Param("example") DynamicApproveExample example);

    int updateByPrimaryKeySelective(DynamicApprove record);

    int updateByPrimaryKey(DynamicApprove record);
}
