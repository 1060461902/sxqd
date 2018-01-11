package edu.zjgsu.ito.controller.admin;

import edu.zjgsu.ito.dao.CompanyMapper;
import edu.zjgsu.ito.dao.RecruitmentMapper;
import edu.zjgsu.ito.dao.StudentMapper;
import edu.zjgsu.ito.dao.StudentRecruitmentMapper;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "admin")
public class ViewController {
//    页大小

    @Autowired
    TeacherService teacherService;
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    WeightService weightService;
    @Autowired
    CheckNumService checkNumService;
    @Autowired
    TableDataService tableDataService;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StudentRecruitmentMapper studentRecruitmentMapper;
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    RecruitmentMapper recruitmentMapper;
    @Autowired
    RecruitmentService recruitmentService;


    @RequestMapping(value = "weight", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> setWeight() {
        int status;
        Map<String, Object> result = new HashMap<String, Object>();

        Weight weight=weightService.selectByPrimaryKey(1);

        result.put("tWeekReport",weight.gettWeekReport());
        result.put("tSummary",weight.gettSummary());
        result.put("tFinalReport",weight.gettFinalReport());
        result.put("cWeekReport",weight.getcWeekReport());
        result.put("cAttendance",weight.getcAttendance());
        result.put("teacherWeight",weight.getTeacherWeight());
        result.put("companyWeight",weight.getCompanyWeight());
        return result;
    }

    @RequestMapping(value = "index",method=RequestMethod.GET)
    public @ResponseBody
    /**
    * author hanfeng
    **/

    Map<String,Object> index(){
        Map<String, Object> result = new HashMap<String, Object>();
        int sum = 0;
//        DateFormat czx=new SimpleDateFormat("yyyy-MM-dd");
        CheckNumExample checkNumExample=new CheckNumExample();
        checkNumExample.setOrderByClause("id DESC");
        checkNumExample.setLimit(1);
        List<CheckNum> checkNums=checkNumService.selectByExample(checkNumExample);
        System.out.println(checkNums);
        CheckNum checkNum=checkNumService.selectByExample(checkNumExample).get(0);
//        JSONObject object=new JSONObject();
//        object.put("table",tableDatas);

        StudentExample studentExample = new StudentExample();
        studentExample.or().andDeleteTagEqualTo(true);
        String studentNum = String.valueOf(studentMapper.countByExample(studentExample));

        StudentRecruitmentExample example = new StudentRecruitmentExample();
        example.or().andPassingEqualTo(1);
        String employeeNum = String.valueOf(studentRecruitmentMapper.countByExample(example));

        CompanyExample companyExample = new CompanyExample();
        companyExample.or().andPassEqualTo(true).andDeleteTagEqualTo(true);
        String companyNum = String.valueOf(companyMapper.countByExample(companyExample));

        RecruitmentExample recruitmentExample = new RecruitmentExample();
        recruitmentExample.or().andPassEqualTo(true).andDeleteTagEqualTo(true).andForbiddenEqualTo(false).andRemoveEqualTo(false);

        String postNum = String.valueOf(recruitmentMapper.countByExample(recruitmentExample));

        List<Recruitment> recruitmentList = recruitmentService.selectByExample(recruitmentExample);
        for (Recruitment recruitment :
                recruitmentList) {
            sum += recruitment.getTotalNumber();
       }
        String headcounts = String.valueOf(sum);

        TableDataExample tableDataExample=new TableDataExample();
        tableDataExample.setOrderByClause("id");
        tableDataExample.setLimit(6);
        List<TableData> tableDatas=tableDataService.selectByExample(tableDataExample);
        System.out.println(tableDatas);
        Date a=new Date();
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");

        result.put("today",dateFormater.format(a));
        result.put("table",tableDatas);
        result.put("studentNum", studentNum);
        result.put("employeeNum", employeeNum);
        result.put("companyNum", companyNum);
        result.put("postNum", postNum);
        result.put("headcounts", headcounts);

        result.put("clockInNum", checkNum.getClockinnum());
        result.put("clockOutNum", checkNum.getClockoutnum());
        result.put("lastDayAttendNum", checkNum.getLastdayattendnum());
//        result.put("table", object);
        result.put("clockInNum", checkNum.getClockinnum());



        return result;
    }


}
