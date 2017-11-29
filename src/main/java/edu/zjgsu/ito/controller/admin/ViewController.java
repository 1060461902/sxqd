package edu.zjgsu.ito.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.vo.FrontTeacher;
import edu.zjgsu.ito.service.StudentService;
import edu.zjgsu.ito.service.TeacherService;
import edu.zjgsu.ito.service.UserService;
import edu.zjgsu.ito.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "admin")
public class ViewController {
//    页大小
    public static final int pageSize = 2;

    @Autowired
    TeacherService teacherService;
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;

    /**
     *
     * @param
     * @return
     * @author sawei
     */
    @RequestMapping(value = "showTeachers", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showTeachers() {


        FrontTeacher FrontTeacherTemp = null;
        List<FrontTeacher> teacherList = new ArrayList<FrontTeacher>();
//        List<String> studentList = new ArrayList<String>();
        Map<String, Object> result = new HashMap<String, Object>();

//        分页查询Teacher表所有的记录
        TeacherExample TeacherExample = new TeacherExample();
        TeacherExample.or().andIdIsNotNull();
//        分页
//        List<Teacher> teachers;
        List<Teacher> teachers=teacherService.selectByExample(TeacherExample);
        if (teachers == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Teacher表里查到记录！");
            return result;
        }
//        查找Teachers的每一个元素的全部信息
        for (Teacher teacher:
             teachers) {
            FrontTeacherTemp = new FrontTeacher();
//            得到老师对应的user
            User user = userService.selectByPrimaryKey(teacher.getUserId());
            if (user == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "无法找到Teacher表userID=" + teacher.getUserId() + "对应的user！");
                return result;
            }

////            查询老师指导的学生
//            StuInfoExample stuInfoExample = new StuInfoExample();
//            stuInfoExample.or().andTeacheridEqualTo(Teacher.getId());
//            List<StuInfo> stuInfos = stuInfoService.selectByExample(stuInfoExample);
//            for (StuInfo stuInfo:
//                 stuInfos) {
//                studentList.add(stuInfo.getId())
//            }

//            设置返回给前端的对象的属性
            FrontTeacherTemp.setId(teacher.getId());
            FrontTeacherTemp.setnickName(user.getNickName());
            FrontTeacherTemp.setuserName(user.getUserName());
            FrontTeacherTemp.setStatus(teacher.getStatus());
            FrontTeacherTemp.setPhone(user.getPhone());
            FrontTeacherTemp.setForbidden(user.getForbidden());

//            加到list里面
            teacherList.add(FrontTeacherTemp);
        }

        result.put("code", Constant.OK);
        result.put("msg", "返回老师信息成功！");
        result.put("teacherList", teacherList);
        return result;

    }
}
