package edu.zjgsu.ito.controller.admin;

import edu.zjgsu.ito.vo.ApprovalVo;
import edu.zjgsu.ito.model.Company;
import edu.zjgsu.ito.service.CompanyService;
import edu.zjgsu.ito.service.CompanyViewService;
import edu.zjgsu.ito.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "admin")
public class OperateCompanyController {

    @Autowired
    CompanyViewService companyViewService;
    @Autowired
    CompanyService companyService;


    /**
     *
     * @param
     * @return code
     * 企业注册单个审批操作
     * @author hanfeng
     */
/*    @RequestMapping(value = "link",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> getArray(String[] ids, String passFlag){

        int status;

        System.out.println("进来了吗");
        System.out.println();
        System.out.println(ids+passFlag);

        Map<String, Object> result = new HashMap<String, Object>();

        for(String id:ids) {
            Company companyBack = companyService.selectByPrimaryKey(id);
            if (companyBack == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "未查到id=" + id + "的记录！");
                return result;
            }
//        已经被check过
            companyBack.setChecked(true);

            if (passFlag.equals("0")) {
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
    }*/
    @RequestMapping(value = "comfirmRegister", method = RequestMethod.POST)
    public @ResponseBody
    Map<String,Object> comfirmRegister(@RequestBody ApprovalVo approvalVo) {

        String[] ids=approvalVo.getIds();
        Boolean passFlag=approvalVo.getPassFlag();


        int status;



        Map<String, Object> result = new HashMap<String, Object>();

        for(String id:ids) {
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

}
