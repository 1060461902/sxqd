package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.CollectionRecruitment;
import edu.zjgsu.ito.model.CollectionRecruitmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectionRecruitmentMapper {
    long countByExample(CollectionRecruitmentExample example);

    int deleteByExample(CollectionRecruitmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CollectionRecruitment record);

    int insertSelective(CollectionRecruitment record);

    List<CollectionRecruitment> selectByExample(CollectionRecruitmentExample example);

    CollectionRecruitment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CollectionRecruitment record, @Param("example") CollectionRecruitmentExample example);

    int updateByExample(@Param("record") CollectionRecruitment record, @Param("example") CollectionRecruitmentExample example);

    int updateByPrimaryKeySelective(CollectionRecruitment record);

    int updateByPrimaryKey(CollectionRecruitment record);
}