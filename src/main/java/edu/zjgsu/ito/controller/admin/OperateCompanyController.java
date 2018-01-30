package edu.zjgsu.ito.controller.admin;

import edu.zjgsu.ito.model.DynamicApprove;
import edu.zjgsu.ito.model.DynamicApproveExample;
import edu.zjgsu.ito.model.Recruitment;
import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.vo.ApprovalVo;
import edu.zjgsu.ito.model.Company;
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
    SimpleService simpleService;
    @Autowired
    AdminComplexService adminComplexService;

    /*
     * @author 詹韩峰
     * @企业注册审批
     * */
    @PostMapping(value = "confirmRegister")
    public Message confirmRegister(@RequestBody ApprovalVo approvalVo) {
        return simpleService.confirmRegister(approvalVo);
    }

    /*
     * @author hanfeng
     * @首页动态审批
     * */
    @PostMapping(value = "confirmDynamicNews")
    public Message confirmDynamicNews(@RequestBody ApprovalVo approvalVo) {
        return simpleService.confirmDynamicNews(approvalVo);
    }

    /*
     * @author hanfeng
     * @实习招聘审批
     * */
    @PostMapping(value = "confirmInternship")
    public Message confirmInternship(@RequestBody ApprovalVo approvalVo) {
        return simpleService.confirmInternship(approvalVo);
    }

    /*
     * @author hanfeng
     * @首页动态展示
     * */
    @GetMapping(value = "confirmShow")
    public Message confirmShow(@RequestParam ("id") String iid, @RequestParam("showStatus") Integer showStatus) {
        return adminComplexService.confirmShow(iid, showStatus);
        }

    /*
     * @author hanfeng
     * @首页动态删除
     * */
    @PostMapping(value = "deleteShow")
    public Message deleteShow(@RequestBody IdVo idVo) {
        return simpleService.deleteShow(idVo);
    }

    /*
     * @author hanfeng
     * @企业删除
     * */
    @PostMapping(value = "deleteCompany")
    public Message deleteCompany(@RequestBody IdVo idVo) {
        return simpleService.deleteCompany(idVo);
    }
    /*
     * @author hanfeng
     * @招聘信息删除
     * */
    @PostMapping(value = "deleteRecruitment")
    public Message deleteRecruitment(@RequestBody IdVo idVo) {
        return simpleService.deleteRecruitment(idVo);
    }

    /*
     * @author hanfeng
     * 禁用公司
     * */
    @GetMapping(value = "forbiddenCompany")
    public Message forbiddenCompany(@RequestParam("id") String iid, @RequestParam("forbidden") boolean forbidden) {
        return simpleService.forbiddenCompany(iid, forbidden);
    }

}
