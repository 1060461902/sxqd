package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.model.Company;
import edu.zjgsu.ito.model.Student;
import edu.zjgsu.ito.model.Teacher;
import edu.zjgsu.ito.service.CommonService;
import edu.zjgsu.ito.service.CompanyService;
import edu.zjgsu.ito.service.StudentService;
import edu.zjgsu.ito.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService{

    @Autowired
    StudentService studentService;

    @Autowired
    CompanyService companyService;

    @Autowired
    TeacherService teacherService;

    //通过不同角色的主键id查到其user的userID
    public Integer role2user(Integer roleid, Integer id) {
        List<Integer> userId = new ArrayList<Integer>();

        switch (roleid) {
            case 2:
                Student student = studentService.selectByPrimaryKey(id);
                userId.add(student.getUserId());
                break;
            case 3:
                Teacher teacher = teacherService.selectByPrimaryKey(id);

                userId.add(teacher.getUserId());
                break;
            case 4:
                Company company = companyService.selectByPrimaryKey(id);
                userId.add(company.getUserId());
                break;
            default:
//                System.out.println(""\);
                break;
        }
        return userId.get(0);
    }
}
