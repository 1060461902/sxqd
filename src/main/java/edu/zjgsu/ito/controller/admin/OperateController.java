package edu.zjgsu.ito.controller.admin;

import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.CompanyService;
import edu.zjgsu.ito.service.StudentService;
import edu.zjgsu.ito.service.TeacherService;
import edu.zjgsu.ito.service.UserService;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "admin")
public class OperateController {
    public static final String defaultPwd = "123456";

//    private String userId;

//    @Autowired
//
    @Autowired
    CompanyService companyService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    UserService userService;

    //通过不同角色的主键id查到其user的userID
    public String role2user(String roleid, String id) {
        List<String> userId = new ArrayList<String>();

        switch (roleid) {
            case "2":
                Student student = studentService.selectByPrimaryKey(id);
                userId.add(student.getUserId());
                break;
            case "3":
                Teacher teacher = teacherService.selectByPrimaryKey(id);

                userId.add(teacher.getUserId());
                break;
            case "4":
                Company company = companyService.selectByPrimaryKey(id);
                userId.add(company.getUserId());
                break;
            default:
//                System.out.println(""\);
                break;
        }
        return userId.get(0);
    }
/*    @RequestMapping(value="/list", method=RequestMethod.POST)
    @ResponseBody
    public String requestList(@RequestParam("listParam[]") List<String> param) {
        return "Request successful. Post param : List<String> - " + param.toString();
}*/



    @RequestMapping(value = "allocateStu2Teacher", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> allocateStu2Teacher(@RequestParam(value = "studentIdList") List<String> StudentIdList,
                                                                 @RequestParam(value = "teacherId") String teacherId) {
        int status;
        Map<String, Object> result = new HashMap<String, Object>();

        for (String stuId:
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
    public @ResponseBody Map<String, Object> forbidAccount(@RequestParam("roleId") String roleId,
                                                           @RequestParam("id") String id) {
        int status;
        String userId;
        Map<String, Object> result = new HashMap<String, Object>();

//        根据角色的主键id查询userID
        userId = role2user(roleId, id);

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


    @RequestMapping(value = "resetPwd", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> resetPwd(@RequestParam("roleId") String roleId,
                                                      @RequestParam("id") String id) {
        int status;
        String userId;
        String md5Password;
        Map<String, Object> result = new HashMap<String, Object>();

        md5Password = Md5Util.getMD5(defaultPwd);

        userId = role2user(roleId, id);

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
    /**
     *
     * @param id 需要被审批的企业的id，即company的主键
     * @return code
     * @author saweis
     */
    @RequestMapping(value = "comfirmRegister", method = RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> comfirmRegister(@RequestParam("id") String id, @RequestParam("passFlag") String passFlag) {
        int status;
        Map<String, Object> result = new HashMap<String, Object>();

        Company companyBack = companyService.selectByPrimaryKey(id);
        if (companyBack == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "未查到id=" + id + "的记录！");
            return result;
        }
//        已经被check过
        companyBack.setChecked(true);

        if (passFlag.equals("0")) {
//            审批不通过
            companyBack.setPass(false);
        } else {
//            审批通过
            companyBack.setPass(true);
        }

//        更新数据库记录
        status = companyService.updateByPrimaryKey(companyBack);

        if (status > 0) {
            result.put("code", Constant.OK);
            result.put("msg", "审批成功！");
        } else {
            result.put("code", Constant.FAIL);
            result.put("msg", "审批失败！更新数据库失败");
        }

        return result;
    }
}
