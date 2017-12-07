package edu.zjgsu.ito.controller.admin;

import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.CompanyService;
import edu.zjgsu.ito.service.StudentService;
import edu.zjgsu.ito.service.TeacherService;
import edu.zjgsu.ito.service.UserService;
import edu.zjgsu.ito.vo.ScreeningVo;
import edu.zjgsu.ito.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value ="admin")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "showStudents",method=RequestMethod.POST)
    public @ResponseBody
    /*
* @param
* 查看学生列表
* @return
* @author hanfeng
**/
    Map<String,Object> showCompanynames(@RequestBody ScreeningVo screeningVo){
        Map<String, Object> result = new HashMap<String, Object>();


        String grade=screeningVo.getGrade();
        String major=screeningVo.getMajor();
        String clss=screeningVo.getClss();
        Integer companyId=screeningVo.getCompanyId();
        String status=screeningVo.getStatus();
        String iTeacher=screeningVo.getiTeacher();

        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria  = studentExample.or();
        CompanyExample companyExample=new CompanyExample();

        criteria = grade.equals(null) ? criteria : criteria.andGradeEqualTo(grade);
        criteria = major.equals(null) ? criteria : criteria.andMajorEqualTo(major);
        criteria = clss.equals(null) ? criteria : criteria.andClssEqualTo(clss);
        criteria = companyId.equals(null) ? criteria : criteria.andCompanyIdEqualTo(companyId);
        criteria = status.equals(null) ? criteria : criteria.andStatusEqualTo(status);

        criteria = iTeacher.equals(null) ? criteria : criteria.andHaveteacherEqualTo(iTeacher);

        List<Student> students=studentService.selectByExample(studentExample);


        List<StudentVo> studentVos=new ArrayList<>();
        for(Student student:students){
            StudentVo studentVO=new StudentVo();
            User user=userService.selectByPrimaryKey(student.getUserId());
            Teacher teacher=teacherService.selectByPrimaryKey(student.getTeacherId());
            User userone=userService.selectByPrimaryKey(teacher.getUserId());
            studentVO.setNickName(user.getNickName());
            studentVO.setUserName(user.getUserName());
            studentVO.setMajor(student.getMajor());
            studentVO.setClss(student.getClss());
            studentVO.setGrade(student.getGrade());
            studentVO.setStatus(student.getStatus());
            studentVO.setStudentId(student.getId());
            studentVO.setTeacherName(userone.getNickName());
            studentVos.add(studentVO);
        }
        result.put("students",studentVos);


        return result;
    }

}
