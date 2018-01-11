package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.ScoreMapper;
import edu.zjgsu.ito.model.Score;
import edu.zjgsu.ito.model.ScoreExample;
import edu.zjgsu.ito.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScoreServiceImpl implements ScoreService{
    @Autowired
    ScoreMapper scoreMapper;
    @Override
    public long countByExample(ScoreExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(ScoreExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Score record) {
        return 0;
    }

    @Override
    public int insertSelective(Score record) {
        return 0;
    }

    @Override
    public List<Score> selectByExample(ScoreExample example) {
        List<Score> scores=scoreMapper.selectByExample(example);
        return  scores;
    }

    @Override
    public Score selectByStudent(Integer studentId) {
        Score score=scoreMapper.selectByStudent(studentId);
        return score;
    }

    @Override
    public Score selectByPrimaryKey(Integer id) {
        Score score=scoreMapper.selectByPrimaryKey(id);
        return null;
    }

    @Override
    public int updateByExampleSelective(Score record, ScoreExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Score record, ScoreExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Score record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Score record) {
        return 0;
    }
}
