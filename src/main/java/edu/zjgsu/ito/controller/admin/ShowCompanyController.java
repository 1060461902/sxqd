package edu.zjgsu.ito.controller.admin;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.CompanyVo;
import edu.zjgsu.ito.vo.FrontDynadic;
import edu.zjgsu.ito.vo.MVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;



@Controller
@RequestMapping(value ="admin")
public class ShowCompanyController {
    //页大小
//    public static final int pageSize=2;
    @Autowired
    CompanyService companyService;
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    CompanyViewService companyViewService;
    @Autowired
    CompanyImageService companyImageService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CompanyMarkService companyMarkService;
    @Autowired
    DynamicApproveService dynamicApproveService;
    @Autowired
    RecruitmentService recruitmentService;

    @RequestMapping(value ="showCompanies",method=RequestMethod.GET)
    public @ResponseBody Map<String,Object> showCompanies(@RequestParam("number") String number
    ) {

/*
* @param
* @return
* 查看已在系统内注册好的企业信息
* @author hanfeng
* */

        Map<String, Object> result = new HashMap<String, Object>();
        CompanyViewExample CompanyViewExample=new CompanyViewExample();
        CompanyViewExample.or().andDeleteTagEqualTo(true).andPassEqualTo(true);

        if(number.equals("0")){
            List<CompanyView> companyViews=companyViewService.selectByExample(CompanyViewExample);
            CompanyVo companyVo;
            List<CompanyVo> companyVoList=new ArrayList<>();
            if(companyViews == null){
                result.put("code", Constant.FAIL);
                result.put("msg", "无法从Company表里查到记录！");
                return result;
            }
            for(CompanyView companyView:companyViews){

                User user = userService.selectByPrimaryKey(companyView.getUserId());
                companyVo =new CompanyVo();
                companyVo.setContact(user.getNickName());
                companyVo.setCompanyName(companyView.getCompanyName());
                companyVo.setId(companyView.getId());

                RecruitmentExample recruitmentExample=new RecruitmentExample();
                recruitmentExample.or().andCompanyIdEqualTo(companyView.getId()).andForbiddenEqualTo(false);
                companyVo.setNowIntership(recruitmentService.countByExample(recruitmentExample));

                RecruitmentExample irecruitmentExample=new RecruitmentExample();
                irecruitmentExample.or().andCompanyIdEqualTo(companyView.getId());
                companyVo.setAllIntership(recruitmentService.countByExample(irecruitmentExample));
                companyVo.setForbidden(companyView.getForbidden());
                StudentExample studentExample=new StudentExample();
                studentExample.or().andCompanyIdEqualTo(companyView.getId());
                companyVo.setStudentNumber(studentService.countByExample(studentExample));
                companyVoList.add(companyVo);
                if (user == null) {
                    result.put("code", Constant.FAIL);
                    result.put("msg", "无法找到Company表userID=" + companyView.getUserId() + "对应的user！");
                    return result;
                }
            }
            result.put("code", Constant.OK);
            result.put("msg", "返回公司信息成功！");
            result.put("compamyViewList",companyVoList);

        }else if(number.equals("少于5人")){
            List<CompanyView> companyViews=companyViewService.selectByExample(CompanyViewExample);
            CompanyVo companyVo;
            List<CompanyVo> companyVoList=new ArrayList<>();
            if(companyViews == null){
                result.put("code", Constant.FAIL);
                result.put("msg", "无法从Company表里查到记录！");
                return result;
            }
            for(CompanyView companyView:companyViews) {
                RecruitmentExample recruitmentExample1 = new RecruitmentExample();
                recruitmentExample1.or().andCompanyIdEqualTo(companyView.getId());
                List<Recruitment> recruitmentList = recruitmentService.selectByExample(recruitmentExample1);
                Integer sum =0;
                for (Recruitment recruitment : recruitmentList) {
                    sum = sum + recruitment.getCurrentNumber();
                }
                if (sum < 5) {
                    User user = userService.selectByPrimaryKey(companyView.getUserId());
                    companyVo = new CompanyVo();
                    companyVo.setContact(user.getNickName());
                    companyVo.setCompanyName(companyView.getCompanyName());
                    companyVo.setId(companyView.getId());

                    RecruitmentExample recruitmentExample = new RecruitmentExample();
                    recruitmentExample.or().andCompanyIdEqualTo(companyView.getId()).andForbiddenEqualTo(false);
                    companyVo.setNowIntership(recruitmentService.countByExample(recruitmentExample));

                    RecruitmentExample irecruitmentExample = new RecruitmentExample();
                    irecruitmentExample.or().andCompanyIdEqualTo(companyView.getId());
                    companyVo.setAllIntership(recruitmentService.countByExample(irecruitmentExample));
                    companyVo.setForbidden(companyView.getForbidden());
                    StudentExample studentExample = new StudentExample();
                    studentExample.or().andCompanyIdEqualTo(companyView.getId());
                    companyVo.setStudentNumber(studentService.countByExample(studentExample));
                    companyVoList.add(companyVo);
                    if (user == null) {
                        result.put("code", Constant.FAIL);
                        result.put("msg", "无法找到Company表userID=" + companyView.getUserId() + "对应的user！");
                        return result;
                    }


                }
                result.put("code", Constant.OK);
                result.put("msg", "返回公司信息成功！");
                result.put("compamyViewList", companyVoList);
            }
        }else if(number.equals("5~10人")){
            List<CompanyView> companyViews=companyViewService.selectByExample(CompanyViewExample);
            CompanyVo companyVo;
            List<CompanyVo> companyVoList=new ArrayList<>();
            if(companyViews == null){
                result.put("code", Constant.FAIL);
                result.put("msg", "无法从Company表里查到记录！");
                return result;
            }
            for(CompanyView companyView:companyViews) {
                RecruitmentExample recruitmentExample1 = new RecruitmentExample();
                recruitmentExample1.or().andCompanyIdEqualTo(companyView.getId());
                List<Recruitment> recruitmentList = recruitmentService.selectByExample(recruitmentExample1);
                Integer sum =0;
                for (Recruitment recruitment : recruitmentList) {
                    sum = sum + recruitment.getCurrentNumber();
                }
                if (5<sum&&sum<10) {
                    User user = userService.selectByPrimaryKey(companyView.getUserId());
                    companyVo = new CompanyVo();
                    companyVo.setContact(user.getNickName());
                    companyVo.setCompanyName(companyView.getCompanyName());
                    companyVo.setId(companyView.getId());

                    RecruitmentExample recruitmentExample = new RecruitmentExample();
                    recruitmentExample.or().andCompanyIdEqualTo(companyView.getId()).andForbiddenEqualTo(false);
                    companyVo.setNowIntership(recruitmentService.countByExample(recruitmentExample));

                    RecruitmentExample irecruitmentExample = new RecruitmentExample();
                    irecruitmentExample.or().andCompanyIdEqualTo(companyView.getId());
                    companyVo.setAllIntership(recruitmentService.countByExample(irecruitmentExample));
                    companyVo.setForbidden(companyView.getForbidden());
                    StudentExample studentExample = new StudentExample();
                    studentExample.or().andCompanyIdEqualTo(companyView.getId());
                    companyVo.setStudentNumber(studentService.countByExample(studentExample));
                    companyVoList.add(companyVo);
                    if (user == null) {
                        result.put("code", Constant.FAIL);
                        result.put("msg", "无法找到Company表userID=" + companyView.getUserId() + "对应的user！");
                        return result;
                    }


                }
                result.put("code", Constant.OK);
                result.put("msg", "返回公司信息成功！");
                result.put("compamyViewList", companyVoList);
            }
       }else if(number.equals("10~20人")){
            List<CompanyView> companyViews=companyViewService.selectByExample(CompanyViewExample);
            CompanyVo companyVo;
            List<CompanyVo> companyVoList=new ArrayList<>();
            if(companyViews == null){
                result.put("code", Constant.FAIL);
                result.put("msg", "无法从Company表里查到记录！");
                return result;
            }
            for(CompanyView companyView:companyViews) {
                RecruitmentExample recruitmentExample1 = new RecruitmentExample();
                recruitmentExample1.or().andCompanyIdEqualTo(companyView.getId());
                List<Recruitment> recruitmentList = recruitmentService.selectByExample(recruitmentExample1);
                Integer sum =0;
                for (Recruitment recruitment : recruitmentList) {
                    sum = sum + recruitment.getCurrentNumber();
                }
                if (10<sum&&sum<20) {
                    User user = userService.selectByPrimaryKey(companyView.getUserId());
                    companyVo = new CompanyVo();
                    companyVo.setContact(user.getNickName());
                    companyVo.setCompanyName(companyView.getCompanyName());
                    companyVo.setId(companyView.getId());

                    RecruitmentExample recruitmentExample = new RecruitmentExample();
                    recruitmentExample.or().andCompanyIdEqualTo(companyView.getId()).andForbiddenEqualTo(false);
                    companyVo.setNowIntership(recruitmentService.countByExample(recruitmentExample));

                    RecruitmentExample irecruitmentExample = new RecruitmentExample();
                    irecruitmentExample.or().andCompanyIdEqualTo(companyView.getId());
                    companyVo.setAllIntership(recruitmentService.countByExample(irecruitmentExample));
                    companyVo.setForbidden(companyView.getForbidden());
                    StudentExample studentExample = new StudentExample();
                    studentExample.or().andCompanyIdEqualTo(companyView.getId());
                    companyVo.setStudentNumber(studentService.countByExample(studentExample));
                    companyVoList.add(companyVo);
                    if (user == null) {
                        result.put("code", Constant.FAIL);
                        result.put("msg", "无法找到Company表userID=" + companyView.getUserId() + "对应的user！");
                        return result;
                    }


                }
                result.put("code", Constant.OK);
                result.put("msg", "返回公司信息成功！");
                result.put("compamyViewList", companyVoList);
            }
       }else if(number.equals("20~50人")){
            List<CompanyView> companyViews=companyViewService.selectByExample(CompanyViewExample);
            CompanyVo companyVo;
            List<CompanyVo> companyVoList=new ArrayList<>();
            if(companyViews == null){
                result.put("code", Constant.FAIL);
                result.put("msg", "无法从Company表里查到记录！");
                return result;
            }
            for(CompanyView companyView:companyViews) {
                RecruitmentExample recruitmentExample1 = new RecruitmentExample();
                recruitmentExample1.or().andCompanyIdEqualTo(companyView.getId());
                List<Recruitment> recruitmentList = recruitmentService.selectByExample(recruitmentExample1);
                Integer sum =0;
                for (Recruitment recruitment : recruitmentList) {
                    sum = sum + recruitment.getCurrentNumber();
                }
                if (20<sum&&sum<50) {
                    User user = userService.selectByPrimaryKey(companyView.getUserId());
                    companyVo = new CompanyVo();
                    companyVo.setContact(user.getNickName());
                    companyVo.setCompanyName(companyView.getCompanyName());
                    companyVo.setId(companyView.getId());

                    RecruitmentExample recruitmentExample = new RecruitmentExample();
                    recruitmentExample.or().andCompanyIdEqualTo(companyView.getId()).andForbiddenEqualTo(false);
                    companyVo.setNowIntership(recruitmentService.countByExample(recruitmentExample));

                    RecruitmentExample irecruitmentExample = new RecruitmentExample();
                    irecruitmentExample.or().andCompanyIdEqualTo(companyView.getId());
                    companyVo.setAllIntership(recruitmentService.countByExample(irecruitmentExample));
                    companyVo.setForbidden(companyView.getForbidden());
                    StudentExample studentExample = new StudentExample();
                    studentExample.or().andCompanyIdEqualTo(companyView.getId());
                    companyVo.setStudentNumber(studentService.countByExample(studentExample));
                    companyVoList.add(companyVo);
                    if (user == null) {
                        result.put("code", Constant.FAIL);
                        result.put("msg", "无法找到Company表userID=" + companyView.getUserId() + "对应的user！");
                        return result;
                    }


                }
                result.put("code", Constant.OK);
                result.put("msg", "返回公司信息成功！");
                result.put("compamyViewList", companyVoList);
            }
       }else if(number.equals("大于50人")){
            List<CompanyView> companyViews=companyViewService.selectByExample(CompanyViewExample);
            CompanyVo companyVo;
            List<CompanyVo> companyVoList=new ArrayList<>();
            if(companyViews == null){
                result.put("code", Constant.FAIL);
                result.put("msg", "无法从Company表里查到记录！");
                return result;
            }
            for(CompanyView companyView:companyViews) {
                RecruitmentExample recruitmentExample1 = new RecruitmentExample();
                recruitmentExample1.or().andCompanyIdEqualTo(companyView.getId());
                List<Recruitment> recruitmentList = recruitmentService.selectByExample(recruitmentExample1);
                Integer sum =0;
                for (Recruitment recruitment : recruitmentList) {
                    sum = sum + recruitment.getCurrentNumber();
                }
                if (sum>50) {
                    User user = userService.selectByPrimaryKey(companyView.getUserId());
                    companyVo = new CompanyVo();
                    companyVo.setContact(user.getNickName());
                    companyVo.setCompanyName(companyView.getCompanyName());
                    companyVo.setId(companyView.getId());

                    RecruitmentExample recruitmentExample = new RecruitmentExample();
                    recruitmentExample.or().andCompanyIdEqualTo(companyView.getId()).andForbiddenEqualTo(false);
                    companyVo.setNowIntership(recruitmentService.countByExample(recruitmentExample));

                    RecruitmentExample irecruitmentExample = new RecruitmentExample();
                    irecruitmentExample.or().andCompanyIdEqualTo(companyView.getId());
                    companyVo.setAllIntership(recruitmentService.countByExample(irecruitmentExample));
                    companyVo.setForbidden(companyView.getForbidden());
                    StudentExample studentExample = new StudentExample();
                    studentExample.or().andCompanyIdEqualTo(companyView.getId());
                    companyVo.setStudentNumber(studentService.countByExample(studentExample));
                    companyVoList.add(companyVo);
                    if (user == null) {
                        result.put("code", Constant.FAIL);
                        result.put("msg", "无法找到Company表userID=" + companyView.getUserId() + "对应的user！");
                        return result;
                    }
                }
                result.put("code", Constant.OK);
                result.put("msg", "返回公司信息成功！");
                result.put("compamyViewList", companyVoList);
            }
       }
        return result;
    }

    @RequestMapping(value ="showCompanyRegisterApplyList",method=RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> showCompanyRegisterApplyList(@RequestParam("type") String type) {
/*
* @param
* 查看企业注册信息
* @return
* @author hanfeng
* */


        Map<String, Object> result = new HashMap<String, Object>();
        CompanyViewExample CompanyViewExample=new CompanyViewExample();
        if(type.equals("企业类型")){
            CompanyViewExample.or().andCheckedEqualTo(false);
        }else {
            CompanyViewExample.or().andCheckedEqualTo(false).andTypeEqualTo(type);
        }


        List<CompanyView> companyViews=companyViewService.selectByExample(CompanyViewExample);

        if(companyViews == null){
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Company表里查到记录！");
            return result;
        }
        for(CompanyView companyView:companyViews){
            User user = userService.selectByPrimaryKey(companyView.getUserId());
            if (user == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "无法找到Company表userID=" + companyView.getUserId() + "对应的user！");
                return result;
            }
        }
        result.put("code", Constant.OK);
        result.put("msg", "返回公司信息成功！");
        result.put("compamyViewList",companyViews);
        return result;
    }
    @RequestMapping(value ="showCompanyDetails",method=RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> showCompanyDetails(@RequestParam("id") String iid) {
/*
* @param
* 查看企业详情
* @return
* @author hanfeng
* */
        Integer id = Integer.valueOf(iid);

        Map<String, Object> result = new HashMap<String, Object>();

        CompanyViewExample CompanyViewExample=new CompanyViewExample();
        CompanyViewExample.or().andIdEqualTo(id);

        List<CompanyView> companyViews=companyViewService.selectByExample(CompanyViewExample);

        if(companyViews == null){
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从companyView表里查到记录！");
            return result;
        }
        for(CompanyView companyView:companyViews){

            CompanyImageExample CompanyImageExample=new CompanyImageExample();
            CompanyImageExample.or().andCompanyIdEqualTo(companyView.getId());
            List<CompanyImage> CompanyImages=companyImageService.selectByExample(CompanyImageExample);
            result.put("Image",CompanyImages);

            CompanyMarkExample CompanyMarkExample=new CompanyMarkExample();
            CompanyMarkExample.or().andCompanyIdEqualTo(companyView.getId());
            List<CompanyMark> CompanyMarks=companyMarkService.selectByExample(CompanyMarkExample);
            result.put("Mark",CompanyMarks);


            User user = userService.selectByPrimaryKey(companyView.getUserId());
            if (user == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "无法找到companyView表userID=" + companyView.getUserId() + "对应的user！");
                return result;
            }
        }
        result.put("code", Constant.OK);
        result.put("msg", "返回公司信息成功！");
        result.put("compamyViewList",companyViews);
        return result;
    }

    @RequestMapping(value ="showDynamicNewsApplyList",method=RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> showDynamicNewsApplyList() {
/*
* @param
* 查看首页动态申请列表
* @return
* @author hanfeng
* */
        FrontDynadic frontDynadic;
        Map<String, Object> result = new HashMap<String, Object>();

        DynamicApproveExample dynamicApproveExample=new DynamicApproveExample();
        dynamicApproveExample.or().andDopassingEqualTo(false).andDeleteTagEqualTo(true);
        List<DynamicApprove> dynamicApproves=dynamicApproveService.selectByExample(dynamicApproveExample);

        if(dynamicApproves == null){
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从DynamicApprove表里查到记录！");
            return result;
        }else {
            for (DynamicApprove dynamicApprove : dynamicApproves) {

                User user = userService.selectByPrimaryKey(dynamicApprove.getCompanyId());

                if (user == null) {
                    result.put("code", Constant.FAIL);
                    result.put("msg", "无法找到DynamicApprove表userID=" + dynamicApprove.getCompanyId() + "对应的user！");
                    return result;
                }

            }
            result.put("code", Constant.OK);
            result.put("msg", "返回动态信息成功！");
            result.put("dynamicNewApplyList", dynamicApproves);
        }
        return result;

    }
    @RequestMapping(value ="showDynamicNewsList",method=RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> showDynamicNewsList() {
/*
* @param
* 查看首页动态列表
* @return
* @author hanfeng
* */
        Map<String, Object> result = new HashMap<String, Object>();

        DynamicApproveExample dynamicApproveExample=new DynamicApproveExample();
        dynamicApproveExample.or().andPassingEqualTo(true).andDeleteTagEqualTo(true);


        List<DynamicApprove> dynamicApproves=dynamicApproveService.selectByExample(dynamicApproveExample);

        if(dynamicApproves == null){
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从DynamicApprove表里查到记录！");
            return result;
        }else {
            for (DynamicApprove dynamicApprove : dynamicApproves) {
                User user = userService.selectByPrimaryKey(dynamicApprove.getCompanyId());
                if (user == null) {
                    result.put("code", Constant.FAIL);
                    result.put("msg", "无法找到dynamicApprove表userID=" + dynamicApprove.getCompanyId() + "对应的user！");
                    return result;
                }

            }
            result.put("code", Constant.OK);
            result.put("msg", "返回动态信息成功！");
            result.put("dynamicApproves", dynamicApproves);
        }
        return result;
    }
    @RequestMapping(value ="showDynamicNewsDetails",method=RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> showDynamicNewsDetails(@RequestParam("id") String iid) {
/*
* 查看动态详情
* @return
* @author hanfeng
* */
        Integer id = Integer.valueOf(iid);

        Map<String, Object> result = new HashMap<String, Object>();
        DynamicApproveExample dynamicApproveExample=new DynamicApproveExample();
        dynamicApproveExample.or().andIdEqualTo(id);
        DynamicApprove dynamicApprove=dynamicApproveService.selectByPrimaryKey(id);

        if(dynamicApprove == null){
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从DynamicApprove表里查到记录！");
            return result;
        }else {
                User user = userService.selectByPrimaryKey(dynamicApprove.getCompanyId());
                if (user == null) {
                    result.put("code", Constant.FAIL);
                    result.put("msg", "无法找到dynamicApprove表userID=" + dynamicApprove.getCompanyId() + "对应的user！");
                    return result;
            }
            result.put("code", Constant.OK);
            result.put("msg", "返回动态信息成功！");
            JSONObject obj=new JSONObject();
            obj.put("id",dynamicApprove.getId());
            obj.put("title",dynamicApprove.getTitle());
            obj.put("imageUrl",dynamicApprove.getImageUrl());
            obj.put("detail",dynamicApprove.getDetail());
            obj.put("nickName",user.getNickName());
            obj.put("phone",dynamicApprove.getPhone());
            obj.put("email",dynamicApprove.getEmail());
            obj.put("startTime",dynamicApprove.getStartTime());
            obj.put("showstatus",dynamicApprove.getShowStatus());
            result.put("dynamicApproves", obj);
        }
        return result;
    }

    @RequestMapping(value = "showCompanynames",method=RequestMethod.GET)
    public @ResponseBody
    /*
    * 获取所有注册企业名字
    * author hanfeng
    * */
    Map<String,Object> showCompanyNames(){
        Map<String, Object> result = new HashMap<String, Object>();
        CompanyExample companyExample=new CompanyExample();
        companyExample.or().andPassEqualTo(true).andDeleteTagEqualTo(true);
        List<Company> companies=companyService.selectByExample(companyExample);
        JSONArray objects = new JSONArray();
        for(Company company:companies){
            JSONObject obj = new JSONObject();
            obj.put("id",company.getId());
            obj.put("companyName",company.getCompanyName());
            objects.add(obj);
        }
        result.put("Names",objects);

        return result;
    }
    @RequestMapping(value = "showCompanyRecruitment", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showCompanyRecruitment(@RequestParam("id") String iid
    ) {


/*
* @param
* @return
* 查看企业的招聘岗位
* @author hanfeng
* */
        Integer id = Integer.valueOf(iid);

        Map<String, Object> result = new HashMap<String, Object>();

        CompanyExample companyExample=new CompanyExample();

        RecruitmentExample recruitmentExample = new RecruitmentExample();
        recruitmentExample.or().andCompanyIdEqualTo(id);
        List<Recruitment> recruitments = recruitmentService.selectByExample(recruitmentExample);

        if (recruitments == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Recruitment表里查到记录！");
            return result;
        }

        JSONArray objs=new JSONArray();
        for(Recruitment recruitment:recruitments){
            JSONObject object=new JSONObject();
            object.put("id",recruitment.getId());
            object.put("post",recruitment.getPost());
            object.put("totalNumber",recruitment.getTotalNumber());
            object.put("currentNumber",recruitment.getCurrentNumber());

            object.put("startTime",new SimpleDateFormat("yyyy/MM").format(recruitment.getReleaseTime()));
            object.put("endTime",new SimpleDateFormat("yyyy/MM").format(recruitment.getEndtime()));
            object.put("release_time",new SimpleDateFormat("yyyy/MM").format(recruitment.getReleaseTime()));
            object.put("address",recruitment.getAddress());
            Company company=companyService.selectByPrimaryKey(recruitment.getCompanyId());
            object.put("companyName",company.getCompanyName());
            object.put("forbidden",recruitment.getForbidden());
            objs.add(object);
        }
        Company company=companyService.selectByPrimaryKey(id);
        User user=userService.selectByPrimaryKey(company.getUserId());
        result.put("name",user.getNickName());
        result.put("recruitmentList",objs);
        result.put("code", Constant.OK);
        return result;
    }


    @RequestMapping(value = "showCompanyStudentName", method = RequestMethod.GET)
    public @ResponseBody
    /*查看公司名下学生
    * @author hanfeng
    * */
    Map<String, Object> showCompanyStudentName(@RequestParam("id") String iid

    ) {

        Map<String, Object> result = new HashMap<String, Object>();
        Integer id = Integer.valueOf(iid);

        StudentExample studentExample=new StudentExample();
        studentExample.or().andCompanyIdEqualTo(id);

        List<Student> students=studentService.selectByExample(studentExample);

        JSONArray objects=new JSONArray();
        System.out.println(students);
        for(Student student:students){
            JSONObject obj=new JSONObject();
            obj.put("id",student.getId());
            User user=userService.selectByPrimaryKey(student.getUserId());
            obj.put("nickName",user.getNickName());

            if (user == null) {
                result.put("code", Constant.FAIL);
//                result.put("msg", "无法找到Teacher表userID=" + id + "对应的user！");
                return result;
            }
            objects.add(obj);
        }
        result.put("Names",objects);
        return result;
    }
    @RequestMapping(value = "showCompanyStudent", method = RequestMethod.POST)
    public @ResponseBody
    /*查看公司名下学生
    * @author hanfeng*/
    Map<String, Object> showTeacherStudent(@RequestBody MVo mVo
    ) {
//        try {
//            major= new String(major.getBytes("iso8859-1"),"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        try {
//            clss= new String(clss.getBytes("iso8859-1"),"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        try {
//            status= new String(status.getBytes("iso8859-1"),"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        String major=mVo.getMajor();
        String clss=mVo.getClss();
        String iid=mVo.getId();
        String status=mVo.getStatus();
        Integer id = Integer.valueOf(iid);

        Map<String, Object> result = new HashMap<String, Object>();

        StudentExample studentExample=new StudentExample();

        StudentExample.Criteria criteria=studentExample.or().andCompanyIdEqualTo(id);

        if(major.equals("专业")){

        }else{
            criteria.andMajorEqualTo(major);
        }
        if(clss.equals("班级")){

        }else{
            criteria.andClssEqualTo(clss);
        }
        if(status.equals("实习状态")){

        } else{
            criteria.andStatusEqualTo(status);
        }
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
            obj.put("teacherId",teacher.getId());
            User user2=userService.selectByPrimaryKey(teacher.getId());
            obj.put("teacherName",user2.getNickName());
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
        result.put("students",objects);
        return result;
    }
    @RequestMapping(value = "showCompanyStudentScreening", method = RequestMethod.POST)
    public @ResponseBody
    /*查看老师名下学生的筛选条件
    * @author hanfeng
    * */
    Map<String, Object> showCompanyStudentScreening(@RequestBody MVo mVo
    ) {
        Map<String, Object> result = new HashMap<String, Object>();

        String major=mVo.getMajor();
        String clss=mVo.getClss();
        String iid=mVo.getId();
        String status=mVo.getStatus();

        Integer id = Integer.valueOf(iid);


        StudentExample studentExample1=new StudentExample();
        studentExample1.or().andCompanyIdEqualTo(id).andDeleteTagEqualTo(true);
        List<Student> students1=studentService.selectByExample(studentExample1);
        Set set1=new TreeSet();
        for(Student student:students1){
            set1.add(student.getMajor());
        }
        result.put("major",set1);
        StudentExample studentExample2=new StudentExample();
        StudentExample.Criteria criteria=studentExample2.or().andTeacherIdEqualTo(id).andDeleteTagEqualTo(true);
        if(major.equals("专业")){

        }else{
            criteria.andMajorEqualTo(major);
        }
        List<Student> students2=studentService.selectByExample(studentExample2);
        Set set2=new TreeSet();
        for(Student student:students2){
            set2.add(student.getClss());
        }
        result.put("clss",set2);

        return result;
    }

    }
