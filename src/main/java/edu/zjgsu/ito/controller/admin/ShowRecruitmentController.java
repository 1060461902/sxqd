package edu.zjgsu.ito.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static jdk.nashorn.internal.objects.NativeString.substr;

@Controller
@RequestMapping(value = "admin")
public class ShowRecruitmentController {

    @Autowired
    SimpleService simpleService;
    @Autowired
    AdminComplexService adminComplexService;
    /*
     * @param
     * @return
     * 查看招聘信息列表
     * @author hanfeng
     * */
    @PostMapping(value = "showRecruitment")
    public Message showRecruitment(@RequestBody ShipVo shipVo) {
        return simpleService.showRecruitment(shipVo);
    }
    /** @param
     * @return
     * 查看招聘申请
     * @author hanfeng
     * */
    @GetMapping(value = "showRecruitmentApplyList")
    public Message showRecruitmentApplyList(@RequestParam("companyId") String companyIdd) {
        return simpleService.showRecruitmentApplyList(companyIdd);
    }
    /** @param
     * @return
     * 查看实习岗位下的学生名字
     * @author hanfeng
     * */
    @GetMapping(value = "showRecruitmentStudent")
    public Message showRecruitmentStudent(@RequestParam("id") String iid) {
        return simpleService.showRecruitmentStudent(iid);
    }
    /*
     * author hanfeng
     * 禁用实习
     * */
    @GetMapping(value = "forbiddenRecruitment")
    public Message forbiddenRecruitment(@RequestParam("id") String iid, @RequestParam("forbidden") boolean forbidden) {
        return simpleService.forbiddenRecruitment(iid,forbidden);
    }
    /*h获取实习的筛选条件
     * */
    @PostMapping(value = "showRecruitmentScreening")
    public Message showRecruitmentScreening(@RequestBody ScreenTwo screenTwo){
        return simpleService.showRecruitmentScreening(screenTwo);
    }
    /*
     * author hanfeng
     * 查看实习列表
     * */
    @PostMapping(value = "showInternship")
    public Message showInternships(@RequestBody ScreenTwo screenOne) {
        return simpleService.showInternships(screenOne);
    }
    /** @param
     * @return
     * 查看考勤信息
     * @author hanfeng
     * */
    @GetMapping(value = "showCheck")
    public Message showCheck(@RequestParam("id") String iid, @RequestParam("month") String month) {
        return simpleService.showCheck(iid, month);
    }
    /** @param
     * @return
     * 查看考勤的筛选条件
     * @author hanfeng
     * */
    @GetMapping(value = "showCheckScreening")
    public Message showCheckScreening(@RequestParam("id") String iid) {
        return simpleService.showCheckScreening(iid);
    }
    /** @param
     * @return
     * 查看周报列表
     * @author hanfeng
     * */
    @GetMapping(value = "showWeeklyList")
    public Message showWeeklyList(@RequestParam("id") String iid) {
        return simpleService.showWeeklyList(iid);
    }
    /** @param
     * @return
     * 查看周报详情
     * @author hanfeng
     * */
    @GetMapping(value = "showWeeklyDetail")
    public Message showWeeklyDetail(@RequestParam("id") String iid) {
        return simpleService.showWeeklyDetail(iid);
    }
    /** @param
     * @return
     * 查看小结详情
     * @author hanfeng
     * */
    @GetMapping(value = "showSummary")
    public Message showSummary(@RequestParam("id") String iid) {
        return simpleService.showSummary(iid);
    }
    /** @param
     * @return
     * 查看报告详情
     * @author hanfeng
     * */
    @GetMapping(value = "showSummaryDetail")
    public Message showSummaryDetail(@RequestParam("id") String iid) {
        return simpleService.showSummaryDetail(iid);
    }
    /** @param
     * @return
     * 查看实习成绩详情
     * @author hanfeng
     * */
    @GetMapping(value = "showScore")
    public Message showScore(@RequestParam("id") String iid) {
        return simpleService.showScore(iid);
    }
    /*
     * @param
     * @return
     * 查看某个企业招聘信息
     * @author hanfeng
     * */
    @PostMapping(value = "showCompanyRecruitments")
    public Message showCompanyRecruitments(@RequestBody CVo cVo) {
        return simpleService.showCompanyRecruitments(cVo);
    }
    /** @param
     * @return
     * 查看某个公司招聘岗位的筛选条件
     * @author hanfeng
     * */
    @GetMapping(value = "showRecruitmentScreen")
    public Message showRecruitmentScreen(@RequestParam("id") Integer id) {
        return simpleService.showRecruitmentScreen(id);
    }
    /*
     * @param
     * @return
     * 查看企业招聘详情
     * @author hanfeng
     * */
    @GetMapping(value = "showRecruitmentDetails")
    public Message showRecruitmentDetails(@RequestParam("id") Integer id) {
        return simpleService.showRecruitmentDetails(id);
    }
}

