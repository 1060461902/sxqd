package edu.zjgsu.ito.controller.admin;

import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.utils.Md5Util;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "admin")
public class OperateController {

    @Autowired
    StudentService studentService;
    @Autowired
    UserService userService;
    @Autowired
    CommonService commonService;

    @Autowired
    AdminOperateService adminOperateService;

    /**
     * 上传Excel，批量注册学生和老师
     * @param request
     * @param roleId
     * @return
     * @author sawei
     */
    @RequestMapping(value = "uploadExcel", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody Map<String, Object> uploadExcel(HttpServletRequest request, @RequestParam("roleId") Integer roleId) {
        return adminOperateService.batchRegister(request, roleId);
    }

    /**
     *学生注册
     * @param temp
     * @return
     * @author sawei
     */
    @RequestMapping(value = "studentRegister", method = RequestMethod.POST )
    public @ResponseBody Map<String, Object> studentRegister(@RequestBody StudentRegisterView temp) {
        int status;
        Map<String, Object> result = new HashMap<String, Object>();

        status = adminOperateService.studentRegister(temp.getUserName(), temp.getNickName(), temp.getMajor(), temp.getClss());

        if (status > 0) {
            result.put("code", Constant.OK);
            result.put("msg", "学生注册成功");
        } else {
            result.put("code", Constant.FAIL);
            result.put("msg", "学生注册失败");
        }

        return result;
    }

    /**
     * 老师注册
     * @param temp
     * @return
     * @author sawei
     */
    @RequestMapping(value = "teacherRegister", method = RequestMethod.POST )
    public @ResponseBody Map<String, Object> teacherRegister(@RequestBody TeacherRegisterView temp) {
        int status;
        Map<String, Object> result = new HashMap<String, Object>();

        status = adminOperateService.teacherRegister(temp.getUserName(), temp.getNickName(), temp.getMajor());

        if (status > 0) {
            result.put("code", Constant.OK);
            result.put("msg", "教师注册成功！");
        } else {
            result.put("code", Constant.FAIL);
            result.put("msg", "教师注册失败！");
        }

        return result;
    }

    @RequestMapping(value = "allocateStu2Teacher", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> allocateStu2Teacher(@RequestParam(value = "studentIdList") List<Integer> StudentIdList,
                                                                 @RequestParam(value = "teacherId") Integer teacherId) {
        int status;
        Map<String, Object> result = new HashMap<String, Object>();

        for (Integer stuId:
             StudentIdList) {
            Student student = studentService.selectByPrimaryKey(stuId);
            student.setTeacherId(teacherId);
            status = studentService.updateByPrimaryKey(student);
            if (!(status > 0)) {
                result.put("code", Constant.FAIL);
                result.put("msg", "分配学生失败！");
                return result;
            }
        }
        result.put("code", Constant.OK);
        result.put("msg", "分配学生成功！");
        return result;
    }


    @RequestMapping(value = "forbidAccount", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> forbidAccount(@RequestParam("roleId") Integer roleId,
                                                           @RequestParam("id") Integer id) {
        int status;
        Integer userId;
        Map<String, Object> result = new HashMap<String, Object>();

//        根据角色的主键id查询userID
        userId = commonService.role2user(roleId, id);

        User user = userService.selectByPrimaryKey(userId);
        user.setForbidden(true);
        status = userService.updateByPrimaryKey(user);

        if (status > 0) {
            result.put("code", Constant.OK);
            result.put("msg", "禁用成功！");
        } else {
            result.put("code", Constant.FAIL);
            result.put("msg", "禁用失败！");
        }

        return result;
    }


    /**
     * 管理员重新设置密码
     * @param roleId
     * @param id
     * @return
     * @author sawei
     */
    @RequestMapping(value = "resetPwd", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> resetPwd(@RequestParam("roleId") Integer roleId,
                                                      @RequestParam("id") Integer id) {
        int status;
        Integer userId;
        String md5Password;
        Map<String, Object> result = new HashMap<String, Object>();

        md5Password = Md5Util.getMD5(Constant.DEFAULTPWD);

        userId = commonService.role2user(roleId, id);

//                修改密码
        User user = userService.selectByPrimaryKey(userId);
        user.setPassword(md5Password);
        status = userService.updateByPrimaryKey(user);

        if (status > 0) {
            result.put("code", Constant.OK);
            result.put("msg", "修改密码成功！");
        } else {
            result.put("code", Constant.FAIL);
            result.put("msg", "修改密码失败！");
        }

        return result;
    }
}
