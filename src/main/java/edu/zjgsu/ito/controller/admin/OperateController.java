package edu.zjgsu.ito.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zjgsu.ito.dao.UserMapper;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.vo.IdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class OperateController {
    @Autowired
    SimpleService simpleService;
    @Autowired
    AdminComplexService adminComplexService;

    /**
     *归档
     * @param studentIds
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author sawei
     */
    @ResponseBody
    @RequestMapping(value = "archive", method = RequestMethod.POST )
    public Message archive(@RequestBody IdVo studentIds, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return adminComplexService.archive(studentIds.String2Integer(studentIds.getId()), request, response);
    }

    /**
     *导出学生成绩
     * @param studentIds
     * @param response
     * @return
     * @throws Exception
     * @author sawei
     */
    @ResponseBody
    @RequestMapping(value = "export2Excel", method = RequestMethod.POST )
    public Message export2Excel(@RequestBody IdVo studentIds, HttpServletResponse response) throws Exception {
        return adminComplexService.writeToExcel("Sheet1", studentIds.String2Integer(studentIds.getId()),response);
    }
    /**
     * 设置权重
     * @param weight
     * @return
     * @author sawei
     */
    @ResponseBody
    @RequestMapping(value = "weight", method = RequestMethod.POST)
    public Message setWeight(@RequestBody Weight weight) {
        return simpleService.setWeight(weight);
    }

    /**
     * 上传Excel，批量注册学生和老师
     * @param request
     * @param roleId
     * @return
     * @throws Exception
     * @author sawei
     */
    @ResponseBody
    @RequestMapping(value = "uploadExcel", method = {RequestMethod.GET, RequestMethod.POST})
    public Message uploadExcel(HttpServletRequest request, @RequestParam("roleId") Integer roleId) throws Exception {
        return adminComplexService.batchRegister(request, roleId);
    }

    /**
     *学生注册
     * @param temp
     * @return
     * @author sawei
     */
    @ResponseBody
    @RequestMapping(value = "studentRegister", method = RequestMethod.POST )
    public Message studentRegister(@RequestBody StudentRegisterView temp) {
        return simpleService.studentRegister(temp.getUserName(), temp.getNickName(), temp.getMajor(), temp.getClss(), temp.getGrade());
    }

    /**
     * 老师注册
     * @param temp
     * @return
     * @author sawei
     */
    @ResponseBody
    @RequestMapping(value = "teacherRegister", method = RequestMethod.POST )
    public Message teacherRegister(@RequestBody TeacherRegisterView temp) {
        return simpleService.teacherRegister(temp.getUserName(), temp.getNickName(), temp.getMajor());
    }


}
