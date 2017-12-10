package edu.zjgsu.ito.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.InternshipVo;
import edu.zjgsu.ito.vo.RecruitmentVo;
import edu.zjgsu.ito.vo.ScreenOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "admin")
public class ShowRecruitmentController {

    @Autowired
    CompanyViewService companyViewService;
    @Autowired
    RecruitmentService recruitmentService;
    @Autowired
    CompanyService companyService;
    @Autowired
    ReportService reportService;
    @Autowired
    StudentRecruitmentService studentRecruitmentService;
    @Autowired
    DailyCheckService dailyCheckService;
    @Autowired
    StudentService studentService;
    @Autowired
    UserService userService;
    @Autowired
    StudentRecruitmentService studentRecruitmentServiceService;
    @Autowired
    StudentRecruitmentViewService studentRecruitmentViewService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    SummaryService summaryService;

    @RequestMapping(value = "showRecruitment", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showRegisterCompanies(@RequestParam("companyName") String companyName) {

        RecruitmentVo recruitmentVo=null;
        List<RecruitmentVo> recruitmentVoList = new ArrayList<RecruitmentVo>();

/*
* @param
* @return
* 查看企业招聘
* @author hanfeng
* */
        Map<String, Object> result = new HashMap<String, Object>();

        RecruitmentExample recruitmentExample = new RecruitmentExample();
        recruitmentExample.or().andCheckedEqualTo(false);

        List<Recruitment> Recruitments = recruitmentService.selectByExample(recruitmentExample);

        if (Recruitments == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Company表里查到记录！");
            return result;
        }
        for (Recruitment recruitment : Recruitments) {
            recruitmentVo=new RecruitmentVo();
            CompanyView companyView = companyViewService.selectByKey(recruitment.getCompanyId());
            recruitmentVo.setContact(recruitment.getContact());
            recruitmentVo.setPost(recruitment.getPost());
            recruitmentVo.setAddress(recruitment.getAddress());
            recruitmentVo.setCompanyName(companyView.getCompanyName());
            recruitmentVo.setPostTime(recruitment.getPostTime());
            recruitmentVo.setCurrentNumber(recruitment.getCurrentNumber());
            recruitmentVo.setTotalNumber(recruitment.getTotalNumber());
            recruitmentVo.setId(recruitment.getId());
            recruitmentVo.setCompanyId(recruitment.getCompanyId());
            recruitmentVoList.add(recruitmentVo);
        }


        result.put("code", Constant.OK);
        result.put("RecruitmentList", recruitmentVoList);
        return result;
    }

    @RequestMapping(value = "showRecruitmentApplyList",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showRecruitmentApplyList(@RequestParam("companyName") String companyName) {

/** @param
* @return
* 查看招聘申请
* @author hanfeng
* */

        try {
            companyName= new String(companyName .getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(companyName);
        Map<String, Object> result = new HashMap<String, Object>();
            RecruitmentExample recruitmentExample = new RecruitmentExample();
//            recruitmentExample.or().andCheckedEqualTo(true).andPassEqualTo(true).andRemoveEqualTo(false);
        recruitmentExample.or().andCheckedEqualTo(false);

            RecruitmentVo recruitmentVo;
            List<RecruitmentVo> recruitmentVoList = new ArrayList<RecruitmentVo>();

            List<Recruitment> Recruitments;
            CompanyView companyView;

            if (companyName == null) {
                Recruitments = recruitmentService.selectByExample(recruitmentExample);
            }else {
                companyView=companyViewService.selectByName(companyName);
                Integer company_id=companyView.getId();
                Recruitments = recruitmentService.selectByCompanyId(company_id);
            }

            if (Recruitments == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "无法从Company表里查到记录！");
                return result;
            }
            System.out.println(Recruitments);
            for (Recruitment recruitment : Recruitments) {
                companyView = companyViewService.selectByKey(recruitment.getCompanyId());
                recruitmentVo=new RecruitmentVo();
                recruitmentVo.setPost(recruitment.getPost());
                recruitmentVo.setAddress(recruitment.getAddress());
                recruitmentVo.setCompanyName(companyView.getCompanyName());
                recruitmentVo.setPostTime(recruitment.getPostTime());
                recruitmentVo.setCurrentNumber(recruitment.getCurrentNumber());
                recruitmentVo.setTotalNumber(recruitment.getTotalNumber());
                recruitmentVo.setCompanyId(recruitment.getCompanyId());
                recruitmentVoList.add(recruitmentVo);


            }
            result.put("code", Constant.OK);
            result.put("msg", "返回公司信息成功！");
            result.put("recruitmentVoList", recruitmentVoList);
            return result;
        }
    @RequestMapping(value = "showRecruitmentStudent",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showRecruitmentStudent(@RequestParam("id") Integer id) {

/** @param
 * @return
 * 查看实习岗位下的学生名字
 * @author hanfeng
 * */
        Map<String, Object> result = new HashMap<String, Object>();
        StudentRecruitmentExample studentRecruitmentExample=new StudentRecruitmentExample();
        studentRecruitmentExample.or().andRecruitmentIdEqualTo(id).andPassingEqualTo(1);
        List<StudentRecruitment> studentRecruitments=studentRecruitmentService.selectByExample(studentRecruitmentExample);

        System.out.println(studentRecruitments);
        JSONArray objects = new JSONArray();

        for(StudentRecruitment studentRecruitment:studentRecruitments){
            Student student=studentService.selectByPrimaryKey(studentRecruitment.getStudentId());
            User user=userService.selectByPrimaryKey(student.getUserId());
            JSONObject obj = new JSONObject();
            obj.put("studentName",user.getNickName());
            obj.put("id",student.getId());
            objects.add(obj);
            result.put("Names",objects);
        }
            return result;
}
    @RequestMapping(value = "forbiddenRecruitment", method = RequestMethod.GET)
    /*
    * author hanfeng
    * 禁用实习
    * */
    public @ResponseBody
    Map<String, Object> forbiddenRecruitment(@RequestParam("id") Integer id,
                                         @RequestParam("forbidden") boolean forbidden
    ) {
        Map<String, Object> result = new HashMap<String, Object>();
        int one;
        Recruitment recruitment=recruitmentService.selectByPrimaryKey(id);
        if(recruitment ==null){
            result.put("code",Constant.FAIL);
            result.put("msg", "未查到id=" + id + "的记录！");
            return result;
        }
        recruitment.setForbidden(forbidden);
        one=recruitmentService.updateByPrimaryKey(recruitment);
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
    @RequestMapping(value = "showRecruitmentScreening",method=RequestMethod.POST)
    public @ResponseBody
    Map<String,Object> showRecruitmentScreening(@RequestBody ScreenOne screenOne){
        Map<String, Object> result = new HashMap<String, Object>();
        String grade =screenOne.getGrade();
        String major =screenOne.getMajor();
        String clss =screenOne.getClss();
        String status=screenOne.getStatus();
        String iteacher=screenOne.getIteacher();

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

        CompanyExample companyExample=new CompanyExample();
        companyExample.or().andPassEqualTo(true);
        List<Company> companies=companyService.selectByExample(companyExample);
        JSONArray companys=new JSONArray();
        for(Company company:companies){
            companys.add(company.getCompanyName());
        }
        result.put("company",companys);
        return result;
    }
    @RequestMapping(value = "showInternships", method = RequestMethod.POST)
    /*
    * author hanfeng
    * 查看实习列表
    * */
    public @ResponseBody
    Map<String, Object> showInternships(@RequestBody ScreenOne screenOne) {
        Map<String, Object> result = new HashMap<String, Object>();
        String grade =screenOne.getGrade();
        String major =screenOne.getMajor();
        String clss =screenOne.getClss();
        String status=screenOne.getStatus();
        String iteacher=screenOne.getIteacher();

        StudentRecruitmentViewExample studentRecruitmentViewExample=new StudentRecruitmentViewExample();
        System.out.println(grade);
        System.out.println(major);
        System.out.println(clss);
        System.out.println(clss);
        System.out.println(iteacher);
        StudentRecruitmentViewExample.Criteria criteria=studentRecruitmentViewExample.or();

        if(grade==null){
            criteria.andPassingEqualTo(1);
        }else{

            criteria.andGradeEqualTo(grade).andPassingEqualTo(1);
        }
        if(major==null){

        }else{
            criteria.andMajorEqualTo(major);
        }
        if(clss==null){

        }else{
            criteria.andClssEqualTo(clss);
        }
        if(status==null){

        }else{
            criteria.andStatusEqualTo(status);
        }


        if(iteacher==null){

        }else if(iteacher.equals("无指导老师")){
            criteria.andTeacherIdIsNull();
        }else if(iteacher.equals("有指导老师")){
            criteria.andTeacherIdIsNotNull();
        }


        List<StudentRecruitmentView> studentRecruitmentViews=studentRecruitmentViewService.selectByExample(studentRecruitmentViewExample);
        System.out.println("1111111111111111");
        System.out.println(studentRecruitmentViews);
        System.out.println("1111111111111111");

        List<InternshipVo> internshipVos=new ArrayList<>();

        for(StudentRecruitmentView studentRecruitmentView:studentRecruitmentViews){
            InternshipVo internshipVo=new InternshipVo();
            User user=userService.selectByPrimaryKey(studentRecruitmentView.getUserId());
            internshipVo.setNickname(user.getNickName());
            internshipVo.setClss(studentRecruitmentView.getClss());
            Company company=companyService.selectByPrimaryKey(studentRecruitmentView.getCompanyId());
            internshipVo.setCompanyId(studentRecruitmentView.getCompanyId());
            internshipVo.setCompany(company.getCompanyName());
            Recruitment recruitment=recruitmentService.selectByPrimaryKey(studentRecruitmentView.getRecruitmentId());
            internshipVo.setPost(recruitment.getPost());
            internshipVo.setStatus(studentRecruitmentView.getStatus());
            internshipVo.setStages(recruitment.getPostTime());
            Teacher teacher=teacherService.selectByPrimaryKey(studentRecruitmentView.getTeacherId());
            User user1=userService.selectByPrimaryKey(teacher.getUserId());
            internshipVo.setTeacherId(teacher.getId());
            internshipVo.setTeacherName(user1.getNickName());
            internshipVo.setId(studentRecruitmentView.getId());
            internshipVos.add(internshipVo);
        }
        result.put("internship",internshipVos);

        return result;
    }
    @RequestMapping(value = "showCheck",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showCheck(@RequestParam("id") Integer id,
                                  @RequestParam("month") String month) {
/** @param
 * @return
 * 查看考勤信息
 * @author hanfeng
 * */
        try {
            month= new String(month .getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Map<String, Object> result = new HashMap<String, Object>();
        DailyCheckExample dailyCheckExample=new DailyCheckExample();
        dailyCheckExample.or().andStudentIdEqualTo(id);
        List<DailyCheck> dailyChecks=dailyCheckService.selectByExample(dailyCheckExample);
        JSONArray objects=new JSONArray();

        System.out.println(month);

        for(DailyCheck dailyCheck:dailyChecks){
            JSONObject obj=new JSONObject();

            String one=new SimpleDateFormat("yyyy/MM/dd").format(dailyCheck.getStartTime());
            String two=new SimpleDateFormat("HH:mm:ss").format(dailyCheck.getStartTime());
            String three=new SimpleDateFormat("HH:mm:ss").format(dailyCheck.getStartTime());
            String four=new SimpleDateFormat("yyyy/MM").format(dailyCheck.getStartTime());

            System.out.println(four);

            obj.put("screen",four);
            obj.put("date",one);
            obj.put("startTime",two);
            obj.put("endTime",three);

            if(month.equals("全部时段")){
                objects.add(obj);
            }else if(month.equals(four)){
                objects.add(obj);
            }else{

            }

        }

        result.put("id",id);
        result.put("date",objects);
        return result;
    }
    @RequestMapping(value = "showCheckScreen",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showCheckScreen(@RequestParam("id") Integer id) {
        /** @param
         * @return
         * 查看考勤的筛选条件
         * @author hanfeng
         * */
        Map<String, Object> result = new HashMap<String, Object>();
        DailyCheckExample dailyCheckExample=new DailyCheckExample();

        dailyCheckExample.or().andStudentIdEqualTo(id);
        List<DailyCheck> dailyChecks=dailyCheckService.selectByExample(dailyCheckExample);

        JSONArray ovjects=new JSONArray();

        for(DailyCheck dailyCheck:dailyChecks){
            JSONObject ovj=new JSONObject();
            String four=new SimpleDateFormat("yyyy/MM").format(dailyCheck.getStartTime());
            ovj.put("screen",four);
            ovjects.add(ovj);
        }

        //Set set=new TreeSet();
        System.out.println(ovjects);
        Set set=new HashSet(ovjects);
        System.out.println(set);

        result.put("id",id);
        result.put("month",set);
        return result;
    }
    @RequestMapping(value = "showWeeklyList",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showWeeklyList(@RequestParam("id") Integer id) {
        /** @param
         * @return
         * 查看周报列表
         * @author hanfeng
         * */
        Map<String, Object> result = new HashMap<String, Object>();

        ReportExample reportExample=new ReportExample();

        reportExample.or().andStudentIdEqualTo(id);

        List<Report> reports=reportService.selectByExample(reportExample);

        JSONArray objects=new JSONArray();
        for(Report report:reports){
            JSONObject object=new JSONObject();
            object.put("week",report.getWeek());
            object.put("teacherScore",report.getScore());
            object.put("companyScore",report.getcScore());
            object.put("id",report.getId());
            object.put("datetime",report.getPublishedDate());
            objects.add(object);
        }
        result.put("weeklyList",objects);
        return result;
    }
    @RequestMapping(value = "showWeeklyDetail",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showWeeklyDetail(@RequestParam("id") Integer id) {
        /** @param
         * @return
         * 查看周报详情
         * @author hanfeng
         * */
        Map<String, Object> result = new HashMap<String, Object>();

        ReportExample reportExample=new ReportExample();

        reportExample.or().andStudentIdEqualTo(id);

        List<Report> reports=reportService.selectByExample(reportExample);

        JSONArray objects=new JSONArray();
        for(Report report:reports){
            JSONObject object=new JSONObject();
            object.put("week",report.getWeek());
            object.put("Score",report.getScore());
            object.put("cScore",report.getcScore());
            object.put("id",report.getId());
            object.put("publishedDate",report.getPublishedDate());
            object.put("title",report.getTitle());
            Student student=studentService.selectByPrimaryKey(report.getStudentId());
            User user=userService.selectByPrimaryKey(student.getUserId());
            object.put("name",user.getNickName());
            object.put("startTime",report.getStartTime());
            object.put("endTime",report.getEndTime());
            object.put("content",report.getContent());
            object.put("readoverTime",report.getReadoverTime());
            object.put("comment",report.getComment());
            User user2=userService.selectByPrimaryKey(student.getTeacherId());
            object.put("teacherName",user2.getNickName());
            object.put("eamil",student.getEmail());
            object.put("phone",user.getPhone());
            object.put("cReadoverTime",report.getcReadoverTime());
            object.put("cComment",report.getcComment());
            Company company =companyService.selectByPrimaryKey(student.getCompanyId());
            object.put("cName",company.getCompanyName());
            object.put("cEmail",company.getEmail());
            User user3=userService.selectByPrimaryKey(company.getUserId());
            object.put("cPhone",user3.getPhone());
            objects.add(object);
        }
        result.put("weeklyList",objects);
        return result;
    }
    @RequestMapping(value = "showSummaryDetail",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showSummaryDetail(@RequestParam("id") Integer id) {
        /** @param
         * @return
         * 查看小结详情
         * @author hanfeng
         * */
        Map<String, Object> result = new HashMap<String, Object>();

        SummaryExample summaryExample=new SummaryExample();

        summaryExample.or().andStudentIdEqualTo(id).andStatusIdEqualTo(true);

        List<Summary> summaries=summaryService.selectByExample(summaryExample);

        JSONArray objects=new JSONArray();
        for(Summary summary:summaries){
            JSONObject object=new JSONObject();
            object.put("Score",summary.getScore());
            object.put("id",summary.getId());
            object.put("publishedDate",summary.getPublishedDate());
            object.put("title",summary.getTitle());
            Student student=studentService.selectByPrimaryKey(summary.getStudentId());
            User user=userService.selectByPrimaryKey(student.getUserId());
            object.put("name",user.getNickName());
            object.put("startTime",summary.getStartTime());
            object.put("endTime",summary.getEndTime());
            object.put("content",summary.getContent());
            object.put("readoverTime",summary.getReadoverTime());
            object.put("comment",summary.getComment());
            User user2=userService.selectByPrimaryKey(student.getTeacherId());
            object.put("teacherName",user2.getNickName());
            object.put("eamil",student.getEmail());
            object.put("phone",user.getPhone());
            objects.add(object);
        }
        result.put("weeklyList",objects);
        return result;
    }
}

