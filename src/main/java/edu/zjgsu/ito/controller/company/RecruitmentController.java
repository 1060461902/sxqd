package edu.zjgsu.ito.controller.company;

import com.alibaba.fastjson.JSONArray;
import edu.zjgsu.ito.model.Recruitment;
import edu.zjgsu.ito.model.RecruitmentExample;
import edu.zjgsu.ito.service.RecruitmentService;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.RecruitmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.objects.NativeString.substr;

@Controller
@RequestMapping(value = "company")
public class RecruitmentController {

    @Autowired
    RecruitmentService recruitmentService;


    @RequestMapping(value = "showRecruitmentsOne", method = RequestMethod.GET)
    /*
    * @author   hanfeng
    * 展示招聘中岗位
    * */
    public @ResponseBody
    Map<String, Object> showRecruitmentsOne(@RequestParam(value="companyId") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        RecruitmentExample recruitmentExample=new RecruitmentExample();
        recruitmentExample.or().andDeleteTagEqualTo(true).andRemoveEqualTo(false).andPassEqualTo(true).andCompanyIdEqualTo(id);
        List<Recruitment> recruitmentList=recruitmentService.selectByExample(recruitmentExample);
        if (recruitmentList == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Recruitments表里查到记录！");
            return result;
        }
        JSONArray recruitmentVoList=new JSONArray();
        for (Recruitment recruitment : recruitmentList) {
                RecruitmentVo recruitmentVo=new RecruitmentVo();
                recruitmentVo.setPost(recruitment.getPost());
                recruitmentVo.setAddress(recruitment.getAddress());
                recruitmentVo.setCurrentNumber(recruitment.getCurrentNumber());
                recruitmentVo.setTotalNumber(recruitment.getTotalNumber());
                recruitmentVo.setId(recruitment.getId());
                recruitmentVo.setReleaseTime(recruitment.getReleaseTime());
                recruitmentVoList.add(recruitmentVo);

        }
        result.put("code", Constant.OK);
        result.put("recruitmentVoList", recruitmentVoList);
        return result;
    }
    @RequestMapping(value = "showRecruitmentsTwo", method = RequestMethod.GET)
    /*
    * @author   hanfeng
    * 展示招聘中岗位
    * */
    public @ResponseBody
    Map<String, Object> showRecruitmentsTwo(@RequestParam(value="companyId") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        RecruitmentExample recruitmentExample=new RecruitmentExample();
        recruitmentExample.or().andDeleteTagEqualTo(true).andRemoveEqualTo(false).andPassEqualTo(true).andCompanyIdEqualTo(id);
        List<Recruitment> recruitmentList=recruitmentService.selectByExample(recruitmentExample);
        if (recruitmentList == null) {
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Recruitments表里查到记录！");
            return result;
        }
        JSONArray recruitmentVoList=new JSONArray();
        for (Recruitment recruitment : recruitmentList) {
            RecruitmentVo recruitmentVo=new RecruitmentVo();
            recruitmentVo.setPost(recruitment.getPost());
            recruitmentVo.setAddress(recruitment.getAddress());
            recruitmentVo.setCurrentNumber(recruitment.getCurrentNumber());
            recruitmentVo.setTotalNumber(recruitment.getTotalNumber());
            recruitmentVo.setId(recruitment.getId());
            recruitmentVo.setReleaseTime(recruitment.getReleaseTime());
            recruitmentVoList.add(recruitmentVo);

        }
        result.put("code", Constant.OK);
        result.put("recruitmentVoList", recruitmentVoList);
        return result;
    }


}
