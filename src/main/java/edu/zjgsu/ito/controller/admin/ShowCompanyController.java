package edu.zjgsu.ito.controller.admin;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.CompanyVo;
import edu.zjgsu.ito.vo.FrontDynadic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.tools.doclint.Entity.and;


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
    public @ResponseBody Map<String,Object> showCompanies(
            /*@RequestParam("size") String size*/
    ) {

/*
* @param
* @return
* 查看已在系统内注册好的企业信息
* @author hanfeng
* */
        /*try {
            size= new String(size .getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
*/
        Map<String, Object> result = new HashMap<String, Object>();
        CompanyViewExample CompanyViewExample=new CompanyViewExample();

      /*  if(size.equals("企业名称")){*/
            CompanyViewExample.or().andPassEqualTo(true).andDeleteTagEqualTo(true);

       /* }else {
            CompanyViewExample.or().andPassEqualTo(true).andCompanyNameEqualTo(size);
        }*/

        List<CompanyView> companyViews=companyViewService.selectByExample(CompanyViewExample);
        CompanyVo companyVo;
        List<CompanyVo> companyVoList=new ArrayList<>();

        if(companyViews == null){
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Company表里查到记录！");
            return result;
        }
        System.out.println(companyViews);
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
        try {
            type= new String(type .getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(type);

        Map<String, Object> result = new HashMap<String, Object>();
        CompanyViewExample CompanyViewExample=new CompanyViewExample();
        if(type.equals("企业类型")){
            CompanyViewExample.or().andPassEqualTo(false);
        }else {
            CompanyViewExample.or().andPassEqualTo(false).andTypeEqualTo(type);
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
    Map<String,Object> showCompanyDetails(@RequestParam("id") Integer id) {
/*
* @param
* 查看企业详情
* @return
* @author hanfeng
* */

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
            result.put("dynamicApproves", dynamicApproves);
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
    Map<String,Object> showDynamicNewsDetails(@RequestParam("id") Integer id) {
/*
* 查看动态详情
* @return
* @author hanfeng
* */
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
            result.put("dynamicNewsDetails", obj);
        }
        return result;
    }

    @RequestMapping(value = "showCompanynames",method=RequestMethod.GET)
    public @ResponseBody
    /*
    * 获取所有注册企业名字
    * author hanfeng
    * */
    Map<String,Object> showCompanynames(){
        Map<String, Object> result = new HashMap<String, Object>();
        CompanyExample companyExample=new CompanyExample();
        companyExample.or().andPassEqualTo(true);
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
    Map<String, Object> showRecruitment(@RequestParam("id") Integer id
    ) {


/*
* @param
* @return
* 查看企业的招聘岗位
* @author hanfeng
* */

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
            object.put("startTime",new SimpleDateFormat("yyyy/MM").format(recruitment.getStarttime()));
            object.put("endTime",new SimpleDateFormat("yyyy/MM").format(recruitment.getEndtime()));
            object.put("release_time",new SimpleDateFormat("yyyy/MM").format(recruitment.getReleaseTime()));
            object.put("address",recruitment.getAddress());
            Company company=companyService.selectByPrimaryKey(recruitment.getCompanyId());
            object.put("companyName",company.getCompanyName());
            object.put("forbidden",recruitment.getForbidden());
            objs.add(object);
        }
        result.put("recruitmentList",objs);
        result.put("code", Constant.OK);
        return result;
    }
    @RequestMapping(value = "showCompanyStudent", method = RequestMethod.GET)
    public @ResponseBody
    /*查看公司名下学生
    * @author hanfeng
    * */
    Map<String, Object> showCompanyStudent(@RequestParam("id") Integer id,
                                           @RequestParam("major") String major,
                                           @RequestParam("clss") String clss,
                                           @RequestParam("status") String status
    ) {
        try {
            major= new String(major .getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            clss= new String(clss .getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            status= new String(status .getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Map<String, Object> result = new HashMap<String, Object>();

        StudentExample studentExample=new StudentExample();

        if(major.equals("专业")){
            studentExample.or().andCompanyIdEqualTo(id).andDeleteTagEqualTo(true);

        }else{
            studentExample.or().andMajorEqualTo(major);
        }
        if(clss.equals("班级")){
            studentExample.or();
        }else{
            studentExample.or().andClssEqualTo(clss);
        }
        if(status.equals("实习状态")){
            studentExample.or();
        }else{
            studentExample.or().andStatusEqualTo(status);
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
           User user2=userService.selectByPrimaryKey(teacher.getUserId());
            obj.put("teacherName",user2.getNickName());
            obj.put("major",student.getMajor());
            obj.put("clss",student.getClss());
            obj.put("forbidden",student.getForbidden());
            obj.put("companyId",student.getCompanyId());

            if (user == null) {
                result.put("code", Constant.FAIL);
//                result.put("msg", "无法找到Teacher表userID=" + id + "对应的user！");
                return result;
            }
            objects.add(obj);
        }
        result.put("students",objects);
        return result;
    }


    }
