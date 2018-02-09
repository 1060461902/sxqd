package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.Image;
import edu.zjgsu.ito.model.ImageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageService {

    long countByExample(ImageExample example);

    int deleteByExample(ImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Image record);

    int insertSelective(Image record);

    List<Image> selectByExample(ImageExample example);

    Image selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Image record, @Param("example") ImageExample example);

    int updateByExample(@Param("record") Image record, @Param("example") ImageExample example);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
}
