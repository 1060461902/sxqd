package edu.zjgsu.ito.controller.company;

import edu.zjgsu.ito.model.DynamicApprove;
import edu.zjgsu.ito.model.DynamicApproveExample;
import edu.zjgsu.ito.model.User;
import edu.zjgsu.ito.service.DynamicApproveService;
import edu.zjgsu.ito.service.UserService;
import edu.zjgsu.ito.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "company")
public class NewsController {
    @Autowired
    DynamicApproveService dynamicApproveService;
    @Autowired
    UserService userService;

    @RequestMapping(value ="showDynamicNewsList",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> showDynamicNewsList() {
/*
* @param
* 查看首页轮播图
* @return
* @author hanfeng
* */
        Map<String, Object> result = new HashMap<String, Object>();

        DynamicApproveExample dynamicApproveExample=new DynamicApproveExample();
        dynamicApproveExample.or().andPassingEqualTo(true).andDeleteTagEqualTo(true).andShowStatusEqualTo(true);

        List<DynamicApprove> dynamicApproves=dynamicApproveService.selectByExample(dynamicApproveExample);

        if(dynamicApproves == null){
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从DynamicApprove表里查到记录！");
            return result;
        }else {
            for (DynamicApprove dynamicApprove : dynamicApproves) {
                User user = userService.selectByPrimaryKey(dynamicApprove.getCompanyId());
                if (user == null) {
                    result.put("code", Constant.FAIL);
                    result.put("msg", "无法找到dynamicApprove表userID=" + dynamicApprove.getCompanyId() + "对应的user！");
                    return result;
                }

            }
            result.put("code", Constant.OK);
            result.put("msg", "返回动态信息成功！");
            result.put("dynamicApproves", dynamicApproves);
        }
        return result;
    }
    @RequestMapping(value ="showNewsList",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> showNewsList() {
/*
* @param
* 查看首页轮播图
* @return
* @author hanfeng
* */
        Map<String, Object> result = new HashMap<String, Object>();

        DynamicApproveExample dynamicApproveExample=new DynamicApproveExample();

        dynamicApproveExample.setOrderByClause("id");
        dynamicApproveExample.setLimit(10);

        dynamicApproveExample.or().andPassingEqualTo(true).andDeleteTagEqualTo(true);

        List<DynamicApprove> dynamicApproves=dynamicApproveService.selectByExample(dynamicApproveExample);

        if(dynamicApproves == null){
            result.put("code", Constant.FAIL);
            result.put("msg", "无法从DynamicApprove表里查到记录！");
            return result;
        }else {
            for (DynamicApprove dynamicApprove : dynamicApproves) {
                User user = userService.selectByPrimaryKey(dynamicApprove.getCompanyId());
                if (user == null) {
                    result.put("code", Constant.FAIL);
                    result.put("msg", "无法找到dynamicApprove表userID=" + dynamicApprove.getCompanyId() + "对应的user！");
                    return result;
                }

            }
            result.put("code", Constant.OK);
            result.put("msg", "返回动态信息成功！");
            result.put("dynamicApproves", dynamicApproves);
        }
        return result;
    }




}