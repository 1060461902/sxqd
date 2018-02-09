package edu.zjgsu.ito.controller.admin;

import edu.zjgsu.ito.dao.CompanyMapper;
import edu.zjgsu.ito.dao.RecruitmentMapper;
import edu.zjgsu.ito.dao.StudentMapper;
import edu.zjgsu.ito.dao.StudentRecruitmentMapper;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.rmi.MarshalException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "admin")
public class ViewController {
//    页大小

    @Autowired
    SimpleService simpleService;


    @GetMapping(value = "weight")
    public Message getWeight() {
        return simpleService.getWeight();
    }

    /**
     * author hanfeng
     **/
    @GetMapping(value = "index")
    public Message index(){
        return simpleService.index();
    }


}
