package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.*;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.service.SimpleService;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.utils.Md5Util;
import edu.zjgsu.ito.vo.ResetPwdUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class SimpleServiceImpl implements SimpleService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    WeightMapper weightMapper;

    /**
     * 重置密码
     * @param roleId
     * @param id
     * @return
     * @author sawei
     */
    @Override
    public Message resetPwd(Integer roleId, Integer id) {
        Integer userId;
        String md5Password;

        md5Password = Constant.DEFAULTPWD;

        userId = role2user(roleId, id);
//                修改密码
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(md5Password);
        userMapper.updateByPrimaryKey(user);

        return Message.createSuc(null);
    }

    /**
     *更新权重
     * @param weight
     * @return
     * @author sawei
     */
    @Override
    public Message setWeight(Weight weight) {
        WeightExample weightExample = new WeightExample();
        weightExample.or().andIdEqualTo(1);
        weightMapper.updateByExample(weight, weightExample);

        return Message.createSuc(null);
    }


    /**
     * 通过不同角色的主键id查到其user的userID
     * @param roleId
     * @param id
     * @return
     * @author sawei
     */
    public Integer role2user(Integer roleId, Integer id) {
        List<Integer> userId = new ArrayList<Integer>();

        switch (roleId) {
            case 2:
                Student student = studentMapper.selectByPrimaryKey(id);
                userId.add(student.getUserId());
                break;
            case 3:
                Teacher teacher = teacherMapper.selectByPrimaryKey(id);
                userId.add(teacher.getUserId());
                break;
            case 4:
                Company company = companyMapper.selectByPrimaryKey(id);
                userId.add(company.getUserId());
                break;
            default:
                break;
        }
        return userId.get(0);
    }

    /**
     * 学生注册
     *
     * @param userName
     * @param nickName
     * @param major
     * @param clss
     * @return
     * @author sawei
     */
    @Override
    public Message studentRegister(String userName, String nickName, String major, String clss, String grade) {
        User user = new User();
        Student student = new Student();

        user.setUserName(userName);
        user.setNickName(nickName);
        user.setPassword(Constant.DEFAULTPWD);
        user.setRoleId(Constant.STUDENT);
        user.setDeleteTag(true);
        user.setCreateTime(new Date());

        student.setUserId(userRegister(user));
        student.setMajor(major);
        student.setClss(clss);
        student.setStatus("未实习");
        student.setDeleteTag(true);
        student.setForbidden(false);
        student.setGrade(grade);
        studentMapper.insert(student);

        return Message.createSuc(null);
    }

    /**
     * 老师注册
     *
     * @param userName
     * @param nickName
     * @param major
     * @return
     * @author sawei
     */
    @Override
    public Message teacherRegister(String userName, String nickName, String major) {
        User user = new User();
        Teacher teacher = new Teacher();

        user.setUserName(userName);
        user.setNickName(nickName);
        user.setPassword(Constant.DEFAULTPWD);
        user.setRoleId(Constant.TEACHER);
        user.setDeleteTag(true);
        user.setCreateTime(new Date());

        teacher.setUserId(userRegister(user));
        teacher.setMajor(major);
        teacher.setDeleteTag(true);
        teacher.setForbidden(false);
        teacherMapper.insert(teacher);

        return Message.createSuc(null);
    }

    /**
     * 添加一条user表记录，并返回最新记录的id
     * @param user
     * @return
     * @author sawei
     */
    public Integer userRegister(User user) {
        int userId;
        UserExample userExample = new UserExample();
        userMapper.insert(user);
        //                找到刚刚插入的那条记录
        userExample.setOrderByClause("id DESC");
        userExample.setLimit(1);

        userId = userMapper.selectByExample(userExample).get(0).getId();

        return userId;
    }


    /**
     * 企业注册
     * @param user
     * @return
     * @sawei
     */
    @Override
    public Message register(User user) {
        String md5Password;

        md5Password = Md5Util.getMD5(user.getPassword());
        user.setPassword(md5Password);
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(user.getUserName());
        userMapper.updateByExample(user, userExample);

        return Message.createSuc(null);
    }

    /**
     * 登录
     * @param request
     * @param user
     * @return
     * @author sawei
     */
    @Override
    public Message login(HttpServletRequest request, User user) {
//        变量声明
        String md5Password;
        HttpSession session = request.getSession();

//        建立example
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(user.getUserName()).andPasswordEqualTo(Md5Util.getMD5(user.getPassword()));
//        根据example查找
        List<User> userList = userMapper.selectByExample(userExample);
//        判断有无这个user
        if (userList.size() > 0) {
//            放到session里
            session.setAttribute("loginFlag", "1");
            return Message.createSuc(null);
        } else {
            return Message.createErr(null);
        }
    }

    /**
     *修改密码
     * @param frontUser
     * @return
     * @author sawei
     */
    @Override
    public Message modifyPwd(ResetPwdUser frontUser) {
        String md5NewPassword;
        String md5OldPassword;

//        先验证账号密码是否正确
        md5OldPassword = Md5Util.getMD5(frontUser.getOldPassword());
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(frontUser.getUserName()).andPasswordEqualTo(md5OldPassword);

        List<User> userList = userMapper.selectByExample(userExample);
        User backUser = userList.get(0);

//        修改密码
        md5NewPassword = Md5Util.getMD5(frontUser.getNewPassword());
        backUser.setPassword(md5NewPassword);

//        修改database中记录

         userMapper.updateByExample(backUser, userExample);

        return Message.createSuc(null);
    }


}
