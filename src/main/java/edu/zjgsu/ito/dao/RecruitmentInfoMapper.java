package edu.zjgsu.ito.dao;

import edu.zjgsu.ito.model.RecruitmentInfo;
import edu.zjgsu.ito.model.RecruitmentInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentInfoMapper {
    long countByExample(RecruitmentInfoExample example);

    int deleteByExample(RecruitmentInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(RecruitmentInfo record);

    int insertSelective(RecruitmentInfo record);

    List<RecruitmentInfo> selectByExample(RecruitmentInfoExample example);

    RecruitmentInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RecruitmentInfo record, @Param("example") RecruitmentInfoExample example);

    int updateByExample(@Param("record") RecruitmentInfo record, @Param("example") RecruitmentInfoExample example);

    int updateByPrimaryKeySelective(RecruitmentInfo record);

    int updateByPrimaryKey(RecruitmentInfo record);
}