package edu.zjgsu.ito.controller.company;

import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "company")
public class NewsController {
    @Autowired
    SimpleService simpleService;

    /*
     * @param
     * 查看首页轮播图
     * @return
     * @author hanfeng
     * */
    @GetMapping(value ="showNewsList")
    public Message showNewsList() {
        return simpleService.showNewsList() ;
    }




}