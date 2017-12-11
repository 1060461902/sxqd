package edu.zjgsu.ito.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    StudentProjectService studentProjectService;
    @Autowired
    StudentClubService studentClubService;
    @Autowired
    StudentHonorService studentHonorService;
    @Autowired
    StudentSkillService studentSkillService;

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

        System.out.println(screeningVo);
        String grade=screeningVo.getGrade();
        String major=screeningVo.getMajor();
        String clss=screeningVo.getClss();
        String status=screeningVo.getStatus();
        System.out.println(grade);
        System.out.println(major);
        System.out.println(clss);
        System.out.println(status);

        StudentExample studentExample=new StudentExample();

        if(grade==null){
            studentExample.or().andDeleteTagEqualTo(true);
        }else{
            studentExample.or().andGradeEqualTo(grade);
        }
        if(major==null){
            studentExample.or();
        }else{
            studentExample.or().andMajorEqualTo(major);
        }
        if(clss==null){
            studentExample.or();
        }else{
            studentExample.or().andClssEqualTo(clss);
        }
        if(status==null){
            studentExample.or();
        }else{
            studentExample.or().andStatusEqualTo(status);
        }
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
            studentVO.setId(student.getId());
            studentVO.setTeacherName(userone.getNickName());
            studentVO.setTeacherId(student.getTeacherId());
            studentVO.setForbidden(student.getForbidden());
            studentVos.add(studentVO);
        }
        result.put("students",studentVos);


        return result;
    }
    @RequestMapping(value = "forbiddenStudent", method = RequestMethod.GET)
    /*
    * author hanfeng
    * 禁用学生
    * */
    public @ResponseBody
    Map<String, Object> forbiddenStudent(@RequestParam("id") Integer id,
                                         @RequestParam("forbidden") boolean forbidden
    ) {
        Map<String, Object> result = new HashMap<String, Object>();
        int one;
        Student student=studentService.selectByPrimaryKey(id);


        if(student ==null){
            result.put("code", Constant.FAIL);
            result.put("msg", "未查到id=" + id + "的记录！");
            return result;
        }
        student.setForbidden(forbidden);
        one=studentService.updateByPrimaryKey(student);
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
    @RequestMapping(value = "deleteStudent", method = RequestMethod.GET)
    /*
    * author hanfeng
    * 删除学生
    * */
    public @ResponseBody
    Map<String, Object> deleteStudent(@RequestParam("id") Integer id
    ) {
        Map<String, Object> result = new HashMap<String, Object>();
        int one;
        Student student=studentService.selectByPrimaryKey(id);


        if(student ==null){
            result.put("code", Constant.FAIL);
            result.put("msg", "未查到id=" + id + "的记录！");
            return result;
        }
        student.setDeleteTag(false);
        one=studentService.updateByPrimaryKey(student);
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

    @RequestMapping(value = "showStudentDetail",method=RequestMethod.GET)
    public @ResponseBody
    /*
* @param
* 查看学生简历
* @return
* @author hanfeng
**/
    Map<String,Object> showStudentDetail(@RequestParam("id")  Integer id){
        Map<String, Object> result = new HashMap<String, Object>();

        Student student=studentService.selectByPrimaryKey(id);
        StudentDetail studentDetail=new StudentDetail();


        User user=userService.selectByPrimaryKey(student.getUserId());
        if (user == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "无法找到student表userID=" + student.getId() + "对应的user！");
            return result;
        }
        studentDetail.setId(student.getId());
        studentDetail.setUserId(student.getUserId());
        studentDetail.setCompanyId(student.getCompanyId());
        studentDetail.setTeacherId(student.getTeacherId());
        studentDetail.setSex(student.getSex());
        studentDetail.setNational(student.getNation());
        studentDetail.setEnglish(student.getEnglish());
        studentDetail.setGrade(student.getGrade());
        studentDetail.setMajor(student.getMajor());
        studentDetail.setClss(student.getClss());
        studentDetail.setPhone(user.getPhone());
        studentDetail.setNickName(user.getNickName());
        studentDetail.setUserName(user.getUserName());
        studentDetail.setBirthday(student.getBirthday());
        studentDetail.setEmail(student.getEmail());
        result.put("student",studentDetail);

        StudentProjectExample studentProjectExample=new StudentProjectExample();
        studentProjectExample.or().andStudentIdEqualTo(student.getId()).andDeleteTagEqualTo(true);
        List<StudentProject> studentProjects=studentProjectService.selectByExample(studentProjectExample);
        JSONArray objects=new JSONArray();
        for(StudentProject studentProject:studentProjects){
            JSONObject obj=new JSONObject();
            obj.put("Project",studentProject);
            objects.add(obj);
        }
        result.put("ProjectList",objects);

        StudentSkillExample studentSkillExample=new StudentSkillExample();
        studentSkillExample.or().andStudentIdEqualTo(student.getId());
        List<StudentSkill> studentSkills=studentSkillService.selectByExample(studentSkillExample);
        JSONArray okjects=new JSONArray();
        for(StudentSkill studentSkill:studentSkills){
            JSONObject okj=new JSONObject();
            okj.put("Skill",studentSkill);
            okjects.add(okj);
        }
        result.put("SkillList",okjects);

        StudentHonorExample studentHonorExample=new StudentHonorExample();
        studentHonorExample.or().andStudentIdEqualTo(student.getId()).andDeleteTagEqualTo(true);
        List<StudentHonor> studentHonors=studentHonorService.selectByExample(studentHonorExample);
        JSONArray ovjects=new JSONArray();
        for(StudentHonor studentHonor:studentHonors){
            JSONObject ovj=new JSONObject();
            ovj.put("Honor",studentHonor);
            ovjects.add(ovj);
        }
        result.put("HonorList",ovjects);

        StudentClubExample studentClubExample=new StudentClubExample();
        studentClubExample.or().andStudentIdEqualTo(student.getId()).andDelectTagEqualTo(true);
        List<StudentClub> studentClubs=studentClubService.selectByExample(studentClubExample);
        JSONArray opjects=new JSONArray();
        System.out.println(studentClubs);
        for(StudentClub studentClub:studentClubs){
            JSONObject opj=new JSONObject();
            opj.put("Club",studentClub);
            opjects.add(opj);
        }
        result.put("ClubList",opjects);
        return result;
    }
    @RequestMapping(value = "showScreening",method=RequestMethod.POST)
    public @ResponseBody
    Map<String,Object> showScreening(@RequestBody ScreeningVo screeningVo){
        Map<String, Object> result = new HashMap<String, Object>();
        String grade =screeningVo.getGrade();
        String major =screeningVo.getMajor();
        String clss =screeningVo.getClss();
        String status=screeningVo.getStatus();

        StudentExample studentExample1=new StudentExample();
        List<Student> students1=studentService.selectByExample(studentExample1);
        Set set1=new TreeSet();
        for(Student student:students1){
            set1.add(student.getGrade());
        }
        result.put("grade",set1);

        StudentExample studentExample2=new StudentExample();
        if(grade==null){
            studentExample2.or();
        }else{
            studentExample2.or().andGradeEqualTo(grade);
        }
        List<Student> students2=studentService.selectByExample(studentExample2);
        Set set2=new TreeSet();
        for(Student student:students2){
            set2.add(student.getMajor());
        }
        result.put("major",set2);


        StudentExample studentExample3=new StudentExample();
        if(major==null){
            studentExample2.or();
        }else{
            studentExample2.or().andGradeEqualTo(major);
        }
        List<Student> students3=studentService.selectByExample(studentExample2);
        Set set3=new TreeSet();
        for(Student student:students3){
            set3.add(student.getClss());
        }
        result.put("clss",set3);

        StudentExample studentExample4=new StudentExample();
        studentExample4.or();
        List<Student> students4=studentService.selectByExample(studentExample4);
        Set set4=new TreeSet();
        for(Student student:students4){
            set4.add(student.getStatus());
        }
        result.put("status",set4);
        return result;
    }

    @RequestMapping(value = "assignedStudent", method = RequestMethod.POST)

    public @ResponseBody
    Map<String, Object> assignedStudent(@RequestBody AssignedStudent assignedStudent) {
          /*
    * author hanfeng
    * 分配学生
    * */
        Map<String, Object> result = new HashMap<String, Object>();
         System.out.println(assignedStudent);
        int one;
        Integer[] ids=assignedStudent.getIds();
        Integer teacherId=assignedStudent.getTeacherId();
         System.out.println(ids);
         System.out.println(teacherId);
       for(Integer id:ids){
           Student student=studentService.selectByPrimaryKey(id);
           if(student ==null){
               result.put("code", Constant.FAIL);
               result.put("msg", "未查到id=" + id + "的记录！");
               return result;
           }
           student.setTeacherId(teacherId);
           one=studentService.updateByPrimaryKey(student);
           if(one<=0){
               result.put("code", Constant.FAIL);
               result.put("msg", "审批失败！更新数据库失败");
               return result;
           }
       }
            result.put("code", Constant.OK);
            result.put("msg", "审批成功！");
            return result;
    }
    @RequestMapping(value = "updateStudent", method = RequestMethod.POST)
    /*
    * author hanfeng
    * 管理员修改老师
    * */
    public @ResponseBody
    Map<String, Object> updateStudent(@RequestBody StudentBaseVo studentBaseVo
    ) {

        Map<String, Object> result = new HashMap<String, Object>();

        Integer id=studentBaseVo.getId();
        Integer sex=studentBaseVo.getSex();
        String nation=studentBaseVo.getNation();
        String birthday=studentBaseVo.getBirthday();
        String phone=studentBaseVo.getPhone();
        String email=studentBaseVo.getEmail();

        Student student=studentService.selectByPrimaryKey(id);

        if(student ==null){
            result.put("code",Constant.FAIL);
            result.put("msg", "未查到id=" + id + "的记录！");
            return result;
        }
        if(sex==0){
            student.setSex(false);
        }else{
            student.setSex(true);
        }
        student.setEmail(email);
        student.setNation(nation);
        student.setBirthday(birthday);
        User user=userService.selectByPrimaryKey(student.getUserId());
        user.setPhone(phone);
        int one=studentService.updateByPrimaryKey(student);
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
