package edu.zjgsu.ito.controller.admin;

import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.*;
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


    @RequestMapping(value ="showCompanies",method=RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> showCompanies(@RequestParam("type") String type) {
/** @param
* @return
* 查看已在系统内注册好的企业信息
* @author hanfeng
**/

        Map<String, Object> result = new HashMap<String, Object>();
        CompanyViewExample CompanyViewExample=new CompanyViewExample();
        CompanyViewExample CompanyExampleView;
        if(type.equals("企业类型")){
            CompanyViewExample.or().andPassEqualTo(true);

        }else {
            CompanyViewExample.or().andPassEqualTo(true).andTypeEqualTo(type);
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

    @RequestMapping(value ="showCompanyRegisterApplyList",method=RequestMethod.GET)
    public @ResponseBody    Map<String,Object> showCompanyRegisterApplyList() {

/** @param
* 查看企业注册信息
* @return
* @author hanfeng
**/
        Map<String, Object> result = new HashMap<String, Object>();
        CompanyViewExample CompanyViewExample=new CompanyViewExample();
        CompanyViewExample CompanyExampleView;

        CompanyViewExample.or().andCheckedEqualTo(false);



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
    Map<String,Object> showCompanyDetails(@RequestParam("id") String id) {

/*
* @param
* 查看公司详情
* @return
* @author hanfeng
*
*/

        Map<String, Object> result = new HashMap<String, Object>();

        CompanyViewExample CompanyViewExample=new CompanyViewExample();
        CompanyViewExample CompanyExampleView;

        CompanyViewExample.or().andIdEqualTo(id);



        List<CompanyView> companyViews=companyViewService.selectByExample(CompanyViewExample);

        if(companyViews == null){
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Company表里查到记录！");
            return result;
        }
        for(CompanyView companyView:companyViews){
            CompanyImageExample CompanyImageExample=new CompanyImageExample();
            CompanyImageExample.or().andCompanyIdEqualTo(companyView.getId());
            List<CompanyImage> CompanyImages=companyImageService.selectByExample(CompanyImageExample);
            result.put("Image",CompanyImages);
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
    @RequestMapping(value ="showRegisteCompanies",method=RequestMethod.GET)
    public @ResponseBody      Map<String,Object> showRegisterCompanies() {

/** @param
* @return
* 查看企业招聘岗位
* @author hanfeng
**/
        Map<String,Object> result = new HashMap<String, Object>();
        CompanyViewExample CompanyViewExample=new CompanyViewExample();
        CompanyViewExample CompanyExampleView;

        CompanyViewExample.or().andPassEqualTo(false).andCheckedEqualTo(false);



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

    }
