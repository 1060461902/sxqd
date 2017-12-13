package edu.zjgsu.ito.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.InternshipVo;
import edu.zjgsu.ito.vo.RecruitmentVo;
import edu.zjgsu.ito.vo.ScreenTwo;
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
    WeightService weightService;
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
    @Autowired
    ImageService imageService;
    @Autowired
    ScoreService scoreService;

    @RequestMapping(value = "showRecruitment", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showRecruitment(@RequestParam("companyName") String companyName,
                                              @RequestParam("status") String status
                                              ) {

        RecruitmentVo recruitmentVo=null;
        List<RecruitmentVo> recruitmentVoList = new ArrayList<RecruitmentVo>();

/*
* @param
* @return
* 查看招聘信息列表
* @author hanfeng
* */
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            companyName= new String(companyName .getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            status= new String(status .getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        CompanyExample companyExample=new CompanyExample();
        List<Recruitment> Recruitments=null;
        System.out.println(companyName);
        RecruitmentExample recruitmentExample = new RecruitmentExample();

        if(companyName.equals("招聘公司")){
            recruitmentExample.or().andPassEqualTo(true);
        }else{
            companyExample.or().andPassEqualTo(true).andDeleteTagEqualTo(true).andCompanyNameEqualTo(companyName);
            List<Company> companies=companyService.selectByExample(companyExample);
            for(Company company:companies){
                recruitmentExample.or().andPassEqualTo(true).andCompanyIdEqualTo(company.getId());
            }
        }
        if(status.equals("招聘状态")){
            recruitmentExample.or();
        }else if(status.equals("招聘中")){
            recruitmentExample.or().andRecruitmentEqualTo(true);
        }else if(status.equals("已过期")){
            recruitmentExample.or().andRecruitmentEqualTo(false);
        }
            Recruitments = recruitmentService.selectByExample(recruitmentExample);

        if (Recruitments == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Recruitment表里查到记录！");
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
            recruitmentVo.setForbidden(recruitment.getForbidden());
            recruitmentVoList.add(recruitmentVo);
        }


        result.put("code", Constant.OK);
        result.put("RecruitmentList", recruitmentVoList);
        return result;

    }

    @RequestMapping(value = "showRecruitmentApplyList",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showRecruitmentApplyList(@RequestParam("companyId") Integer companyId) {

/** @param
* @return
* 查看招聘申请
* @author hanfeng
* */

//        try {
//            companyName= new String(companyName .getBytes("iso8859-1"),"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }


        Map<String, Object> result = new HashMap<String, Object>();
        RecruitmentExample recruitmentExample = new RecruitmentExample();

        if (companyId==0){
            recruitmentExample.or().andCheckedEqualTo(false);

        }else{
            recruitmentExample.or().andCheckedEqualTo(false).andCompanyIdEqualTo(companyId);
        }

            RecruitmentVo recruitmentVo;
            List<RecruitmentVo> recruitmentVoList = new ArrayList<RecruitmentVo>();

            List<Recruitment> Recruitments;
            Recruitments = recruitmentService.selectByExample(recruitmentExample);

            if (Recruitments == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "无法从Company表里查到记录！");
                return result;
            }

        System.out.println(Recruitments);

            for (Recruitment recruitment : Recruitments) {
                CompanyView companyView = companyViewService.selectByKey(recruitment.getCompanyId());
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
            result.put("recruitmentApplyList", recruitmentVoList);
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
    /*h获取实习的筛选条件
    * */
    public @ResponseBody
    Map<String,Object> showRecruitmentScreening(@RequestBody ScreenTwo screenTwo){
        Map<String, Object> result = new HashMap<String, Object>();
        System.out.println(screenTwo);

        String grade =screenTwo.getGrade();
        String clss =screenTwo.getClss();
        String status=screenTwo.getStatus();
        String iteacher=screenTwo.getIteacher();
        Integer companyId=screenTwo.getCompanyId();
        String company=screenTwo.getCompany();

        System.out.println(grade);
        System.out.println(clss);
        System.out.println(companyId);
        System.out.println(company);
        System.out.println(iteacher);
        System.out.println(status);

        StudentExample studentExample1=new StudentExample();

        studentExample1.or().andCompanyIdIsNotNull();

        List<Student> students1=studentService.selectByExample(studentExample1);
        Set set1=new TreeSet();
        for(Student student:students1){
            set1.add(student.getGrade());
        }
        result.put("grade",set1);
        System.out.println("11111111111111");

        StudentExample studentExample2=new StudentExample();
        StudentExample.Criteria criteria=studentExample2.or();
        if(grade.equals("年级")){

        }else{
            criteria.andGradeEqualTo(grade);
        }
        List<Student> students2=studentService.selectByExample(studentExample2);
        Set set2=new TreeSet();
        for(Student student:students2){
            set2.add(student.getClss());
        }
        result.put("clss",set2);

        if(clss.equals("班级")){

        }else{
            criteria.andClssEqualTo(clss);
        }
        List<Student> students3=studentService.selectByExample(studentExample2);
        Set set3=new TreeSet();

        for(Student student:students3){
            set3.add(student.getCompanyId());
        }

        Iterator<Integer> temp = set3.iterator();
        JSONArray jsonArray=new JSONArray();
        while (temp.hasNext()) {
            Company company1=companyService.selectByPrimaryKey(temp.next());
            JSONObject obj=new JSONObject();
            obj.put("companyName",company1.getCompanyName());
            obj.put("companyId",company1.getId());
            jsonArray.add(obj);
        }

        result.put("company",jsonArray);

//        StudentExample studentExample4=new StudentExample();
//        studentExample4.or();
//        List<Student> students4=studentService.selectByExample(studentExample4);
//        Set set5=new TreeSet();
//        for(Student student:students4){
//            set5.add(student.getStatus());
//        }

        return result;
    }
    @RequestMapping(value = "showInternships", method = RequestMethod.POST)
    /*
    * author hanfeng
    * 查看实习列表
    * */
    public @ResponseBody
    Map<String, Object> showInternships(@RequestBody ScreenTwo screenOne) {
        Map<String, Object> result = new HashMap<String, Object>();

        String grade =screenOne.getGrade();
        Integer companyId =screenOne.getCompanyId();
        String clss =screenOne.getClss();
        String status=screenOne.getStatus();
        String iteacher=screenOne.getIteacher();
        String company=screenOne.getCompany();


        StudentRecruitmentViewExample studentRecruitmentViewExample=new StudentRecruitmentViewExample();

        StudentRecruitmentViewExample.Criteria criteria=studentRecruitmentViewExample.or();

        if(grade.equals("年级")){
            criteria.andPassingEqualTo(1);
        }else{
            criteria.andGradeEqualTo(grade).andPassingEqualTo(1);
        }
        if(clss.equals("班级")){

        }else{
            criteria.andClssEqualTo(clss);
        }
        if(companyId==null){

        }else{
            criteria.andCompanyIdEqualTo(companyId);
        }

        if(status.equals("实习状态")){

        }else{
            criteria.andStatusEqualTo(status);
        }
        if(iteacher.equals("有无指导老师")){

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
            Company company1=companyService.selectByPrimaryKey(studentRecruitmentView.getCompanyId());
            internshipVo.setCompanyId(studentRecruitmentView.getCompanyId());
            internshipVo.setCompany(company1.getCompanyName());
            Recruitment recruitment=recruitmentService.selectByPrimaryKey(studentRecruitmentView.getRecruitmentId());
            internshipVo.setPost(recruitment.getPost());
            internshipVo.setStatus(studentRecruitmentView.getStatus());
            internshipVo.setStages(recruitment.getPostTime());
            Teacher teacher=teacherService.selectByPrimaryKey(studentRecruitmentView.getTeacherId());
            User user1=userService.selectByPrimaryKey(teacher.getUserId());
            internshipVo.setTeacherId(teacher.getId());
            internshipVo.setTeacherName(user1.getNickName());
            internshipVo.setId(studentRecruitmentView.getId());
            internshipVo.setStudentId(studentRecruitmentView.getStudentId());
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
            ovj.put("screens",four);
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
            object.put("datetime",new SimpleDateFormat("yyyy-MM-dd").format(report.getPublishedDate()));
            objects.add(object);
        }
        result.put("studentId",id);
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
            object.put("publishedDate",new SimpleDateFormat("yyyy-MM-dd").format(report.getPublishedDate()));
            object.put("title",report.getTitle());
            Student student=studentService.selectByPrimaryKey(report.getStudentId());
            User user=userService.selectByPrimaryKey(student.getUserId());
            object.put("name",user.getNickName());
            object.put("startTime",new SimpleDateFormat("yyyy-MM-dd").format(report.getStartTime()));
            object.put("endTime",new SimpleDateFormat("yyyy-MM-dd").format(report.getEndTime()));
            object.put("content",report.getContent());
            object.put("readoverTime",new SimpleDateFormat("yyyy-MM-dd").format(report.getReadoverTime()));
            object.put("comment",report.getComment());
            User user2=userService.selectByPrimaryKey(student.getTeacherId());
            object.put("teacherName",user2.getNickName());
            object.put("eamil",student.getEmail());
            object.put("phone",user.getPhone());
            object.put("cReadoverTime",new SimpleDateFormat("yyyy-MM-dd").format(report.getcReadoverTime()));
            object.put("cComment",report.getcComment());
            Company company =companyService.selectByPrimaryKey(student.getCompanyId());
            object.put("cName",company.getCompanyName());
            object.put("cEmail",company.getEmail());
            User user3=userService.selectByPrimaryKey(company.getUserId());
            ImageExample imageExample=new ImageExample();
            imageExample.or().andReportIdEqualTo(report.getId());
            List<Image> images=imageService.selectByExample(imageExample);
            object.put("photp",images);
            object.put("cPhone",user3.getPhone());

            objects.add(object);
        }
        result.put("studentId",id);
        result.put("weeklyList",objects);
        return result;
    }
    @RequestMapping(value = "showSummary",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showSummary(@RequestParam("id") Integer id) {
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
            object.put("publishedDate",new SimpleDateFormat("yyyy-MM-dd").format(summary.getPublishedDate()));
            object.put("title",summary.getTitle());
            Student student=studentService.selectByPrimaryKey(summary.getStudentId());
            User user=userService.selectByPrimaryKey(student.getUserId());
            object.put("name",user.getNickName());

            object.put("startTime",new SimpleDateFormat("yyyy-MM-dd").format(summary.getStartTime()));

            object.put("endTime",new SimpleDateFormat("yyyy-MM-dd").format(summary.getEndTime()));
            object.put("content",summary.getContent());
            object.put("readoverTime",new SimpleDateFormat("yyyy-MM-dd").format(summary.getReadoverTime()));
            object.put("comment",summary.getComment());
            User user2=userService.selectByPrimaryKey(student.getTeacherId());
            object.put("teacherName",user2.getNickName());
            object.put("email",student.getEmail());
            object.put("phone",user.getPhone());
            objects.add(object);
        }
        result.put("studentId",id);
        result.put("summary",objects);
        return result;
    }
    @RequestMapping(value = "showSummaryDetail",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showSummaryDetail(@RequestParam("id") Integer id) {
        /** @param
         * @return
         * 查看报告详情
         * @author hanfeng
         * */
        Map<String, Object> result = new HashMap<String, Object>();

        SummaryExample summaryExample=new SummaryExample();

        summaryExample.or().andStudentIdEqualTo(id).andStatusIdEqualTo(false);

        List<Summary> summaries=summaryService.selectByExample(summaryExample);

        JSONArray objects=new JSONArray();
        for(Summary summary:summaries){
            JSONObject object=new JSONObject();
            object.put("Score",summary.getScore());
            object.put("id",summary.getId());
            object.put("publishedDate",new SimpleDateFormat("yyyy-MM-dd").format(summary.getPublishedDate()));
            object.put("title",summary.getTitle());
            Student student=studentService.selectByPrimaryKey(summary.getStudentId());
            User user=userService.selectByPrimaryKey(student.getUserId());
            object.put("name",user.getNickName());
            object.put("startTime",new SimpleDateFormat("yyyy-MM-dd").format(summary.getStartTime()));
            object.put("endTime",new SimpleDateFormat("yyyy-MM-dd").format(summary.getEndTime()));
            object.put("content",summary.getContent());
            object.put("readoverTime",new SimpleDateFormat("yyyy-MM-dd").format(summary.getReadoverTime()));
            object.put("comment",summary.getComment());
            User user2=userService.selectByPrimaryKey(student.getTeacherId());
            object.put("teacherName",user2.getNickName());
            object.put("email",student.getEmail());
            object.put("phone",user.getPhone());
            objects.add(object);
        }
        result.put("studentId",id);
        result.put("summary",objects);
        return result;
    }

    @RequestMapping(value = "showScore",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showScore(@RequestParam("id") Integer id) {
        /** @param
         * @return
         * 查看实习成绩详情
         * @author hanfeng
         * */
        Map<String, Object> result = new HashMap<String, Object>();
        Score score=scoreService.selectByStudent(id);
        Weight weight=weightService.selectByPrimaryKey(1);

       System.out.println(weight.toString());

        JSONObject obj=new JSONObject();

        float tweek=score.gettWeekReport();  //老师给的周报加权
        float tsummary=score.gettSummary();  //老师的小结成绩

        Float treport=score.gettFinalReport();
        if (treport == null) {
            treport = 0F;
        }
        System.out.println(treport);

        float  tweighting=tweek*weight.getcWeekReport()+tsummary*weight.gettSummary()+treport*weight.gettFinalReport();

        obj.put("totalScore",tweighting);
        obj.put("tWeekly",tweek);
        obj.put("summary",tsummary);
        obj.put("report",treport);
        obj.put("Weighting", tweighting);

        obj.put("ttotalScore",score.gettWeekReport()*weight.getcWeekReport()+score.gettSummary()*weight.gettSummary()+score.gettFinalReport()*weight.gettFinalReport()+score.getAdditionalScore());

        obj.put("cWeekly",score.getcWeekReport()*weight.getcWeekReport());
        obj.put("checkonWork",score.getcAttendance()*weight.getcAttendance());
        obj.put("ctotalScore",score.getcWeekReport()*weight.getcWeekReport()+score.getcAttendance()*weight.getcAttendance());
        obj.put("additionalPoints",score.getAdditionalScore());


        result.put("score",obj);

        return result;
    }

    @RequestMapping(value = "showCompanyRecruitments", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showCompanyRecruitments(@RequestParam("companyId") Integer companyId,
                                               @RequestParam("month") String month) {

        RecruitmentVo recruitmentVo=null;
        List<RecruitmentVo> recruitmentVoList = new ArrayList<RecruitmentVo>();

        /*
        * @param
        * @return
        * 查看某个企业招聘信息
        * @author hanfeng
        * */
        Map<String, Object> result = new HashMap<String, Object>();

        RecruitmentExample recruitmentExample = new RecruitmentExample();
        recruitmentExample.or().andPassEqualTo(true).andCompanyIdEqualTo(companyId);

        List<Recruitment> Recruitments = recruitmentService.selectByExample(recruitmentExample);

        if (Recruitments == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Recruitments表里查到记录！");
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

    @RequestMapping(value = "showRecruitmentScreen",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showRecruitmentScreen(@RequestParam("id") Integer id) {
        /** @param
         * @return
         * 查看某个公司招聘岗位的筛选条件
         * @author hanfeng
         * */
        Map<String, Object> result = new HashMap<String, Object>();
         RecruitmentExample recruitmentExample=new RecruitmentExample();

        recruitmentExample.or().andCompanyIdEqualTo(id);
        List<Recruitment> recruitments = recruitmentService.selectByExample(recruitmentExample);
        if (recruitments == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Recruitments表里查到记录！");
            return result;
        }


        JSONArray ovjects=new JSONArray();

        for(Recruitment recruitment:recruitments){
            JSONObject ovj=new JSONObject();
            String four=new SimpleDateFormat("yyyy/MM").format(recruitment.getStarttime());
            ovj.put("screen",four);
            ovjects.add(ovj);
        }
        System.out.println(ovjects);
        Set set=new HashSet(ovjects);
        System.out.println(set);

        result.put("id",id);
        result.put("month",set);
        return result;
    }
    @RequestMapping(value = "showRecruitmentDetails", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showRecruitmentDetails(@RequestParam("id") Integer id

    ) {
/*
* @param
* @return
* 查看企业招聘详情
* @author hanfeng
* */
        Map<String, Object> result = new HashMap<String, Object>();
        List<Recruitment> Recruitments=null;

        Recruitment recruitment = recruitmentService.selectByPrimaryKey(id);

        if (Recruitments == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Recruitment表里查到记录！");
            return result;
        }
            JSONObject object=new JSONObject();
            object.put("post",recruitment.getPost());
            object.put("totalnumber",recruitment.getTotalNumber());
            object.put("postTime",recruitment.getPostTime());
            object.put("address",recruitment.getAddress());
            object.put("skillRequire",recruitment.getSkillRequire());
            Company company=companyService.selectByPrimaryKey(recruitment.getCompanyId());
            object.put("companyName",company.getCompanyName());
            object.put("logo",company.getLogo());
            object.put("type",company.getType());
            object.put("size",company.getSize());
            object.put("stage",company.getStage());
            object.put("postInfo",recruitment.getPostInfo());
            object.put("id",recruitment.getId());
            result.put("code", Constant.OK);
            result.put("recruitmentDetails", object);
            return result;

    }
}

