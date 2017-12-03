package edu.zjgsu.ito.controller.admin;

import edu.zjgsu.ito.model.CompanyView;
import edu.zjgsu.ito.model.Recruitment;
import edu.zjgsu.ito.model.RecruitmentExample;
import edu.zjgsu.ito.service.CompanyViewService;
import edu.zjgsu.ito.service.RecruitmentService;
import edu.zjgsu.ito.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
@Controller
@RequestMapping(value = "admin")
public class ShowRecruitmentController {

    @Autowired
    CompanyViewService companyViewService;
    @Autowired
    RecruitmentService recruitmentService;


    @RequestMapping(value ="showRecruitment",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> showRegisterCompanies() {
*/
/*
* @param
* @return
* 查看企业招聘岗位
* @author hanfeng
* *//*

        Map<String,Object> result = new HashMap<String, Object>();
        RecruitmentExample recruitmentExample=new RecruitmentExample();
        recruitmentExample.or().andCheckedEqualTo(true);

        List<Recruitment> Recruitments=recruitmentService.selectByExample(recruitmentExample);

        if(Recruitments == null){
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Company表里查到记录！");
            return result;
        }
        for(Recruitment recruitment:Recruitments){
            CompanyView companyView=companyViewService.selectByKey(recruitment.getCompanyId());
            if (companyView == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "无法找到Company表userID=" + recruitment.getUserId() + "对应的user！");
                return result;
            }
        }
        result.put("code", Constant.OK);
        result.put("msg", "返回公司信息成功！");
        result.put("compamyViewList",Recruitments);
        return result;
    }
}
*/
