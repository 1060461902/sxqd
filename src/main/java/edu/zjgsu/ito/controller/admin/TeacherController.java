package edu.zjgsu.ito.controller.admin;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
@RequestMapping(value ="admin")
public class TeacherController {
    @Autowired
    SimpleService simpleService;

    /*
     * 查看老师列表
     * */
    @GetMapping(value = "showTeachers")
    public Message showTeachers(@RequestParam("major") String major) {
        return simpleService.showTeachers(major);
    }

    /*查看老师名下学生名字
     * @author hanfeng*/
    @GetMapping(value = "teacherStudentName")
    public Message teacherStudentName(@RequestParam("id") Integer id) {
        return simpleService.teacherStudentName(id);
    }

    /*查看老师名下学生
     * @author hanfeng*/
    @PostMapping(value = "showTeacherStudent")
    public Message showTeacherStudent(@RequestBody MVo mVo) {
        return simpleService.showTeacherStudent(mVo);
    }

    /*查看老师名下学生的筛选条件
     * @author hanfeng
     * */
    @PostMapping(value = "showTeacherStudentScreen")
    public Message showTeacherStudentScreen(@RequestBody MVo mVo) {
        return simpleService.showTeacherStudentScreen(mVo);
    }

    /*查看老师详情
     * @author hanfeng*/
    @GetMapping(value = "teacherDetail")
    public Message teacherDetail(@RequestParam("id") Integer id) {
        return simpleService.teacherDetail(id);
    }

    /*
     * author hanfeng
     * 禁用老师
     * */
    @GetMapping(value = "forbiddenTeacher")
    public Message forbiddenTeacher(@RequestParam("id") Integer id, @RequestParam("forbidden") boolean forbidden) {
        return simpleService.forbiddenTeacher(id, forbidden);
    }

    /*
     * author hanfeng
     * 删除
     * */
    @PostMapping(value = "deleteTeacher")
    public Message deleteTeacher(@RequestBody IdVo idVo) {
        return simpleService.deleteTeacher(idVo);
    }

    /*
     * 获取老师专业列表
     * @author hanfeng
     * */
    @GetMapping(value = "teacherMajor")
    public Message teacherMajor() {
        return simpleService.teacherMajor();
    }

    /*
     * author hanfeng
     * 管理员修改老师
     * */
    @PostMapping(value = "updateTeacher")
    public Message updateTeacher(@RequestBody TeacherVo teacherVo) {
        return simpleService.updateTeacher(teacherVo);
    }


}