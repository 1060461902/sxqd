package edu.zjgsu.ito.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.dao.*;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.service.SimpleService;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.utils.Md5Util;
import edu.zjgsu.ito.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

import static jdk.nashorn.internal.objects.NativeString.substr;

@Service
public class SimpleServiceImpl implements SimpleService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    WeightMapper weightMapper;
    @Autowired
    DynamicApproveMapper dynamicApproveMapper;
    @Autowired
    RecruitmentMapper recruitmentMapper;
    @Autowired
    CompanyViewMapper companyViewMapper;
    @Autowired
    CompanyImageMapper companyImageMapper;
    @Autowired
    CompanyMarkMapper companyMarkMapper;
    @Autowired
    StudentRecruitmentMapper studentRecruitmentMapper;
    @Autowired
    StudentRecruitmentViewMapper studentRecruitmentViewMapper;
    @Autowired
    ScoreMapper scoreMapper;
    @Autowired
    DailyCheckMapper dailyCheckMapper;
    @Autowired
    ReportMapper reportMapper;
    @Autowired
    ImageMapper imageMapper;
    @Autowired
    SummaryMapper summaryMapper;
    /**
     * 重置密码
     * @param roleId
     * @param id
     * @return
     * @author sawei
     */
    @Override
    public Message resetPwd(Integer roleId, Integer id) {
        Integer userId;
        String md5Password;

        md5Password = Constant.DEFAULTPWD;

        userId = role2user(roleId, id);
//                修改密码
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(md5Password);
        userMapper.updateByPrimaryKey(user);

        return Message.createSuc(null);
    }

    /**
     *更新权重
     * @param weight
     * @return
     * @author sawei
     */
    @Override
    public Message setWeight(Weight weight) {
        WeightExample weightExample = new WeightExample();
        weightExample.or().andIdEqualTo(1);
        weightMapper.updateByExample(weight, weightExample);

        return Message.createSuc(null);
    }


    /**
     * 通过不同角色的主键id查到其user的userID
     * @param roleId
     * @param id
     * @return
     * @author sawei
     */
    public Integer role2user(Integer roleId, Integer id) {
        List<Integer> userId = new ArrayList<Integer>();

        switch (roleId) {
            case 2:
                Student student = studentMapper.selectByPrimaryKey(id);
                userId.add(student.getUserId());
                break;
            case 3:
                Teacher teacher = teacherMapper.selectByPrimaryKey(id);
                userId.add(teacher.getUserId());
                break;
            case 4:
                Company company = companyMapper.selectByPrimaryKey(id);
                userId.add(company.getUserId());
                break;
            default:
                break;
        }
        return userId.get(0);
    }

    /**
     * 学生注册
     *
     * @param userName
     * @param nickName
     * @param major
     * @param clss
     * @return
     * @author sawei
     */
    @Override
    public Message studentRegister(String userName, String nickName, String major, String clss, String grade) {
        User user = new User();
        Student student = new Student();

        user.setUserName(userName);
        user.setNickName(nickName);
        user.setPassword(Constant.DEFAULTPWD);
        user.setRoleId(Constant.STUDENT);
        user.setDeleteTag(true);
        user.setCreateTime(new Date());

        student.setUserId(userRegister(user));
        student.setMajor(major);
        student.setClss(clss);
        student.setStatus("未实习");
        student.setDeleteTag(true);
        student.setForbidden(false);
        student.setGrade(grade);
        studentMapper.insert(student);

        return Message.createSuc(null);
    }

    /**
     * 老师注册
     *
     * @param userName
     * @param nickName
     * @param major
     * @return
     * @author sawei
     */
    @Override
    public Message teacherRegister(String userName, String nickName, String major) {
        User user = new User();
        Teacher teacher = new Teacher();

        user.setUserName(userName);
        user.setNickName(nickName);
        user.setPassword(Constant.DEFAULTPWD);
        user.setRoleId(Constant.TEACHER);
        user.setDeleteTag(true);
        user.setCreateTime(new Date());

        teacher.setUserId(userRegister(user));
        teacher.setMajor(major);
        teacher.setDeleteTag(true);
        teacher.setForbidden(false);
        teacherMapper.insert(teacher);

        return Message.createSuc(null);
    }

    /**
     * 添加一条user表记录，并返回最新记录的id
     * @param user
     * @return
     * @author sawei
     */
    public Integer userRegister(User user) {
        int userId;
        UserExample userExample = new UserExample();
        userMapper.insert(user);
        //                找到刚刚插入的那条记录
        userExample.setOrderByClause("id DESC");
        userExample.setLimit(1);

        userId = userMapper.selectByExample(userExample).get(0).getId();

        return userId;
    }


    /**
     * 企业注册
     * @param user
     * @return
     * @sawei
     */
    @Override
    public Message register(User user) {
        String md5Password;

        md5Password = Md5Util.getMD5(user.getPassword());
        user.setPassword(md5Password);
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(user.getUserName());
        userMapper.updateByExample(user, userExample);

        return Message.createSuc(null);
    }

    /**
     * 登录
     * @param request
     * @param user
     * @return
     * @author sawei
     */
    @Override
    public Message login(HttpServletRequest request, User user) {
//        变量声明
        String md5Password;
        HttpSession session = request.getSession();

//        建立example
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(user.getUserName()).andPasswordEqualTo(Md5Util.getMD5(user.getPassword()));
//        根据example查找
        List<User> userList = userMapper.selectByExample(userExample);
//        判断有无这个user
        if (userList.size() > 0) {
//            放到session里
            session.setAttribute("loginFlag", "1");
            return Message.createSuc(null);
        } else {
            return Message.createErr(null);
        }
    }

    /**
     *修改密码
     * @param frontUser
     * @return
     * @author sawei
     */
    @Override
    public Message modifyPwd(ResetPwdUser frontUser) {
        String md5NewPassword;
        String md5OldPassword;

//        先验证账号密码是否正确
        md5OldPassword = Md5Util.getMD5(frontUser.getOldPassword());
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(frontUser.getUserName()).andPasswordEqualTo(md5OldPassword);

        List<User> userList = userMapper.selectByExample(userExample);
        User backUser = userList.get(0);

//        修改密码
        md5NewPassword = Md5Util.getMD5(frontUser.getNewPassword());
        backUser.setPassword(md5NewPassword);

//        修改database中记录

         userMapper.updateByExample(backUser, userExample);

        return Message.createSuc(null);
    }

    /*
     * @author 詹韩峰
     * @企业注册审批
     * */
    @Override
    public Message confirmRegister(ApprovalVo approvalVo) {
        Integer[] ids=approvalVo.getId();
        Boolean passFlag=approvalVo.getPassFlag();

        for(Integer id:ids) {
            Company companyBack = companyMapper.selectByPrimaryKey(id);
            if (companyBack == null) {
                return Message.createErr("未查到id=" + id + "的记录！");
            }
//        已经被check过
            companyBack.setChecked(true);
            if (passFlag.equals(false)) {
//            审批不通过
                companyBack.setPass(false);
            } else {
//            审批通过
                companyBack.setPass(true);
            }

//        更新数据库记录
            companyMapper.updateByPrimaryKey(companyBack);
        }

        return Message.createSuc(null);

    }

    /*
     * @author hanfeng
     * @首页动态审批
     * */
    @Override
    public Message confirmDynamicNews(ApprovalVo approvalVo) {
        Integer[] ids=approvalVo.getId();
        Boolean passFlag=approvalVo.getPassFlag();
        String meg=approvalVo.getMeg();

        for(Integer id:ids) {

            DynamicApprove dynamicApproveOne=dynamicApproveMapper.selectByPrimaryKey(id);

            if (dynamicApproveOne == null) {
                return Message.createErr("未查到id=" + id + "的记录！");
            }
//        已经被check过
            dynamicApproveOne.setDopassing(true);
            if (passFlag.equals(false)) {
//            审批不通过
                dynamicApproveOne.setPassing(false);
            } else {
//            审批通过
                dynamicApproveOne.setPassing(true);
            }
            dynamicApproveOne.setWhy(meg);

//        更新数据库记录
            dynamicApproveMapper.updateByPrimaryKey(dynamicApproveOne);
        }
        return Message.createSuc(null);
    }

    /*
     * @author hanfeng
     * @实习招聘审批
     * */
    @Override
    public Message confirmInternship(ApprovalVo approvalVo) {
        Integer[] ids=approvalVo.getId();

        Boolean passFlag=approvalVo.getPassFlag();
        String meg=approvalVo.getMeg();
        for(Integer id:ids) {

            Recruitment recruitmentOne=recruitmentMapper.selectByPrimaryKey(id);
            if (recruitmentOne == null) {
                return Message.createErr("未查到id=" + id + "的记录！");
            }
//        已经被check过
            recruitmentOne.setChecked(true);
            if (passFlag.equals(false)) {
//            审批不通过
                recruitmentOne.setPass(false);
            } else {
//            审批通过
                recruitmentOne.setPass(true);
            }
            recruitmentOne.setWhy(meg);

//        更新数据库记录
            recruitmentMapper.updateByPrimaryKey(recruitmentOne);
        }
        return Message.createSuc(null);
    }
    /*
     * @author hanfeng
     * @首页动态删除
     * */
    @Override
    public Message deleteShow(IdVo idVo) {
        //        System.out.println(idVo);
        String[] ids= idVo.getId();
//        System.out.println(ids);
        for (String iid : ids) {
            Integer id = Integer.valueOf(iid);
            DynamicApprove dynamicApproveOne = dynamicApproveMapper.selectByPrimaryKey(id);
            if (dynamicApproveOne == null) {
                return Message.createErr("未查到id=" + id + "的记录！");
            }
            dynamicApproveOne.setDeleteTag(false);
            dynamicApproveMapper.updateByPrimaryKey(dynamicApproveOne);
        }
        return Message.createSuc(null);
    }
    /*
     * @author hanfeng
     * @企业删除
     * */
    @Override
    public Message deleteCompany(IdVo idVo) {
        //        System.out.println(idVo);
        String[] ids= idVo.getId();
        for (String iid : ids) {
            Integer id = Integer.valueOf(iid);

            Company companyOne = companyMapper.selectByPrimaryKey(id);
            if (companyOne == null) {
                return Message.createErr("未查到id=" + id + "的记录！");
            }
            companyOne.setDeleteTag(false);
            companyMapper.updateByPrimaryKey(companyOne);
        }
        return Message.createSuc(null);
    }
    /*
     * @author hanfeng
     * @招聘信息删除
     * */
    @Override
    public Message deleteRecruitment(IdVo idVo) {
        //        System.out.println(idVo);
        String[] ids= idVo.getId();
        for (String iid : ids) {
            Integer id = Integer.valueOf(iid);

            Recruitment recruitmentOne = recruitmentMapper.selectByPrimaryKey(id);
            if (recruitmentOne == null) {
                return Message.createErr("未查到id=" + id + "的记录！");
            }
            recruitmentOne.setDeleteTag(false);
            recruitmentMapper.updateByPrimaryKey(recruitmentOne);
        }
        return Message.createSuc(null);
    }
    /*
     * @author hanfeng
     * 禁用公司
     * */
    @Override
    public Message forbiddenCompany(String iid, boolean forbidden) {
        Integer id = Integer.valueOf(iid);

        Company company=companyMapper.selectByPrimaryKey(id);

        if(company ==null){
            return Message.createErr("未查到id=" + id + "的记录！");
        }
        company.setForbidden(forbidden);
        companyMapper.updateByPrimaryKey(company);
        return Message.createSuc(null);
    }

    /*
     * @param
     * 查看企业注册信息
     * @return
     * @author hanfeng
     * */
    @Override
    public Message showCompanyRegisterApplyList(String type) {
        Map<String, Object> result = new HashMap<String, Object>();

        CompanyViewExample CompanyViewExample = new CompanyViewExample();
        if(type.equals("企业类型")){
            CompanyViewExample.or().andCheckedEqualTo(false);
        }else {
            CompanyViewExample.or().andCheckedEqualTo(false).andTypeEqualTo(type);
        }

        List<CompanyView> companyViews=companyViewMapper.selectByExample(CompanyViewExample);

        if(companyViews == null){
            return Message.createErr("无法从Company表里查到记录！");
        }
        for(CompanyView companyView:companyViews){
            User user = userMapper.selectByPrimaryKey(companyView.getUserId());
            if (user == null) {
                return Message.createErr("无法找到Company表userID=" + companyView.getUserId() + "对应的user！");
            }
        }
        result.put("compamyViewList",companyViews);
        return Message.createSuc(result);
    }
    /*
     * @param
     * 查看企业详情
     * @return
     * @author hanfeng
     * */
    @Override
    public Message showCompanyDetails(String iid) {
        Integer id = Integer.valueOf(iid);

        Map<String, Object> result = new HashMap<String, Object>();

        CompanyViewExample CompanyViewExample=new CompanyViewExample();
        CompanyViewExample.or().andIdEqualTo(id);

        List<CompanyView> companyViews=companyViewMapper.selectByExample(CompanyViewExample);

        if(companyViews == null){
            return Message.createErr("无法从companyView表里查到记录！");
        }
        for(CompanyView companyView:companyViews){

            CompanyImageExample CompanyImageExample=new CompanyImageExample();
            CompanyImageExample.or().andCompanyIdEqualTo(companyView.getId());
            List<CompanyImage> CompanyImages=companyImageMapper.selectByExample(CompanyImageExample);
            result.put("Image",CompanyImages);

            CompanyMarkExample CompanyMarkExample=new CompanyMarkExample();
            CompanyMarkExample.or().andCompanyIdEqualTo(companyView.getId());
            List<CompanyMark> CompanyMarks=companyMarkMapper.selectByExample(CompanyMarkExample);
            result.put("Mark",CompanyMarks);


            User user = userMapper.selectByPrimaryKey(companyView.getUserId());
            if (user == null) {
                return Message.createErr("无法找到companyView表userID=" + companyView.getUserId() + "对应的user！");
            }
        }
        result.put("compamyViewList",companyViews);
        return Message.createSuc(result);
    }
    /*
     * @param
     * 查看首页动态申请列表
     * @return
     * @author hanfeng
     * */
    @Override
    public Message showDynamicNewsApplyList() {
        FrontDynadic frontDynadic;
        Map<String, Object> result = new HashMap<String, Object>();

        DynamicApproveExample dynamicApproveExample=new DynamicApproveExample();
        dynamicApproveExample.or().andDopassingEqualTo(false).andDeleteTagEqualTo(true);
        List<DynamicApprove> dynamicApproves=dynamicApproveMapper.selectByExample(dynamicApproveExample);

        if(dynamicApproves == null){
            return Message.createErr("无法从DynamicApprove表里查到记录！");
        }else {
            for (DynamicApprove dynamicApprove : dynamicApproves) {

                User user = userMapper.selectByPrimaryKey(dynamicApprove.getCompanyId());

                if (user == null) {
                    return Message.createErr("无法找到DynamicApprove表userID=" + dynamicApprove.getCompanyId() + "对应的user！");
                }

            }
        }

        result.put("dynamicNewApplyList", dynamicApproves);
        return Message.createSuc(dynamicApproves);
    }
    /*
     * @param
     * 查看首页动态列表
     * @return
     * @author hanfeng
     * */
    @Override
    public Message showDynamicNewsList() {
        Map<String, Object> result = new HashMap<String, Object>();

        DynamicApproveExample dynamicApproveExample=new DynamicApproveExample();
        dynamicApproveExample.or().andPassingEqualTo(true).andDeleteTagEqualTo(true);


        List<DynamicApprove> dynamicApproves=dynamicApproveMapper.selectByExample(dynamicApproveExample);

        if(dynamicApproves == null){
            return Message.createErr("无法从DynamicApprove表里查到记录！");
        }else {
            for (DynamicApprove dynamicApprove : dynamicApproves) {
                User user = userMapper.selectByPrimaryKey(dynamicApprove.getCompanyId());
                if (user == null) {
                    return Message.createErr("无法找到dynamicApprove表userID=" + dynamicApprove.getCompanyId() + "对应的user！");
                }

            }
        }

        result.put("dynamicApproves", dynamicApproves);
        return Message.createSuc(result);
    }
    /*
     * 获取所有注册企业名字
     * author hanfeng
     * */
    @Override
    public Message showCompanyNames() {
        Map<String, Object> result = new HashMap<String, Object>();

        CompanyExample companyExample=new CompanyExample();
        companyExample.or().andPassEqualTo(true).andDeleteTagEqualTo(true);
        List<Company> companies=companyMapper.selectByExample(companyExample);

        JSONArray objects = new JSONArray();
        for(Company company:companies){
            JSONObject obj = new JSONObject();
            obj.put("id",company.getId());
            obj.put("companyName",company.getCompanyName());
            objects.add(obj);
        }
        result.put("Names",objects);

        return Message.createSuc(result);
    }

    /*
     * 查看动态详情
     * @return
     * @author hanfeng
     * */
    @Override
    public Message showDynamicNewsDetails(String iid) {
        Integer id = Integer.valueOf(iid);

        Map<String, Object> result = new HashMap<String, Object>();
        DynamicApproveExample dynamicApproveExample=new DynamicApproveExample();
        dynamicApproveExample.or().andIdEqualTo(id);
        DynamicApprove dynamicApprove=dynamicApproveMapper.selectByPrimaryKey(id);

        if(dynamicApprove == null){
            return Message.createErr("无法从DynamicApprove表里查到记录！");
        }else {
            User user = userMapper.selectByPrimaryKey(dynamicApprove.getCompanyId());
            if (user == null) {
                return Message.createErr("无法找到dynamicApprove表userID=" + dynamicApprove.getCompanyId() + "对应的user！");
            }
            JSONObject obj=new JSONObject();
            obj.put("id",dynamicApprove.getId());
            obj.put("title",dynamicApprove.getTitle());
            obj.put("imageUrl",dynamicApprove.getImageUrl());
            obj.put("detail",dynamicApprove.getDetail());
            obj.put("nickName",user.getNickName());
            obj.put("phone",dynamicApprove.getPhone());
            obj.put("email",dynamicApprove.getEmail());
            obj.put("startTime",dynamicApprove.getStartTime());
            obj.put("showstatus",dynamicApprove.getShowStatus());
            result.put("dynamicApproves", obj);
        }
        return Message.createSuc(result);
    }
    /*
     * @param
     * @return
     * 查看企业的招聘岗位
     * @author hanfeng
     * */
    @Override
    public Message showCompanyRecruitment(String iid) {

        Integer id = Integer.valueOf(iid);

        Map<String, Object> result = new HashMap<String, Object>();

        CompanyExample companyExample=new CompanyExample();

        RecruitmentExample recruitmentExample = new RecruitmentExample();
        recruitmentExample.or().andCompanyIdEqualTo(id);
        List<Recruitment> recruitments = recruitmentMapper.selectByExample(recruitmentExample);

        if (recruitments == null) {
            return Message.createErr("无法从Recruitment表里查到记录！");
        }

        JSONArray objs=new JSONArray();
        for(Recruitment recruitment:recruitments){
            JSONObject object=new JSONObject();
            object.put("id",recruitment.getId());
            object.put("post",recruitment.getPost());
            object.put("totalNumber",recruitment.getTotalNumber());
            object.put("currentNumber",recruitment.getCurrentNumber());

            object.put("startTime",new SimpleDateFormat("yyyy/MM").format(recruitment.getReleaseTime()));
            object.put("endTime",new SimpleDateFormat("yyyy/MM").format(recruitment.getEndtime()));
            object.put("release_time",new SimpleDateFormat("yyyy/MM").format(recruitment.getReleaseTime()));
            object.put("address",recruitment.getAddress());
            Company company=companyMapper.selectByPrimaryKey(recruitment.getCompanyId());
            object.put("companyName",company.getCompanyName());
            object.put("forbidden",recruitment.getForbidden());
            objs.add(object);
        }
        Company company=companyMapper.selectByPrimaryKey(id);
        User user=userMapper.selectByPrimaryKey(company.getUserId());
        result.put("name",user.getNickName());
        result.put("recruitmentList",objs);

        return Message.createSuc(result);
    }
    /*查看公司名下学生
     * @author hanfeng
     * */
    @Override
    public Message showCompanyStudentName(String iid) {
        Map<String, Object> result = new HashMap<String, Object>();
        Integer id = Integer.valueOf(iid);

        StudentExample studentExample=new StudentExample();
        studentExample.or().andCompanyIdEqualTo(id);

        List<Student> students=studentMapper.selectByExample(studentExample);

        JSONArray objects=new JSONArray();
        System.out.println(students);
        for(Student student:students){
            JSONObject obj=new JSONObject();
            obj.put("id",student.getId());
            User user=userMapper.selectByPrimaryKey(student.getUserId());
            obj.put("nickName",user.getNickName());

            if (user == null) {
                return Message.createErr("无法找到Teacher表userID=" + id + "对应的user！");
            }
            objects.add(obj);
        }
        result.put("Names",objects);
        return Message.createSuc(result);
    }
    /*查看公司名下学生
     * @author hanfeng*/
    @Override
    public Message showCompanyStudent(MVo mVo) {
        String major=mVo.getMajor();
        String clss=mVo.getClss();
        String iid=mVo.getId();
        String status=mVo.getStatus();
        Integer id = Integer.valueOf(iid);

        Map<String, Object> result = new HashMap<String, Object>();

        StudentExample studentExample=new StudentExample();

        StudentExample.Criteria criteria=studentExample.or().andCompanyIdEqualTo(id);

        if(major.equals("专业")){

        }else{
            criteria.andMajorEqualTo(major);
        }
        if(clss.equals("班级")){

        }else{
            criteria.andClssEqualTo(clss);
        }
        if(status.equals("实习状态")){

        } else{
            criteria.andStatusEqualTo(status);
        }
        List<Student> students=studentMapper.selectByExample(studentExample);
        JSONArray objects=new JSONArray();
//        System.out.println(students);
        for(Student student:students){
            JSONObject obj=new JSONObject();
            obj.put("id",student.getId());
            User user=userMapper.selectByPrimaryKey(student.getUserId());
            obj.put("nickName",user.getNickName());
            obj.put("userName",user.getUserName());
            obj.put("status",student.getStatus());
            Company company=companyMapper.selectByPrimaryKey(student.getCompanyId());
            obj.put("companyName",company.getCompanyName());
            Teacher teacher=teacherMapper.selectByPrimaryKey(student.getTeacherId());
            obj.put("teacherId",teacher.getId());
            User user2=userMapper.selectByPrimaryKey(teacher.getId());
            obj.put("teacherName",user2.getNickName());
            obj.put("major",student.getMajor());
            obj.put("clss",student.getClss());
            obj.put("companyId",student.getCompanyId());

            if (user == null) {
                return Message.createErr("无法找到Teacher表userID=" + id + "对应的user！");
            }
            obj.put("name",user.getNickName());
            objects.add(obj);
        }
        result.put("students",objects);
        return Message.createSuc(result);
    }
    /*查看老师名下学生的筛选条件
     * @author hanfeng
     * */
    @Override
    public Message showCompanyStudentScreening(MVo mVo) {
        Map<String, Object> result = new HashMap<String, Object>();

        String major=mVo.getMajor();
        String clss=mVo.getClss();
        String iid=mVo.getId();
        String status=mVo.getStatus();

        Integer id = Integer.valueOf(iid);


        StudentExample studentExample1=new StudentExample();
        studentExample1.or().andCompanyIdEqualTo(id).andDeleteTagEqualTo(true);
        List<Student> students1=studentMapper.selectByExample(studentExample1);
        Set set1=new TreeSet();
        for(Student student:students1){
            set1.add(student.getMajor());
        }
        result.put("major",set1);
        StudentExample studentExample2=new StudentExample();
        StudentExample.Criteria criteria=studentExample2.or().andTeacherIdEqualTo(id).andDeleteTagEqualTo(true);
        if(major.equals("专业")){

        }else{
            criteria.andMajorEqualTo(major);
        }
        List<Student> students2=studentMapper.selectByExample(studentExample2);
        Set set2=new TreeSet();
        for(Student student:students2){
            set2.add(student.getClss());
        }
        result.put("clss",set2);

        return Message.createSuc(result);
    }
    /*
     * @param
     * @return
     * 查看招聘信息列表
     * @author hanfeng
     * */
    @Override
    public Message showRecruitment(ShipVo shipVo) {
        RecruitmentVo recruitmentVo;
        List<RecruitmentVo> recruitmentVoList = new ArrayList<RecruitmentVo>();

        Map<String, Object> result = new HashMap<String, Object>();

        String companyName=shipVo.getCompanyName();
        String status=shipVo.getStatu();
        String place=shipVo.getPlace();

        CompanyExample companyExample=new CompanyExample();
        List<Recruitment> Recruitments=null;
        System.out.println(companyName);

        RecruitmentExample recruitmentExample = new RecruitmentExample();
        RecruitmentExample.Criteria criteria=recruitmentExample.or();

        if(companyName.equals("招聘公司")){
            criteria.andPassEqualTo(true).andDeleteTagEqualTo(true).andRemoveEqualTo(false);
        }else{
            Integer idd=Integer.valueOf(companyName);
            companyExample.or().andPassEqualTo(true).andDeleteTagEqualTo(true).andIdEqualTo(idd);
            List<Company> companies=companyMapper.selectByExample(companyExample);
            for(Company company:companies){
                criteria.andPassEqualTo(true).andCompanyIdEqualTo(idd).andDeleteTagEqualTo(true);
            }
        }
        if(status.equals("招聘状态")){

        }else if(status.equals("招聘中")){
            criteria.andRecruitmentEqualTo(true);
        }else if(status.equals("已过期")){
            criteria.andRecruitmentEqualTo(false);
        }
        /*if(place.equals("工作地址")){

        }else{
            criteria.andCityEqualTo(place);
        }*/
        Recruitments = recruitmentMapper.selectByExample(recruitmentExample);
        if (Recruitments == null) {
            return Message.createErr("无法从Recruitment表里查到记录！");
        }
        for (Recruitment recruitment : Recruitments) {
            recruitmentVo=new RecruitmentVo();
            CompanyView companyView = companyViewMapper.selectByKey(recruitment.getCompanyId());
            recruitmentVo.setContact(recruitment.getContact());
            recruitmentVo.setPost(recruitment.getPost());
            recruitmentVo.setAddress(recruitment.getAddress());
            recruitmentVo.setCompanyName(companyView.getCompanyName());
            recruitmentVo.setPostTime(recruitment.getPostTime());
            recruitmentVo.setCurrentNumber(recruitment.getCurrentNumber());
            recruitmentVo.setTotalNumber(recruitment.getTotalNumber());
            recruitmentVo.setId(recruitment.getId());
            recruitmentVo.setCompanyId(recruitment.getCompanyId());
            recruitmentVo.setForbidden(recruitment.getForbidden());
            recruitmentVoList.add(recruitmentVo);
        }
        result.put("RecruitmentList", recruitmentVoList);
        return Message.createSuc(result);
    }
    /** @param
     * @return
     * 查看招聘申请
     * @author hanfeng
     * */
    @Override
    public Message showRecruitmentApplyList(String companyIdd) {
        Map<String, Object> result = new HashMap<String, Object>();
        RecruitmentExample recruitmentExample = new RecruitmentExample();
        Integer companyId = Integer.valueOf(companyIdd);

        if (companyId==0){
            recruitmentExample.or().andCheckedEqualTo(false).andDeleteTagEqualTo(true);
        }else{
            recruitmentExample.or().andCheckedEqualTo(false).andCompanyIdEqualTo(companyId).andDeleteTagEqualTo(true);
        }

        RecruitmentVo recruitmentVo;
        List<RecruitmentVo> recruitmentVoList = new ArrayList<RecruitmentVo>();

        List<Recruitment> Recruitments;
        Recruitments = recruitmentMapper.selectByExample(recruitmentExample);

        if (Recruitments == null) {
            return Message.createErr("无法从Company表里查到记录！");
        }

        System.out.println(Recruitments);

        for (Recruitment recruitment : Recruitments) {
            CompanyView companyView = companyViewMapper.selectByKey(recruitment.getCompanyId());
            recruitmentVo=new RecruitmentVo();
            recruitmentVo.setContact(recruitment.getContact());
            recruitmentVo.setPost(recruitment.getPost());
            recruitmentVo.setAddress(recruitment.getAddress());
            recruitmentVo.setId(recruitment.getId());
            recruitmentVo.setCompanyName(companyView.getCompanyName());
            recruitmentVo.setPostTime(recruitment.getPostTime());
            recruitmentVo.setCurrentNumber(recruitment.getCurrentNumber());
            recruitmentVo.setTotalNumber(recruitment.getTotalNumber());
            recruitmentVo.setCompanyId(recruitment.getCompanyId());
            recruitmentVoList.add(recruitmentVo);

        }
        result.put("recruitmentApplyList", recruitmentVoList);
        return Message.createSuc(result);
    }
    /** @param
     * @return
     * 查看实习岗位下的学生名字
     * @author hanfeng
     * */
    @Override
    public Message showRecruitmentStudent(String iid) {
        Map<String, Object> result = new HashMap<String, Object>();
        StudentRecruitmentExample studentRecruitmentExample=new StudentRecruitmentExample();
        Integer id = Integer.valueOf(iid);

        studentRecruitmentExample.or().andRecruitmentIdEqualTo(id).andPassingEqualTo(1);
        List<StudentRecruitment> studentRecruitments=studentRecruitmentMapper.selectByExample(studentRecruitmentExample);

        System.out.println(studentRecruitments);
        JSONArray objects = new JSONArray();

        for(StudentRecruitment studentRecruitment:studentRecruitments){
            Student student=studentMapper.selectByPrimaryKey(studentRecruitment.getStudentId());
            User user=userMapper.selectByPrimaryKey(student.getUserId());
            JSONObject obj = new JSONObject();
            obj.put("studentName",user.getNickName());
            obj.put("id",student.getId());
            objects.add(obj);
            result.put("Names",objects);
        }
        return Message.createSuc(result);
    }
    /*
     * author hanfeng
     * 禁用实习
     * */
    @Override
    public Message forbiddenRecruitment(String iid, boolean forbidden) {
        Integer id = Integer.valueOf(iid);

        Recruitment recruitment=recruitmentMapper.selectByPrimaryKey(id);
        if(recruitment ==null){
            return Message.createErr("未查到id=" + id + "的记录！");
        }
        recruitment.setForbidden(forbidden);
        recruitmentMapper.updateByPrimaryKey(recruitment);

        return Message.createSuc(null);
    }
    /*h获取实习的筛选条件
     * */
    @Override
    public Message showRecruitmentScreening(ScreenTwo screenTwo) {
        Map<String, Object> result = new HashMap<String, Object>();
//        System.out.println(screenTwo);

        String grade =screenTwo.getGrade();
        String clss =screenTwo.getClss();
        String status=screenTwo.getStatus();
        String iteacher=screenTwo.getIteacher();
//        String company=screenTwo.getCompany();
        String companyIdd=screenTwo.getCompanyId();
        Integer companyId = Integer.valueOf(companyIdd);

//
//        System.out.println(grade);
//        System.out.println(clss);
//        System.out.println(companyId);
////        System.out.println(company);
//        System.out.println(iteacher);
//        System.out.println(status);

        StudentRecruitmentViewExample studentRecruitmentViewExample1=new StudentRecruitmentViewExample();

        studentRecruitmentViewExample1.or().andPassingEqualTo(1).andDeleteTagEqualTo(true);

        List<StudentRecruitmentView> students1=studentRecruitmentViewMapper.selectByExample(studentRecruitmentViewExample1);
        Set set1=new TreeSet();
        for(StudentRecruitmentView student:students1){
            set1.add(student.getGrade());
        }
        result.put("grade",set1);
//        System.out.println("11111111111111");

        StudentRecruitmentViewExample studentRecruitmentViewExample2=new StudentRecruitmentViewExample();
        StudentRecruitmentViewExample.Criteria criteria=studentRecruitmentViewExample2.or().andDeleteTagEqualTo(true).andPassingEqualTo(1);
        if(grade.equals("年级")){

        }else{
            criteria.andGradeEqualTo(grade);
        }
        List<StudentRecruitmentView> students2=studentRecruitmentViewMapper.selectByExample(studentRecruitmentViewExample2);
        Set set2=new TreeSet();
        for(StudentRecruitmentView student:students2){
            set2.add(student.getClss());
        }
        result.put("clss",set2);

        if(clss.equals("班级")){

        }else{
            criteria.andClssEqualTo(clss);
        }


        List<StudentRecruitmentView> students3=studentRecruitmentViewMapper.selectByExample(studentRecruitmentViewExample2);
        Set set3=new TreeSet();

        for(StudentRecruitmentView student:students3){
            set3.add(student.getCompanyId());
        }

        Iterator<Integer> temp = set3.iterator();
        JSONArray jsonArray=new JSONArray();
        while (temp.hasNext()) {
            Company company1=companyMapper.selectByPrimaryKey(temp.next());
            JSONObject obj=new JSONObject();
            obj.put("companyName",company1.getCompanyName());
            obj.put("companyId",company1.getId());
            jsonArray.add(obj);
        }

        result.put("company",jsonArray);

//        StudentExample studentExample4=new StudentExample();
//        studentExample4.or();
//        List<Student> students4=studentService.selectByExample(studentExample4);
//        Set set5=new TreeSet();
//        for(Student student:students4){
//            set5.add(student.getStatus());
//        }

        return Message.createSuc(result);
    }
    /*
     * author hanfeng
     * 查看实习列表
     * */
    @Override
    public Message showInternships(ScreenTwo screenOne) {
        Map<String, Object> result = new HashMap<String, Object>();

        String grade =screenOne.getGrade();
        String clss =screenOne.getClss();
        String status=screenOne.getStatus();
        String iteacher=screenOne.getIteacher();
//        String company=screenOne.getCompany();
        String companyIdd=screenOne.getCompanyId();


        StudentRecruitmentViewExample studentRecruitmentViewExample=new StudentRecruitmentViewExample();

        StudentRecruitmentViewExample.Criteria criteria=studentRecruitmentViewExample.or();

        if(grade.equals("年级")){
            criteria.andPassingEqualTo(1).andDeleteTagEqualTo(true);
        }else{
            criteria.andGradeEqualTo(grade).andPassingEqualTo(1).andDeleteTagEqualTo(true);
        }
        if(clss.equals("班级")){

        }else{
            criteria.andClssEqualTo(clss);
        }
        if(companyIdd.equals("0")){

        }else{
            Integer companyId = Integer.valueOf(companyIdd);
            criteria.andCompanyIdEqualTo(companyId);
        }

        if(status.equals("全部")){

        }else{
            criteria.andStatusEqualTo(status);
        }
        if(iteacher.equals("有无指导老师")){

        }else if(iteacher.equals("无指导老师")){
            criteria.andTeacherIdIsNull();
        }else if(iteacher.equals("有指导老师")){
            criteria.andTeacherIdIsNotNull();
        }


        List<StudentRecruitmentView> studentRecruitmentViews=studentRecruitmentViewMapper.selectByExample(studentRecruitmentViewExample);




        List<InternshipVo> internshipVos=new ArrayList<>();

        for(StudentRecruitmentView studentRecruitmentView:studentRecruitmentViews){
            InternshipVo internshipVo=new InternshipVo();
            SummaryExample summaryExample=new SummaryExample();
            summaryExample.or().andStudentIdEqualTo(studentRecruitmentView.getStudentId()).andStatusIdEqualTo(true);
            Weight weight=weightMapper.selectByPrimaryKey(1);

            Score score=scoreMapper.selectByStudent(studentRecruitmentView.getStudentId());
            if(score==null){
                internshipVo.setScore(null);
            }else{
                internshipVo.setScore(score.gettWeekReport()*weight.gettWeekReport()+score.gettSummary()*weight.gettSummary()+score.gettFinalReport()*weight.gettFinalReport()+score.getAdditionalScore());
            }
            User user=userMapper.selectByPrimaryKey(studentRecruitmentView.getUserId());
            internshipVo.setNickName(user.getNickName());
            internshipVo.setClss(studentRecruitmentView.getClss());
            Company company1=companyMapper.selectByPrimaryKey(studentRecruitmentView.getCompanyId());
            internshipVo.setCompanyId(studentRecruitmentView.getCompanyId());
            internshipVo.setCompany(company1.getCompanyName());
            Recruitment recruitment=recruitmentMapper.selectByPrimaryKey(studentRecruitmentView.getRecruitmentId());
            internshipVo.setPost(recruitment.getPost());
            internshipVo.setStatus(studentRecruitmentView.getStatus());
            internshipVo.setStages(recruitment.getPostTime());
            Teacher teacher=teacherMapper.selectByPrimaryKey(studentRecruitmentView.getTeacherId());
            User user1=userMapper.selectByPrimaryKey(teacher.getUserId());
            internshipVo.setTeacherId(teacher.getId());
            internshipVo.setTeacherName(user1.getNickName());
            internshipVo.setId(studentRecruitmentView.getId());
            internshipVo.setStudentId(studentRecruitmentView.getStudentId());
            internshipVo.setStages(studentRecruitmentView.getPostTime());
            internshipVos.add(internshipVo);
        }
        result.put("internship",internshipVos);

        return Message.createSuc(result);
    }
    /** @param
     * @return
     * 查看考勤信息
     * @author hanfeng
     * */
    @Override
    public Message showCheck(String iid, String month) {
        //        try {
//            month= new String(month .getBytes("iso8859-1"),"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        Integer id = Integer.valueOf(iid);

        Map<String, Object> result = new HashMap<String, Object>();
        DailyCheckExample dailyCheckExample=new DailyCheckExample();
        dailyCheckExample.or().andStudentIdEqualTo(id);
        List<DailyCheck> dailyChecks=dailyCheckMapper.selectByExample(dailyCheckExample);
        JSONArray objects=new JSONArray();

        System.out.println(month);

        for(DailyCheck dailyCheck:dailyChecks){
            JSONObject obj=new JSONObject();

            String one=new SimpleDateFormat("yyyy/MM/dd").format(dailyCheck.getStartTime());
            String two=new SimpleDateFormat("HH:mm:ss").format(dailyCheck.getStartTime());
            String three=new SimpleDateFormat("HH:mm:ss").format(dailyCheck.getStartTime());
            String four=new SimpleDateFormat("yyyy/MM").format(dailyCheck.getStartTime());

            System.out.println(four);

            obj.put("screen",four);
            obj.put("date",one);
            obj.put("startTime",two);
            obj.put("endTime",three);

            if(month.equals("全部时段")){
                objects.add(obj);
            }else if(month.equals(four)){
                objects.add(obj);
            }else{

            }

        }
        Student student=studentMapper.selectByPrimaryKey(id);
        User user=userMapper.selectByPrimaryKey(student.getUserId());
        result.put("name",user.getNickName());
        result.put("id",id);
        result.put("date",objects);
        return Message.createSuc(result);
    }
    /** @param
     * @return
     * 查看考勤的筛选条件
     * @author hanfeng
     * */
    @Override
    public Message showCheckScreening(String iid) {
        Integer id = Integer.valueOf(iid);

        Map<String, Object> result = new HashMap<String, Object>();
        DailyCheckExample dailyCheckExample=new DailyCheckExample();
        dailyCheckExample.or().andStudentIdEqualTo(id);
        List<DailyCheck> dailyChecks=dailyCheckMapper.selectByExample(dailyCheckExample);
        JSONArray ovjects=new JSONArray();

        for(DailyCheck dailyCheck:dailyChecks){
            JSONObject ovj=new JSONObject();
            String four=new SimpleDateFormat("yyyy/MM").format(dailyCheck.getStartTime());
            ovj.put("screens",four);
            ovjects.add(ovj);
        }

        //Set set=new TreeSet();
        System.out.println(ovjects);
        Set set=new HashSet(ovjects);
        System.out.println(set);

        result.put("id",id);
        result.put("month",set);
        return Message.createSuc(result);
    }
    /** @param
     * @return
     * 查看周报列表
     * @author hanfeng
     * */
    @Override
    public Message showWeeklyList(String iid) {
        Map<String, Object> result = new HashMap<String, Object>();
        Integer id = Integer.valueOf(iid);

        ReportExample reportExample=new ReportExample();

        reportExample.or().andStudentIdEqualTo(id);
        reportExample.setOrderByClause("id");

        List<Report> reports=reportMapper.selectByExample(reportExample);

        JSONArray objects=new JSONArray();
        for(Report report:reports){
            JSONObject object=new JSONObject();
            object.put("week",report.getWeek());
            object.put("teacherScore",report.getScore());
            object.put("companyScore",report.getcScore());
            object.put("id",report.getId());

            object.put("datetime",report.getPublishedDate());
            objects.add(object);
        }
        result.put("studentId",id);
        result.put("weeklyList",objects);
        return Message.createSuc(result);
    }
    /** @param
     * @return
     * 查看周报详情
     * @author hanfeng
     * */
    @Override
    public Message showWeeklyDetail(String iid) {
        Map<String, Object> result = new HashMap<String, Object>();

        ReportExample reportExample=new ReportExample();
        Integer id = Integer.valueOf(iid);

        reportExample.or().andStudentIdEqualTo(id);

        List<Report> reports=reportMapper.selectByExample(reportExample);

        JSONArray objects=new JSONArray();
        for(Report report:reports){
            JSONObject object=new JSONObject();
            object.put("week",report.getWeek());
            object.put("Score",report.getScore());
            object.put("cScore",report.getcScore());
            object.put("id",report.getId());
            object.put("publishedDate",report.getPublishedDate());
            object.put("title",report.getTitle());
            Student student=studentMapper.selectByPrimaryKey(report.getStudentId());
            User user=userMapper.selectByPrimaryKey(student.getUserId());
            object.put("name",user.getNickName());

            object.put("startTime",report.getStartTime());

            object.put("endTime",report.getEndTime());

            object.put("content",report.getContent());

            object.put("readoverTime",report.getReadoverTime());

            object.put("comment",report.getComment());
            User user2=userMapper.selectByPrimaryKey(student.getTeacherId());
            object.put("teacherName",user2.getNickName());
            object.put("email",student.getEmail());
            object.put("phone",user.getPhone());

            object.put("cReadoverTime",report.getcReadoverTime());

            object.put("cComment",report.getcComment());
            Company company =companyMapper.selectByPrimaryKey(student.getCompanyId());
            object.put("cName",company.getCompanyName());
            object.put("cEmail",company.getEmail());
            User user3=userMapper.selectByPrimaryKey(company.getUserId());
            ImageExample imageExample=new ImageExample();
            imageExample.or().andReportIdEqualTo(report.getId());
            List<Image> images=imageMapper.selectByExample(imageExample);
            object.put("photo",images);
            object.put("cPhone",user3.getPhone());

            objects.add(object);
        }
        result.put("studentId",id);
        result.put("weeklyList",objects);
        return Message.createSuc(result);
    }
    /** @param
     * @return
     * 查看小结详情
     * @author hanfeng
     * */
    @Override
    public Message showSummary(String iid) {
        Integer id = Integer.valueOf(iid);
        Map<String, Object> result = new HashMap<String, Object>();

        SummaryExample summaryExample=new SummaryExample();

        summaryExample.or().andStudentIdEqualTo(id).andStatusIdEqualTo(true);
        List<Summary> summaries=summaryMapper.selectByExample(summaryExample);


        JSONArray objects=new JSONArray();
        for(Summary summary:summaries){
            JSONObject object=new JSONObject();
            object.put("Score",summary.getScore());
            object.put("id",summary.getId());

            object.put("publishedDate",summary.getPublishedDate());

            object.put("title",summary.getTitle());
            Student student=studentMapper.selectByPrimaryKey(summary.getStudentId());
            User user=userMapper.selectByPrimaryKey(student.getUserId());
            object.put("name",user.getNickName());

            object.put("startTime",summary.getStartTime());

            object.put("endTime",summary.getEndTime());
            object.put("content",summary.getContent());
            object.put("readoverTime",summary.getReadoverTime());
            object.put("comment",summary.getComment());
            User user2=userMapper.selectByPrimaryKey(student.getTeacherId());
            object.put("teacherName",user2.getNickName());
            object.put("email",student.getEmail());
            object.put("phone",user.getPhone());
            objects.add(object);
        }
        result.put("studentId",id);
        result.put("summary",objects);
        return Message.createSuc(result);
    }
    /** @param
     * @return
     * 查看报告详情
     * @author hanfeng
     * */
    @Override
    public Message showSummaryDetail(String iid) {
        Integer id = Integer.valueOf(iid);

        Map<String, Object> result = new HashMap<String, Object>();

        SummaryExample summaryExample=new SummaryExample();

        summaryExample.or().andStudentIdEqualTo(id).andStatusIdEqualTo(false);

        List<Summary> summaries=summaryMapper.selectByExample(summaryExample);

        JSONArray objects=new JSONArray();
        for(Summary summary:summaries){
            JSONObject object=new JSONObject();
            object.put("Score",summary.getScore());
            object.put("id",summary.getId());
            object.put("publishedDate",summary.getPublishedDate());
            object.put("title",summary.getTitle());
            Student student=studentMapper.selectByPrimaryKey(summary.getStudentId());
            User user=userMapper.selectByPrimaryKey(student.getUserId());
            object.put("name",user.getNickName());
            object.put("startTime",summary.getStartTime());
            object.put("endTime",summary.getEndTime());
            object.put("content",summary.getContent());
            object.put("readoverTime",summary.getReadoverTime());
            object.put("comment",summary.getComment());
            User user2=userMapper.selectByPrimaryKey(student.getTeacherId());
            object.put("teacherName",user2.getNickName());
            object.put("email",student.getEmail());
            object.put("phone",user.getPhone());
            objects.add(object);
        }
        result.put("studentId",id);
        result.put("summary",objects);
        return Message.createSuc(result);
    }
    /** @param
     * @return
     * 查看实习成绩详情
     * @author hanfeng
     * */
    @Override
    public Message showScore(String iid) {
        Integer id = Integer.valueOf(iid);

        Map<String, Object> result = new HashMap<String, Object>();
        Score score=scoreMapper.selectByStudent(id);
        Weight weight=weightMapper.selectByPrimaryKey(1);

//        System.out.println(weight.toString());

        JSONObject obj=new JSONObject();

        float tweek=score.gettWeekReport();  //老师给的周报加权
        float tsummary=score.gettSummary();  //老师的小结成绩

        Float treport=score.gettFinalReport();
        if (treport == null) {
            treport = 0F;
        }
//        System.out.println(treport);

        float  tweighting=tweek*weight.gettWeekReport()+tsummary*weight.gettSummary()+treport*weight.gettFinalReport();

        obj.put("totalScore",tweighting);
        obj.put("tWeekly",tweek);
        obj.put("summary",tsummary);
        obj.put("report",treport);
        obj.put("Weighting", tweighting);
        obj.put("ttotalScore",score.gettWeekReport()*weight.gettWeekReport()+score.gettSummary()*weight.gettSummary()+score.gettFinalReport()*weight.gettFinalReport()+score.getAdditionalScore());
        obj.put("cWeekly",score.getcWeekReport()*weight.getcWeekReport());
        float a=score.getcAttendance()*weight.getcAttendance();
        obj.put("checkonWork",(int)a);
        obj.put("ctotalScore",score.getcWeekReport()*weight.getcWeekReport()+score.getcAttendance()*weight.getcAttendance());
        obj.put("additionalPoints",score.getAdditionalScore());
        result.put("score",obj);

        result.put("tWeekReport",weight.gettWeekReport());
        result.put("tSummary",weight.gettSummary());
        result.put("tFinalReport",weight.gettFinalReport());
        result.put("cWeekReport",weight.getcWeekReport());
        result.put("cAttendance",weight.getcAttendance());
        result.put("teacherWeight",weight.getTeacherWeight());
        result.put("companyWeight",weight.getCompanyWeight());

        return Message.createSuc(result);
    }
    /*
     * @param
     * @return
     * 查看某个企业招聘信息
     * @author hanfeng
     * */
    @Override
    public Message showCompanyRecruitments(CVo cVo) {
        RecruitmentVo recruitmentVo;
        List<RecruitmentVo> recruitmentVoList = new ArrayList<RecruitmentVo>();
        String id=cVo.getId();
        String month=cVo.getMonth();
        Integer companyId = Integer.valueOf(id);

        Map<String, Object> result = new HashMap<String, Object>();
        RecruitmentExample recruitmentExample = new RecruitmentExample();
        recruitmentExample.or().andPassEqualTo(true).andCompanyIdEqualTo(companyId);
        List<Recruitment> Recruitments = recruitmentMapper.selectByExample(recruitmentExample);

        if (Recruitments == null) {
            return Message.createErr("无法从Recruitments表里查到记录！");
        }
        for (Recruitment recruitment : Recruitments) {
            recruitmentVo=new RecruitmentVo();
            if(substr(recruitment.getReleaseTime(),0,7).equals(month)) {
                CompanyView companyView = companyViewMapper.selectByKey(recruitment.getCompanyId());
                recruitmentVo.setContact(recruitment.getContact());
                recruitmentVo.setPost(recruitment.getPost());
                recruitmentVo.setAddress(recruitment.getAddress());
                recruitmentVo.setCompanyName(companyView.getCompanyName());
                recruitmentVo.setPostTime(recruitment.getPostTime());
                recruitmentVo.setCurrentNumber(recruitment.getCurrentNumber());
                recruitmentVo.setTotalNumber(recruitment.getTotalNumber());
                recruitmentVo.setId(recruitment.getId());
                recruitmentVo.setReleaseTime(recruitment.getReleaseTime());
                recruitmentVo.setCompanyId(recruitment.getCompanyId());
                recruitmentVoList.add(recruitmentVo);
            }
            else if(month.equals("招聘时间")){
                CompanyView companyView = companyViewMapper.selectByKey(recruitment.getCompanyId());
                recruitmentVo.setContact(recruitment.getContact());
                recruitmentVo.setPost(recruitment.getPost());
                recruitmentVo.setAddress(recruitment.getAddress());
                recruitmentVo.setCompanyName(companyView.getCompanyName());
                recruitmentVo.setPostTime(recruitment.getPostTime());
                recruitmentVo.setCurrentNumber(recruitment.getCurrentNumber());
                recruitmentVo.setTotalNumber(recruitment.getTotalNumber());
                recruitmentVo.setId(recruitment.getId());
                recruitmentVo.setReleaseTime(recruitment.getReleaseTime());
                recruitmentVo.setCompanyId(recruitment.getCompanyId());
                recruitmentVoList.add(recruitmentVo);
            }
        }
        result.put("RecruitmentList", recruitmentVoList);
        return Message.createSuc(result);
    }
    /** @param
     * @return
     * 查看某个公司招聘岗位的筛选条件
     * @author hanfeng
     * */
    @Override
    public Message showRecruitmentScreen(Integer id) {
        //        Integer id = Integer.valueOf(iid);
        Map<String, Object> result = new HashMap<String, Object>();
        RecruitmentExample recruitmentExample=new RecruitmentExample();

        recruitmentExample.or().andCompanyIdEqualTo(id).andPassEqualTo(true).andDeleteTagEqualTo(true);
        List<Recruitment> recruitments = recruitmentMapper.selectByExample(recruitmentExample);

        if (recruitments == null) {
            return Message.createErr("无法从Recruitments表里查到记录！");
        }
        JSONArray ovjects=new JSONArray();
        for(Recruitment recruitment:recruitments){
            JSONObject ovj=new JSONObject();
            String four=substr(recruitment.getReleaseTime(),0,7);
            /*new SimpleDateFormat("yyyy/MM").format(recruitment.getReleaseTime());*/
            ovj.put("screen",four);
            ovjects.add(ovj);
        }
//        System.out.println(ovjects);
        Set set=new HashSet(ovjects);
//        System.out.println(set);
        result.put("id",id);
        result.put("month",set);
        return Message.createSuc(result);
    }

    @Override
    public Message showRecruitmentDetails(Integer id) {
        //        Integer id = Integer.valueOf(iid);

        Map<String, Object> result = new HashMap<String, Object>();

        Recruitment recruitment = recruitmentMapper.selectByPrimaryKey(id);

        if (recruitment == null) {
            return Message.createErr("无法从Recruitment表里查到记录！");
        }
        JSONObject object=new JSONObject();
        object.put("post",recruitment.getPost());
        object.put("totalnumber",recruitment.getTotalNumber());
        object.put("postTime",recruitment.getPostTime());
        object.put("address",recruitment.getAddress());
        object.put("skillRequire",recruitment.getSkillRequire());
        Company company=companyMapper.selectByPrimaryKey(recruitment.getCompanyId());
        object.put("companyName",company.getCompanyName());
        object.put("logo",company.getLogo());
        object.put("type",company.getType());
        object.put("size",company.getSize());
        object.put("stage",company.getStage());
        object.put("postInfo",recruitment.getPostInfo());
        object.put("id",recruitment.getId());
        result.put("code", Constant.OK);
        result.put("recruitmentDetails", object);
        return Message.createSuc(result);
    }

    @Override
    public Message showStudents(ScreeningVo screeningVo, Integer pageNum) {
        System.out.println(pageNum);
        Map<String, Object> result = new HashMap<String, Object>();
        System.out.println(screeningVo);
        String grade=screeningVo.getGrade();
        String major=screeningVo.getMajor();
        String clss=screeningVo.getClss();
        String status=screeningVo.getStatu();
        System.out.println(grade);
        System.out.println(major);
        System.out.println(clss);
        System.out.println(status);
        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.or();


        if(grade.equals("年级")){
            criteria.andDeleteTagEqualTo(true);
        }else{
            criteria.andGradeEqualTo(grade);
        }
        if(major.equals("专业")){

        }else{
            criteria.andMajorEqualTo(major);
        }
        if(clss.equals("班级")){

        }else{
            criteria.andClssEqualTo(clss);
        }
        if(status.equals("实习状态")){

        }else{
            criteria.andStatusEqualTo(status);
        }

 /*       studentExample.setOrderByClause("id DESC");

        PageHelper.startPage(pageNum,8);
        Page<Student> students=(Page<Student>)studentService.selectByExample(studentExample);*/

        List<Student> students=studentMapper.selectByExample(studentExample);
        List<StudentVo> studentVos=new ArrayList<>();
        for(Student student:students){
            StudentVo studentVO=new StudentVo();
            User user=userMapper.selectByPrimaryKey(student.getUserId());
            if(student.getTeacherId()==null){

            }else{
                Teacher teacher=teacherMapper.selectByPrimaryKey(student.getTeacherId());
                User userone=userMapper.selectByPrimaryKey(teacher.getUserId());
                studentVO.setTeacherName(userone.getNickName());
                studentVO.setTeacherId(student.getTeacherId());
            }
            studentVO.setNickName(user.getNickName());
            studentVO.setUserName(user.getUserName());
            studentVO.setMajor(student.getMajor());
            studentVO.setClss(student.getClss());
            studentVO.setGrade(student.getGrade());
            studentVO.setStatu(student.getStatus());
            studentVO.setId(student.getId());
            studentVO.setForbidden(student.getForbidden());
            studentVos.add(studentVO);
        }
      /*  PageInfo pageResult = new PageInfo(studentVos);
        pageResult.setList(studentVos);*/

        result.put("studentList",studentVos);
        return Message.createSuc(result);
    }


}
