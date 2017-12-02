package edu.zjgsu.ito.controller.admin;

import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.CompanyService;
import edu.zjgsu.ito.service.CompanyViewService;
import edu.zjgsu.ito.service.RecruitmentService;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.RecruitmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "showRecruitment", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> showRegisterCompanies() {

        RecruitmentVo recruitmentVo=null;
        List<RecruitmentVo> recruitmentVoList = new ArrayList<RecruitmentVo>();

/*
* @param
* @return
* 查看企业招聘申请
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

            recruitmentVo.setPost(recruitment.getPost());
            recruitmentVo.setAddress(recruitment.getAddress());
            recruitmentVo.setCompanyName(companyView.getCompanyName());
            recruitmentVo.setPostTime(recruitment.getPostTime());
            recruitmentVo.setCurrentNumber(recruitment.getCurrentNumber());
            recruitmentVo.setTotalNumber(recruitment.getTotalNumber());
            recruitmentVoList.add(recruitmentVo);
        }


        result.put("code", Constant.OK);
        result.put("msg", "返回公司信息成功！");
        result.put("RecruitmentList", recruitmentVoList);
        return result;
    }

    @RequestMapping(value = "showRecruitmentApplyList",method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> showRecruitmentApplyList(@RequestBody String companyName) {

/** @param
* @return
* 查看实习
* @author hanfeng
* */

            Map<String, Object> result = new HashMap<String, Object>();
            RecruitmentExample recruitmentExample = new RecruitmentExample();
            recruitmentExample.or().andCheckedEqualTo(true).andPassEqualTo(true).andRemoveEqualTo(false);

            RecruitmentVo recruitmentVo=null;
            List<RecruitmentVo> recruitmentVoList = new ArrayList<RecruitmentVo>();

            List<Recruitment> Recruitments;
            CompanyView companyView;

            if (companyName == null) {
                Recruitments = recruitmentService.selectByExample(recruitmentExample);
            }else {
                companyView=companyViewService.selectByName(companyName);
                String company_id=companyView.getId();
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
                recruitmentVoList.add(recruitmentVo);

                if (companyView == null) {
                    result.put("code", Constant.FAIL);
                    result.put("msg", "无法找到Company表userID=" + recruitment.getUserId() + "对应的user！");
                    return result;
                }

            }
            result.put("code", Constant.OK);
            result.put("msg", "返回公司信息成功！");
            result.put("recruitmentVoList", recruitmentVoList);
            return result;
        }
}

