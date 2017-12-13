package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.ImageMapper;
import edu.zjgsu.ito.model.Image;
import edu.zjgsu.ito.model.ImageExample;
import edu.zjgsu.ito.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageMapper imageMapper;
    @Override
    public long countByExample(ImageExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(ImageExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Image record) {
        return 0;
    }

    @Override
    public int insertSelective(Image record) {
        return 0;
    }

    @Override
    public List<Image> selectByExample(ImageExample example) {
        List<Image> images=imageMapper.selectByExample(example);
        return images;
    }

    @Override
    public Image selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Image record, ImageExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Image record, ImageExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Image record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Image record) {
        return 0;
    }
}
