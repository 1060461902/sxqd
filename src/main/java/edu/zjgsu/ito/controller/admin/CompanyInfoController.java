package edu.zjgsu.ito.controller.admin;

import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.CompanyService;
import edu.zjgsu.ito.service.StudentService;
import edu.zjgsu.ito.service.TeacherService;
import edu.zjgsu.ito.service.UserService;
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
public class CompanyInfoController {
    //页大小
//    public static final int pageSize=2;
    @Autowired
    CompanyService companyService;
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @RequestMapping(value ="showCompanies",method=RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> showCompanys() {
/*
* @param
* @return
* @author hanfeng
* */
        Map<String, Object> result = new HashMap<String, Object>();
        CompanyExample CompanyExample=new CompanyExample();
       // CompanyExample.or().andIdIsNotNull();


        List<Company> companys=companyService.selectByExample(CompanyExample);


        System.out.println(companys);
        if(companys == null){
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从Company表里查到记录！");
            return result;
        }
        for(Company company:companys){
            User user = userService.selectByPrimaryKey(company.getUserId());
            if (user == null) {
                result.put("code", Constant.FAIL);
                result.put("msg", "无法找到Teacher表userID=" + company.getUserId() + "对应的user！");
                return result;
            }

        }
        result.put("code", Constant.OK);
        result.put("msg", "返回公司信息成功！");
        result.put("companyList", companys);

        return result;
    }

}
