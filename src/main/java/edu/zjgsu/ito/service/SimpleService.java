package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.User;
import edu.zjgsu.ito.model.Weight;
import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.vo.ResetPwdUser;

import javax.servlet.http.HttpServletRequest;

public interface SimpleService {

    Message teacherRegister(String userName, String nickName, String major);

    Message studentRegister(String userName, String nickName, String major, String clss, String grade);

    Message setWeight(Weight weight);

    Message resetPwd(Integer roleId, Integer id);

    Message register(User user);

    Message login(HttpServletRequest request, User user);

    Message modifyPwd(ResetPwdUser frontUser);
}
