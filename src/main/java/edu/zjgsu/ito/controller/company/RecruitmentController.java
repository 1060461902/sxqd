package edu.zjgsu.ito.controller.company;

import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "company")
public class RecruitmentController {
    @Autowired
    SimpleService simpleService;

    /*
     * @author   hanfeng
     * 展示招聘中岗位
     * */
    @GetMapping(value = "showRecruitmentsOne")
    public Message showRecruitmentsOne(@RequestParam(value="companyId") Integer id) {
        return simpleService.showRecruitmentsOne(id);
    }

    /*
     * @author   hanfeng
     * 展示招聘中岗位
     * */
    @GetMapping(value = "showRecruitmentsTwo")
    public Message showRecruitmentsTwo(@RequestParam(value="companyId") Integer id) {
        return simpleService.showRecruitmentsTwo(id);
    }


}
