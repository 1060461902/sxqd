package edu.zjgsu.ito.controller.admin;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.CompanyService;
import edu.zjgsu.ito.service.StudentService;
import edu.zjgsu.ito.service.TeacherService;
import edu.zjgsu.ito.service.UserService;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.FrontTeacher;
import edu.zjgsu.ito.vo.TeacherDetail;
import edu.zjgsu.ito.vo.TeacherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping(value ="admin")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    CompanyService companyService;


    @RequestMapping(value = "showTeachers", method = RequestMethod.GET)
    /*
    * 查看老师列表
    * */
    public @ResponseBody
    Map<String, Object> showTeachers(@RequestParam("major") String major) {

        try {
            major= new String(major .getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FrontTeacher FrontTeacherTemp = null;
        List<FrontTeacher> teacherList = new ArrayList<FrontTeacher>();
        Map<String, Object> result = new HashMap<String, Object>();

        TeacherExample TeacherExample = new TeacherExample();
        if(major.equals("专业")){
            TeacherExample.or().andIdIsNotNull();

        }else {
            TeacherExample.or().andIdIsNotNull().andMajorEqualTo(major);
        }

        List<Teacher> teachers=teacherService.selectByExample(TeacherExample);
        if (teachers == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Teacher表里查到记录！");
            return result;
        }
        for (Teacher teacher:
                teachers) {
            FrontTeacherTemp = new FrontTeacher();

            User user = userService.selectByPrimaryKey(teacher.getUserId());
            if (user == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "无法找到Teacher表userID=" + teacher.getUserId() + "对应的user！");
                return result;
            }
            StudentExample studentExample=new StudentExample();
            studentExample.or().andTeacherIdEqualTo(teacher.getId());
            long count=studentService.countByExample(studentExample);
            FrontTeacherTemp.setCount(count);
            FrontTeacherTemp.setId(teacher.getUserId());
            FrontTeacherTemp.setNickName(user.getNickName());
            FrontTeacherTemp.setUserName(user.getUserName());
            FrontTeacherTemp.setMajor(teacher.getMajor());
            FrontTeacherTemp.setPhone(user.getPhone());
            FrontTeacherTemp.setForbidden(teacher.getForbidden());

            teacherList.add(FrontTeacherTemp);
        }
        result.put("code", Constant.OK);
        result.put("msg", "返回老师信息成功！");
        result.put("teacherList", teacherList);
        return result;
    }
    @RequestMapping(value = "teacherStudentName", method = RequestMethod.GET)
    public @ResponseBody
    /*查看老师名下学生名字
    * @author hanfeng*/
    Map<String, Object> teacherStudentName(@RequestParam("id") Integer id
                                           ) {
        Map<String, Object> result = new HashMap<String, Object>();

        StudentExample studentExample=new StudentExample();
        studentExample.or().andTeacherIdEqualTo(id);
        List<Student> students=studentService.selectByExample(studentExample);
        JSONArray objects=new JSONArray();
        System.out.println(students);
        for(Student student:students){
            JSONObject obj=new JSONObject();
            obj.put("id",student.getId());
            User user=userService.selectByPrimaryKey(student.getUserId());
            if (user == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "无法找到Teacher表userID=" + id + "对应的user！");
                return result;
            }
            obj.put("name",user.getNickName());
            objects.add(obj);
        }
        result.put("Names",objects);
        return result;
    }
    @RequestMapping(value = "showTeacherStudent", method = RequestMethod.GET)
    public @ResponseBody
    /*查看老师名下学生
    * @author hanfeng*/
    Map<String, Object> showTeacherStudent(@RequestParam("id") Integer id
    ) {
        Map<String, Object> result = new HashMap<String, Object>();

        StudentExample studentExample=new StudentExample();
        studentExample.or().andTeacherIdEqualTo(id);
        List<Student> students=studentService.selectByExample(studentExample);
        JSONArray objects=new JSONArray();
        System.out.println(students);
        for(Student student:students){
            JSONObject obj=new JSONObject();
            obj.put("id",student.getId());
            User user=userService.selectByPrimaryKey(student.getUserId());
            obj.put("nickName",user.getNickName());
            obj.put("userName",user.getUserName());
            obj.put("status",student.getStatus());
            Company company=companyService.selectByPrimaryKey(student.getCompanyId());
            obj.put("companyName",company.getCompanyName());
            Teacher teacher=teacherService.selectByPrimaryKey(student.getTeacherId());
            obj.put("major",student.getMajor());
            obj.put("clss",student.getClss());
            obj.put("companyId",student.getCompanyId());

            if (user == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "无法找到Teacher表userID=" + id + "对应的user！");
                return result;
            }
            obj.put("name",user.getNickName());
            objects.add(obj);
        }
        result.put("recruitmentList",objects);
        return result;
    }

    @RequestMapping(value = "teacherDetail", method = RequestMethod.GET)
    public @ResponseBody
    /*查看老师详情
    * @author hanfeng*/
    Map<String, Object> teacherDetail(@RequestParam("id") Integer id) {
        TeacherDetail teacherDetail = null;
        Map<String, Object> result = new HashMap<String, Object>();
        TeacherExample TeacherExample = new TeacherExample();
        TeacherExample.or().andIdEqualTo(id);
        Teacher teacher=teacherService.selectByPrimaryKey(id);
        teacherDetail = new TeacherDetail();

            User user = userService.selectByPrimaryKey(teacher.getUserId());
            if (user == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "无法找到Teacher表userID=" + teacher.getUserId() + "对应的user！");
                return result;
            }

            teacherDetail.setId(teacher.getUserId());
            teacherDetail.setNickName(user.getNickName());
            teacherDetail.setUserName(user.getUserName());
            teacherDetail.setMajor(teacher.getMajor());
            teacherDetail.setPhone(user.getPhone());
            teacherDetail.setSex(teacher.getSex());
            teacherDetail.setEmail(teacher.getEmail());
            teacherDetail.setPhoto(teacher.getPhoto());


        result.put("code", Constant.OK);
        result.put("msg", "返回老师信息成功！");
        result.put("teacherDetail", teacherDetail);
        return result;
    }
    @RequestMapping(value = "forbiddenTeacher", method = RequestMethod.GET)
    /*
    * author hanfeng
    * 禁用老师
    * */
    public @ResponseBody
    Map<String, Object> forbiddenTeacher(@RequestParam("id") Integer id,
                                         @RequestParam("forbidden") boolean forbidden
                                         ) {
        Map<String, Object> result = new HashMap<String, Object>();
        int one;
        Teacher teacher=teacherService.selectByPrimaryKey(id);

        if(teacher ==null){
            result.put("code",Constant.FAIL);
            result.put("msg", "未查到id=" + id + "的记录！");
            return result;
        }
        teacher.setForbidden(forbidden);
        one=teacherService.updateByPrimaryKey(teacher);
        if(one>0){
            result.put("code", Constant.OK);
            result.put("msg", "审批成功！");
        }else{
            result.put("code", Constant.FAIL);
            result.put("msg", "审批失败！更新数据库失败");
            return result;
        }
        return result;
    }
    @RequestMapping(value = "deleteTeacher", method = RequestMethod.GET)
    /*
    * author hanfeng
    * 删除
    * */
    public @ResponseBody
    Map<String, Object> deleteTeacher(@RequestParam("id") Integer id
    ) {
        Map<String, Object> result = new HashMap<String, Object>();
        int one;
        Teacher teacher=teacherService.selectByPrimaryKey(id);

        if(teacher ==null){
            result.put("code",Constant.FAIL);
            result.put("msg", "未查到id=" + id + "的记录！");
            return result;
        }
        teacher.setDeleteTag(false);
        one=teacherService.updateByPrimaryKey(teacher);
        if(one>0){
            result.put("code", Constant.OK);
            result.put("msg", "删除成功！");
        }else{
            result.put("code", Constant.FAIL);
            result.put("msg", "删除失败！更新数据库失败");
            return result;
        }
        return result;
    }
    @RequestMapping(value = "teacherMajor", method = RequestMethod.GET)
    public @ResponseBody
    /*
    * 获取老师专业列表
    * @author hanfeng
    * */
    Map<String, Object> teacherMajor() {
        Map<String, Object> result = new HashMap<String, Object>();

        TeacherExample teacherExample=new TeacherExample();
        List<Teacher> teachers=teacherService.selectByExample(teacherExample);
        JSONArray objects=new JSONArray();
        for(Teacher teacher:teachers){
            JSONObject obj=new JSONObject();
            obj.put("major",teacher.getMajor());
            objects.add(obj);
            }
            Set set=new HashSet(objects);
            result.put("major",set);
            return result;
    }
    @RequestMapping(value = "updateTeacher", method = RequestMethod.POST)
    /*
    * author hanfeng
    * 管理员修改老师
    * */
    public @ResponseBody
    Map<String, Object> updateTeacher(@RequestBody TeacherVo teacherVo
    ) {

        Map<String, Object> result = new HashMap<String, Object>();

        Integer id=teacherVo.getId();
        String sex=teacherVo.getSex();
        String major=teacherVo.getMajor();
        String rank=teacherVo.getRank();
        String phone=teacherVo.getPhone();
        String email=teacherVo.getEmail();

        Teacher teacher=teacherService.selectByPrimaryKey(id);

        if(teacher ==null){
            result.put("code",Constant.FAIL);
            result.put("msg", "未查到id=" + id + "的记录！");
            return result;
        }
        teacher.setMajor(major);
        teacher.setRank(rank);
        teacher.setSex(sex);
        teacher.setEmail(email);
        User user=userService.selectByPrimaryKey(teacher.getUserId());
        user.setPhone(phone);

        int one=teacherService.updateByPrimaryKey(teacher);
        int two=userService.updateByPrimaryKey(user);
        if(one>0&&two>0){
            result.put("code", Constant.OK);
            result.put("msg", "审批成功！");
        }else{
            result.put("code", Constant.FAIL);
            result.put("msg", "审批失败！更新数据库失败");
            return result;
        }
        return result;
    }


}