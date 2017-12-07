package edu.zjgsu.ito.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.RecruitmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    StudentRecruitmentService studentRecruitmentService;

    @Autowired
    StudentService studentService;
    @Autowired
    UserService userService;

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
}

