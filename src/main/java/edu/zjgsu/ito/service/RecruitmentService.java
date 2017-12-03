package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.Recruitment;
import edu.zjgsu.ito.model.RecruitmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitmentService{
    long countByExample(RecruitmentExample example);

    int deleteByExample(RecruitmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Recruitment record);

    int insertSelective(Recruitment record);

    List<Recruitment> selectByExample(RecruitmentExample example);

    Recruitment selectByPrimaryKey(Integer id);

    List<Recruitment> selectByCompanyId(Integer companyId);

    int updateByExampleSelective(@Param("record") Recruitment record, @Param("example") RecruitmentExample example);

    int updateByExample(@Param("record") Recruitment record, @Param("example") RecruitmentExample example);

    int updateByPrimaryKeySelective(Recruitment record);

    int updateByPrimaryKey(Recruitment record);
}
