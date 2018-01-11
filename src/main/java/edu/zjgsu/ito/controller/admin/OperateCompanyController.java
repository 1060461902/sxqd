package edu.zjgsu.ito.controller.admin;

import edu.zjgsu.ito.model.DynamicApprove;
import edu.zjgsu.ito.model.DynamicApproveExample;
import edu.zjgsu.ito.model.Recruitment;
import edu.zjgsu.ito.service.DynamicApproveService;
import edu.zjgsu.ito.service.RecruitmentService;
import edu.zjgsu.ito.vo.ApprovalVo;
import edu.zjgsu.ito.model.Company;
import edu.zjgsu.ito.service.CompanyService;
import edu.zjgsu.ito.service.CompanyViewService;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.IdVo;
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


        Integer[] ids=approvalVo.getId();
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

        Integer[] ids=approvalVo.getId();
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
        Integer[] ids=approvalVo.getId();

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

    @RequestMapping(value = "comfirmShow", method = RequestMethod.GET)
    public @ResponseBody
    /*
    * @author hanfeng
    * @首页动态展示
    * */

    Map<String,Object> comfirmshow(@RequestParam ("id") String iid,
                                   @RequestParam("showStatus") Integer showStatus) {
        int status;
        Map<String, Object> result = new HashMap<String, Object>();
        Integer id = Integer.valueOf(iid);

        DynamicApprove dynamicApproveOne = dynamicApproveService.selectByPrimaryKey(id);

        if (dynamicApproveOne == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "未查到id=" + id + "的记录！");
            return result;
        }
        if (showStatus.equals(1)) {
            dynamicApproveOne.setShowStatus(false);
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(date);
            dynamicApproveOne.setEndTime(time);
        } else {
            dynamicApproveOne.setShowStatus(true);

            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(date);
            dynamicApproveOne.setStartTime(time);
        }

//        更新数据库记录
        DynamicApproveExample dynamicApproveExample1 = new DynamicApproveExample();
        dynamicApproveExample1.or().andDeleteTagEqualTo(true).andShowStatusEqualTo(true).andPassingEqualTo(true).andDopassingEqualTo(true);
        long count = dynamicApproveService.countByExample(dynamicApproveExample1);
        if (count == 6) {
            result.put("code", 250);
            result.put("msg", "操作失败，展示轮播图数量已达6张");
            return result;
        } else {
            status = dynamicApproveService.updateByPrimaryKey(dynamicApproveOne);

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


    @RequestMapping(value = "deleteshow", method = RequestMethod.POST)
    public @ResponseBody
    /*
    * @author hanfeng
    * @首页动态删除
    * */

    Map<String,Object> deleteshow(@RequestBody IdVo idVo) {
        System.out.println(idVo);
        String[] ids= idVo.getId();
        System.out.println(ids);
        int status;
        Map<String, Object> result = new HashMap<String, Object>();

        for (String iid : ids) {
            Integer id = Integer.valueOf(iid);

            DynamicApprove dynamicApproveOne = dynamicApproveService.selectByPrimaryKey(id);
            if (dynamicApproveOne == null) {

                result.put("code", Constant.FAIL);
                result.put("msg", "未查到id=" + id + "的记录！");
                return result;
            }
            dynamicApproveOne.setDeleteTag(false);
            status = dynamicApproveService.updateByPrimaryKey(dynamicApproveOne);
            if (status > 0) {
                result.put("code", Constant.OK);
                result.put("msg", "删除成功！");
            } else {
                result.put("code", Constant.FAIL);
                result.put("msg", "删除失败！更新数据库失败");
                return result;
            }
        }
        return result;

    }


    @RequestMapping(value = "deleteCompany", method = RequestMethod.POST)
    public @ResponseBody
    /*
    * @author hanfeng
    * @企业删除
    * */

    Map<String,Object> deleteCompany(@RequestBody IdVo idVo) {
        System.out.println(idVo);
        String[] ids= idVo.getId();
        int status;
        Map<String, Object> result = new HashMap<String, Object>();
        for (String iid : ids) {
            Integer id = Integer.valueOf(iid);

            Company companyOne = companyService.selectByPrimaryKey(id);
            if (companyOne == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "未查到id=" + id + "的记录！");
                return result;
            }
            companyOne.setDeleteTag(false);
            status = companyService.updateByPrimaryKey(companyOne);
            if (status > 0) {
                result.put("code", Constant.OK);
                result.put("msg", "删除成功！");
            } else {
                result.put("code", Constant.FAIL);
                result.put("msg", "删除失败！更新数据库失败");
                return result;
            }
        }
        return result;

    }
    @RequestMapping(value = "deleteRecruitment", method = RequestMethod.POST)
    public @ResponseBody
    /*
    * @author hanfeng
    * @招聘信息删除
    * */

    Map<String,Object> deleteRecruitment(@RequestBody IdVo idVo) {
        System.out.println(idVo);
        String[] ids= idVo.getId();
        int status;
        Map<String, Object> result = new HashMap<String, Object>();
        for (String iid : ids) {
            Integer id = Integer.valueOf(iid);

            Recruitment recruitmentOne = recruitmentService.selectByPrimaryKey(id);
            if (recruitmentOne == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "未查到id=" + id + "的记录！");
                return result;
            }
            recruitmentOne.setDeleteTag(false);
            status = recruitmentService.updateByPrimaryKey(recruitmentOne);
            if (status > 0) {
                result.put("code", Constant.OK);
                result.put("msg", "删除成功！");
            } else {
                result.put("code", Constant.FAIL);
                result.put("msg", "删除失败！更新数据库失败");
                return result;
            }
        }
        return result;

    }


    @RequestMapping(value = "forbiddenCompany", method = RequestMethod.GET)
    /*
    * @author hanfeng
    * 禁用公司
    * */
    public @ResponseBody
    Map<String, Object> forbiddenCompany(@RequestParam("id") String iid,
                                         @RequestParam("forbidden") boolean forbidden
    ) {
        Map<String, Object> result = new HashMap<String, Object>();
        int one;
        Integer id = Integer.valueOf(iid);

        Company company=companyService.selectByPrimaryKey(id);

        if(company ==null){
            result.put("code", Constant.FAIL);
            result.put("msg", "未查到id=" + id + "的记录！");
            return result;
        }
        company.setForbidden(forbidden);
        one=companyService.updateByPrimaryKey(company);
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
}
