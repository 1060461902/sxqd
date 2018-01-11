package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.UserMapper;
import edu.zjgsu.ito.model.StudentExample;
import edu.zjgsu.ito.model.User;
import edu.zjgsu.ito.model.UserExample;
import edu.zjgsu.ito.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public long countByExample(UserExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(UserExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(User record) {
        int count = userMapper.insert(record);
        return count;
    }

    @Override
    public int insertSelective(User record) {
        return 0;
    }

    @Override
    public List<User> selectByExample(UserExample example) {
        List<User> userList = userMapper.selectByExample(example);
        return userList;
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        StudentExample studentExample = new StudentExample();
//        StudentExample.GeneratedCriteria criteria = studentExample.or();
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public int updateByExampleSelective(User record, UserExample example) {
        return 0;
    }

    @Override
    public int updateByExample(User record, UserExample example) {
        int status;

        status = userMapper.updateByExample(record, example);
        return status;
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        int status;
        status = userMapper.updateByPrimaryKey(record);
        return status;
    }

}
