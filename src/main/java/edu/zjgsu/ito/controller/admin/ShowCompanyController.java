package edu.zjgsu.ito.controller.admin;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.CompanyVo;
import edu.zjgsu.ito.vo.FrontDynadic;
import edu.zjgsu.ito.vo.MVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;



@RestController
@RequestMapping(value ="admin")
public class ShowCompanyController {
    //页大小
//    public static final int pageSize=2;
    @Autowired
    SimpleService simpleService;
    @Autowired
    AdminComplexService adminComplexService;

    /*
     * @param
     * @return
     * 查看已在系统内注册好的企业信息
     * @author hanfeng
     * */
    @GetMapping(value ="showCompanies")
    public Message showCompanies(@RequestParam("number") String number) {
        return adminComplexService.showCompanies(number);
    }
    /*
     * @param
     * 查看企业注册信息
     * @return
     * @author hanfeng
     * */
    @GetMapping(value ="showCompanyRegisterApplyList")
    public Message showCompanyRegisterApplyList(@RequestParam("type") String type) {
        return simpleService.showCompanyRegisterApplyList(type);
    }
    /*
     * @param
     * 查看企业详情
     * @return
     * @author hanfeng
     * */
    @GetMapping(value ="showCompanyDetails")
    public Message showCompanyDetails(@RequestParam("id") String iid) {
        return simpleService.showCompanyDetails(iid);
    }
    /*
     * @param
     * 查看首页动态申请列表
     * @return
     * @author hanfeng
     * */
    @GetMapping(value ="showDynamicNewsApplyList")
    public Message showDynamicNewsApplyList() {
        return simpleService.showDynamicNewsApplyList();
    }
    /*
     * @param
     * 查看首页动态列表
     * @return
     * @author hanfeng
     * */
    @GetMapping(value ="showDynamicNewsList")
    public Message showDynamicNewsList() {
        return simpleService.showDynamicNewsList();
    }
    /*
     * 查看动态详情
     * @return
     * @author hanfeng
     * */
    @GetMapping(value ="showDynamicNewsDetails")
    public Message showDynamicNewsDetails(@RequestParam("id") String iid) {
        return simpleService.showDynamicNewsDetails(iid);
    }
    /*
     * 获取所有注册企业名字
     * author hanfeng
     * */
    @GetMapping(value = "showCompanynames")
    public Message showCompanyNames(){
        return simpleService.showCompanyNames();
    }
    /*
     * @param
     * @return
     * 查看企业的招聘岗位
     * @author hanfeng
     * */
    @GetMapping(value = "showCompanyRecruitment")
    public Message showCompanyRecruitment(@RequestParam("id") String iid) {
        return simpleService.showCompanyRecruitment(iid);
    }

    /*查看公司名下学生
     * @author hanfeng
     * */
    @GetMapping(value = "showCompanyStudentName")
    public Message showCompanyStudentName(@RequestParam("id") String iid) {
        return simpleService.showCompanyStudentName(iid);
    }
    /*查看公司名下学生
     * @author hanfeng*/
    @PostMapping(value = "showCompanyStudent")
    public Message showCompanyStudent(@RequestBody MVo mVo) {
        return simpleService.showCompanyStudent(mVo);
    }
    /*查看老师名下学生的筛选条件
     * @author hanfeng
     * */
    @PostMapping(value = "showCompanyStudentScreening")
    public Message showCompanyStudentScreening(@RequestBody MVo mVo) {
        return simpleService.showCompanyStudentScreening(mVo);
    }
}
