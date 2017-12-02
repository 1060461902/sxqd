package edu.zjgsu.ito.controller;

import edu.zjgsu.ito.model.User;
import edu.zjgsu.ito.model.UserExample;
import edu.zjgsu.ito.service.UserService;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.utils.Md5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//登录注册登出
@Controller
//@RequestMapping(value = "admin")
public class UserController {

    @Resource
    private UserService userService;


/*    *//**
     *
     * @return code
     *//*
    @RequestMapping(value = "admin/register",method = RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> register(@RequestBody User user) {
        int status;
        String md5Password;
//        String password = "admin";
        Map<String, Object> result = new HashMap<String,Object>();

        md5Password = Md5Util.getMD5(user.getPassword());
        user.setPassword(md5Password);

        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(user.getUsername());
        status = userService.updateByExample(user, userExample);

        if (status > 0) {
            result.put("code", Constant.OK);
        } else {
            result.put("code", Constant.FAIL);
        }

        return result;
    }*/


    /**
     *
     * @param request
     * @param user 保存前端传过来的账号和密码
     * @return status code
     * @author sawei
     */
    @RequestMapping(value = "admin/login", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> login(HttpServletRequest request, @RequestBody User user) {
//        变量声明
        String md5Password;
        Map<String, Object> result = new HashMap<String, Object>();//返回的信息保存在result里
        HttpSession session = request.getSession();

//        建立example
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(user.getUserName())
                .andPasswordEqualTo(Md5Util.getMD5(user.getPassword()));
//        根据example查找
        List<User> userList = userService.selectByExample(userExample);
//        判断有无这个user
        if (userList.size() > 0) {
//            登录成功
            result.put("code", Constant.OK);
//            放到session里
            session.setAttribute("loginFlag", "1");
//            String tmp = (String) session.getAttribute("loginFlag");
//            System.out.println(tmp);
        } else {
            result.put("code", Constant.FAIL);
        }

        return result;
    }//login
    //    @RequestMapping(value = "/logout", method = RequestMethod.POST)
//    public @ResponseBody
//    Map<String, Object> logout(HttpServletRequest request) {
//        Map<String, Object> result = new HashMap<String, Object>();//返回的信息保存在result里
//        HttpSession session = request.getSession();
//        String tmp = (String) session.getAttribute("loginFlag");
//        System.out.println(tmp);
//        return result;
//    }

    /**
     *
     * @param request
     * @return status code
     * @author sawei
     */
    @RequestMapping(value = "admin/logout", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();//返回的信息保存在result里
        request.getSession().invalidate();
        result.put("code", Constant.OK);
        return result;
    }

    @RequestMapping(value = "admin/modifyPwd", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> modifyPwd(@RequestBody User frontUser) {
        int status;
        String md5Password;
        Map<String, Object> result = new HashMap<String, Object>();//返回的信息保存在result里

//        得到admin账号
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo("admin");
        List<User> userList = userService.selectByExample(userExample);
        User backUser = userList.get(0);

//        修改密码
        md5Password = Md5Util.getMD5(frontUser.getPassword());
        backUser.setPassword(md5Password);


//        修改database中记录

        status = userService.updateByExample(backUser, userExample);

        if (status > 0) {
            result.put("code", Constant.OK);
        } else {
            result.put("code", Constant.FAIL);
        }

        return result;
    }
}
