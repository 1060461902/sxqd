package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.User;
import edu.zjgsu.ito.model.Weight;
import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.vo.*;

import javax.servlet.http.HttpServletRequest;

public interface SimpleService {

    Message teacherRegister(String userName, String nickName, String major);

    Message studentRegister(String userName, String nickName, String major, String clss, String grade);

    Message setWeight(Weight weight);

    Message resetPwd(Integer roleId, Integer id);

    Message register(User user);

    Message login(HttpServletRequest request, User user);

    Message modifyPwd(ResetPwdUser frontUser);

    Message confirmRegister(ApprovalVo approvalVo);

    Message confirmDynamicNews(ApprovalVo approvalVo);

    Message confirmInternship(ApprovalVo approvalVo);

    Message deleteShow(IdVo idVo);

    Message deleteCompany(IdVo idVo);

    Message deleteRecruitment(IdVo idVo);

    Message forbiddenCompany(String iid, boolean forbidden);

    Message showCompanyRegisterApplyList(String type);

    Message showCompanyDetails(String iid);

    Message showDynamicNewsApplyList();

    Message showDynamicNewsList();

    Message showCompanyNames();

    Message showDynamicNewsDetails(String iid);

    Message showCompanyRecruitment(String iid);

    Message showCompanyStudentName(String iid);

    Message showCompanyStudent(MVo mVo);

    Message showCompanyStudentScreening(MVo mVo);

    Message showRecruitment(ShipVo shipVo);

    Message showRecruitmentApplyList(String companyIdd);

    Message showRecruitmentStudent(String iid);

    Message forbiddenRecruitment(String iid, boolean forbidden);

    Message showRecruitmentScreening(ScreenTwo screenTwo);

    Message showInternships(ScreenTwo screenOne);

    Message showCheck(String iid, String month);

    Message showCheckScreening(String iid);

    Message showWeeklyList(String iid);

    Message showWeeklyDetail(String iid);

    Message showSummary(String iid);

    Message showSummaryDetail(String iid);

    Message showScore(String iid);

    Message showCompanyRecruitments(CVo cVo);

    Message showRecruitmentScreen(Integer id);

    Message showRecruitmentDetails(Integer id);

    Message showStudents(ScreeningVo screeningVo , Integer pageNum);







}
