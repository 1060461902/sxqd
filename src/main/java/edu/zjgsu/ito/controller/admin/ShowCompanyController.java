package edu.zjgsu.ito.controller.admin;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.FrontDynadic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    CompanyMarkService companyMarkService;
    @Autowired
    DynamicApproveService dynamicApproveService;

    @RequestMapping(value ="showCompanies",method=RequestMethod.GET)
    public @ResponseBody Map<String,Object> showCompanies(@RequestParam("name") String name) {

/*
* @param
* @return
* 查看已在系统内注册好的企业信息
* @author hanfeng
* */
        try {
            name= new String(name .getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Map<String, Object> result = new HashMap<String, Object>();
        CompanyViewExample CompanyViewExample=new CompanyViewExample();

        if(name.equals("企业名称")){
            CompanyViewExample.or().andPassEqualTo(true);

        }else {
            CompanyViewExample.or().andPassEqualTo(true).andCompanyNameEqualTo(name);
        }

        List<CompanyView> companyViews=companyViewService.selectByExample(CompanyViewExample);

        if(companyViews == null){
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Company表里查到记录！");
            return result;
        }
        for(CompanyView companyView:companyViews){
            User user = userService.selectByPrimaryKey(companyView.getUserId());
            companyView.setPassword(null);
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
        dynamicApproveExample.or().andDopassingEqualTo(false);
        List<DynamicApprove> dynamicApproves=dynamicApproveService.selectByExample(dynamicApproveExample);

        if(dynamicApproves == null){
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从DynamicApprove表里查到记录！");
            return result;
        }else {
            for (DynamicApprove dynamicApprove : dynamicApproves) {

                User user = userService.selectByPrimaryKey(dynamicApprove.getCompanyId());

//                frontDynadic.setName(user.);

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
            result.put("user",user);
            result.put("dynamicApproves", dynamicApprove);
        }
        return result;
    }

    @RequestMapping(value = "showCompanynames",method=RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> showCompanynames(){
        Map<String, Object> result = new HashMap<String, Object>();
        CompanyExample companyExample=new CompanyExample();
        companyExample.or().andPassEqualTo(true);
        List<Company> companies=companyService.selectByExample(companyExample);
        JSONArray objects = new JSONArray();
        for(Company company:companies){
            JSONObject obj = new JSONObject();
            obj.put("companyName",company.getCompanyName());
            objects.add(obj);
            result.put("obj",obj);
        }

        return result;
    }
    }
