package edu.zjgsu.ito.controller.admin;

import edu.zjgsu.ito.model.DynamicApprove;
import edu.zjgsu.ito.model.Recruitment;
import edu.zjgsu.ito.service.DynamicApproveService;
import edu.zjgsu.ito.service.RecruitmentService;
import edu.zjgsu.ito.vo.ApprovalVo;
import edu.zjgsu.ito.model.Company;
import edu.zjgsu.ito.service.CompanyService;
import edu.zjgsu.ito.service.CompanyViewService;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.RecruitmentDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "admin")
public class OperateCompanyController {

    @Autowired
    CompanyViewService companyViewService;
    @Autowired
    CompanyService companyService;
    @Autowired
    DynamicApproveService dynamicApproveService;
    @Autowired
    RecruitmentService recruitmentService;


    @RequestMapping(value = "comfirmRegister", method = RequestMethod.POST)
    public @ResponseBody
    /*
    * @author 詹韩峰
    * @企业注册审批
    * */
    Map<String,Object> comfirmRegister(@RequestBody ApprovalVo approvalVo) {

        Integer[] ids=approvalVo.getIds();
        Boolean passFlag=approvalVo.getPassFlag();
        int status;
        Map<String, Object> result = new HashMap<String, Object>();
        for(Integer id:ids) {
            Company companyBack = companyService.selectByPrimaryKey(id);
            if (companyBack == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "未查到id=" + id + "的记录！");
                return result;
            }
//        已经被check过
            companyBack.setChecked(true);
            if (passFlag.equals(false)) {
//            审批不通过
                companyBack.setPass(false);
            } else {
//            审批通过
                companyBack.setPass(true);
            }

//        更新数据库记录
            status = companyService.updateByPrimaryKey(companyBack);

            if (status > 0) {
                result.put("code", Constant.OK);
                result.put("msg", "审批成功！");
            } else {
                result.put("code", Constant.FAIL);
                result.put("msg", "审批失败！更新数据库失败");
                return result;
            }
        }
        return result;
    }

    @RequestMapping(value = "comfirmDynamicNews", method = RequestMethod.POST)
    public @ResponseBody
    /*
    * @author hanfeng
    * @首页动态审批
    * */
    Map<String,Object> comfirmDynamicNews(@RequestBody ApprovalVo approvalVo) {

        Integer[] ids=approvalVo.getIds();
        Boolean passFlag=approvalVo.getPassFlag();
        String meg=approvalVo.getMeg();

        int status;
        Map<String, Object> result = new HashMap<String, Object>();
        for(Integer id:ids) {
            DynamicApprove dynamicApproveOne=dynamicApproveService.selectByPrimaryKey(id);

            if (dynamicApproveOne == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "未查到id=" + id + "的记录！");
                return result;
            }
//        已经被check过
            dynamicApproveOne.setDopassing(true);
            if (passFlag.equals(false)) {
//            审批不通过
                dynamicApproveOne.setPassing(false);
            } else {
//            审批通过
                dynamicApproveOne.setPassing(true);
            }
            dynamicApproveOne.setWhy(meg);

//        更新数据库记录
            status=dynamicApproveService.updateByPrimaryKey(dynamicApproveOne);

            if (status > 0) {
                result.put("code", Constant.OK);
                result.put("msg", "审批成功！");
            } else {
                result.put("code", Constant.FAIL);
                result.put("msg", "审批失败！更新数据库失败");
                return result;
            }
        }
        return result;
    }

    @RequestMapping(value = "comfirmInternship", method = RequestMethod.POST)
    public @ResponseBody
    /*
    * @author hanfeng
    * @实习招聘审批
    * */
    Map<String,Object> comfirmInternship(@RequestBody ApprovalVo approvalVo) {
        Integer[] ids=approvalVo.getIds();
        Boolean passFlag=approvalVo.getPassFlag();
        String meg=approvalVo.getMeg();
        int status;
        Map<String, Object> result = new HashMap<String, Object>();
        for(Integer id:ids) {
            Recruitment recruitmentOne=recruitmentService.selectByPrimaryKey(id);
            if (recruitmentOne == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "未查到id=" + id + "的记录！");
                return result;
            }
//        已经被check过
            recruitmentOne.setChecked(true);
            if (passFlag.equals(false)) {
//            审批不通过
                recruitmentOne.setPass(false);
            } else {
//            审批通过
                recruitmentOne.setPass(true);
            }
            recruitmentOne.setWhy(meg);

//        更新数据库记录
            status=recruitmentService.updateByPrimaryKey(recruitmentOne);

            if (status > 0) {
                result.put("code", Constant.OK);
                result.put("msg", "审批成功！");
            } else {
                result.put("code", Constant.FAIL);
                result.put("msg", "审批失败！更新数据库失败");
                return result;
            }
        }
        return result;
    }
    @RequestMapping(value = "comfirmshow", method = RequestMethod.GET)
    public @ResponseBody
    /*
    * @author hanfeng
    * @首页动态展示
    * */

    Map<String,Object> comfirmshow(@RequestParam ("id") Integer id,
                                   @RequestParam("showStatus") String showStatus) {
        int status;
        Map<String, Object> result = new HashMap<String, Object>();

            DynamicApprove dynamicApproveOne=dynamicApproveService.selectByPrimaryKey(id);

            if (dynamicApproveOne == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "未查到id=" + id + "的记录！");
                return result;
            }
            if (showStatus.equals(0)) {
                dynamicApproveOne.setShowStatus(false);
                Date date=new Date();
                DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time=format.format(date);
                dynamicApproveOne.setEndTime(time);
            } else {
                dynamicApproveOne.setShowStatus(true);

                Date date=new Date();
                DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time=format.format(date);
                dynamicApproveOne.setStartTime(time);
            }

//        更新数据库记录
            status=dynamicApproveService.updateByPrimaryKey(dynamicApproveOne);

            if (status > 0) {
                result.put("code", Constant.OK);
                result.put("msg", "审批成功！");
            } else {
                result.put("code", Constant.FAIL);
                result.put("msg", "审批失败！更新数据库失败");
                return result;
            }
        return result;
    }
}
