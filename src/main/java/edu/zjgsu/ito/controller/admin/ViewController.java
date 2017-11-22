package edu.zjgsu.ito.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.vo.FrontTeacherInfo;
import edu.zjgsu.ito.service.StuInfoService;
import edu.zjgsu.ito.service.TeacherInfoService;
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
    TeacherInfoService teacherInfoService;
    @Autowired
    UserService userService;
    @Autowired
    StuInfoService stuInfoService;

    /**
     *
     * @param pageNum 页码
     * @return
     * @author sawei
     */
    @RequestMapping(value = "showTeachers", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showTeachers(@RequestParam("pageNum") int pageNum) {


        FrontTeacherInfo FrontTeacherInfoTemp = null;
        List<FrontTeacherInfo> teacherList = new ArrayList<FrontTeacherInfo>();
//        List<String> studentList = new ArrayList<String>();
        Map<String, Object> result = new HashMap<String, Object>();

//        分页查询teacherinfo表所有的记录
        TeacherInfoExample teacherInfoExample = new TeacherInfoExample();
        teacherInfoExample.or().andIdIsNotNull();
//        分页
        Page<TeacherInfo> teacherInfos = PageHelper.startPage(pageNum,pageSize);
        teacherInfoService.selectByExample(teacherInfoExample);
        if (teacherInfos == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从teacherInfo表里查到记录！");
            return result;
        }

//        查找teacherInfos的每一个元素的全部信息
        for (TeacherInfo teacherInfo:
             teacherInfos) {
            FrontTeacherInfoTemp = new FrontTeacherInfo();

//            得到老师对应的user
            User user = userService.selectByPrimaryKey(teacherInfo.getUserid());
            if (user == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "无法找到teacherinfo表userID=" + teacherInfo.getUserid() + "对应的user！");
                return result;
            }

////            查询老师指导的学生
//            StuInfoExample stuInfoExample = new StuInfoExample();
//            stuInfoExample.or().andTeacheridEqualTo(teacherInfo.getId());
//            List<StuInfo> stuInfos = stuInfoService.selectByExample(stuInfoExample);
//            for (StuInfo stuInfo:
//                 stuInfos) {
//                studentList.add(stuInfo.getId())
//            }

//            设置返回给前端的对象的属性
            FrontTeacherInfoTemp.setId(teacherInfo.getId());
            FrontTeacherInfoTemp.setNickname(user.getNickname());
            FrontTeacherInfoTemp.setUsername(user.getUsername());
            FrontTeacherInfoTemp.setStatus(teacherInfo.getStatus());
            FrontTeacherInfoTemp.setPhone(user.getPhone());
            FrontTeacherInfoTemp.setForbidden(user.getForbidden());

//            加到list里面
            teacherList.add(FrontTeacherInfoTemp);
        }

        result.put("code", Constant.OK);
        result.put("msg", "返回老师信息成功！");
        result.put("teacherList", teacherList);

        return result;
    }
}
