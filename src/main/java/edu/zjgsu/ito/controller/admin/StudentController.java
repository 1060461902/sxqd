package edu.zjgsu.ito.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value ="admin")
public class StudentController {
    @Autowired
    SimpleService simpleService;

    /*
     * @param
     * 查看学生列表
     * @return
     * @author hanfeng
     **/
    @PostMapping(value = "showStudents")
    public Message showStudents(@RequestBody ScreeningVo screeningVo , @RequestParam(value="pageNum",defaultValue="1") Integer pageNum){
        return simpleService.showStudents(screeningVo, pageNum);

    }

    /*
     * author hanfeng
     * 禁用学生
     * */
    @GetMapping(value = "forbiddenStudent")
    public Message forbiddenStudent(@RequestParam("id") String iid, @RequestParam("forbidden") boolean forbidden) {
        return simpleService.forbiddenStudent(iid, forbidden);
    }

    /*
     * author hanfeng
     * 删除学生
     * */
    @PostMapping(value = "deleteStudent")
    public Message deleteStudent(@RequestBody IdVo idvo) {
        return simpleService.deleteStudent(idvo);
    }

    /*
     * @param
     * 查看学生简历
     * @return
     * @author hanfeng
     **/
    @GetMapping(value = "showStudentDetail")
    public Message showStudentDetail(@RequestParam("id")  Integer id){
        return simpleService.showStudentDetail(id);
    }

    @PostMapping(value = "showScreening")
    public Message showScreening(@RequestBody ScreeningVo screeningVo){
        return simpleService.showScreening(screeningVo);
    }

    /*
     * author hanfeng
     * 分配学生
     * */
    @PostMapping(value = "assignedStudent")
    public Message assignedStudent(@RequestBody AssignedStudent assignedStudent) {
        return simpleService.assignedStudent(assignedStudent);
    }
    /*
     * author hanfeng
     * 管理员修改学生
     * */
    @PostMapping(value = "updateStudent")
    public Message updateStudent(@RequestBody StudentBaseVo studentBaseVo) {
        return simpleService.updateStudent(studentBaseVo);
    }

    @GetMapping(value = "showRoad")
    public Message showRoad(@RequestParam("id") Integer id) {
        return simpleService.showRoad(id);
    }

}
