package edu.zjgsu.ito.controller;

import edu.zjgsu.ito.model.User;
import edu.zjgsu.ito.model.UserExample;
import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.service.SimpleService;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.utils.Md5Util;
import edu.zjgsu.ito.vo.ResetPwdUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "admin")
public class UserController {
    @Autowired
    SimpleService simpleService;

    /**
     * 公司注册
     * @param user
     * @return
     * @author sawei
     */
    @GetMapping(value = "register")
    public Message register(@RequestBody User user) {
        return simpleService.register(user);
    }

    /**
     *登录
     * @param request
     * @param user
     * @return
     * @author sawei
     */
    @PostMapping(value = "login")
    public Message login(HttpServletRequest request, @RequestBody User user) {
        return simpleService.login(request, user);
    }


    /**
     *登出
     * @param request
     * @return
     * @author sawei
     */
    @PostMapping(value = "logout")
    public Message logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return Message.createSuc(null);
    }

    /**
     *修改密码
     * @param frontUser
     * @return
     * @author sawei
     */
    @PostMapping(value = "modifyPwd")
    public Message modifyPwd(@RequestBody ResetPwdUser frontUser) {
        return simpleService.modifyPwd(frontUser);
    }

    /**
     * 管理员重新设置密码
     * @param roleId
     * @param id
     * @return
     * @author sawei
     */
    @GetMapping(value = "resetPwd")
    public Message resetPwd(@RequestParam("roleId") Integer roleId, @RequestParam("id") Integer id) {
        return simpleService.resetPwd(roleId, id);
    }

}
