package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.AdminOperateService;
import edu.zjgsu.ito.service.StudentService;
import edu.zjgsu.ito.service.TeacherService;
import edu.zjgsu.ito.service.UserService;
import edu.zjgsu.ito.utils.Constant;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminOperateServiceImpl implements AdminOperateService {

    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;


    /**
     * 对表中数值格式化
     * @param cell
     * @return
     * @author sawei
     */
    @Override
    public  Object getCellValue(Cell cell){
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = df.format(cell.getNumericCellValue());
                }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf.format(cell.getDateCellValue());
                }else{
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }

    /**
     *读取Excel
     * @param is 文件流
     * @param fileName 文件名
     * @return
     * @author sawei
     */
    @Override
    public List<List<Object>> readExcel(InputStream is, String fileName, Integer roleId) {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        List<Object> record = null;
        List<List<Object>> data = null;
        String fileType = null;

//        取得文件后缀名
        fileType = fileName.substring(fileName.lastIndexOf("."));
//            根据类型new
        try {
            wb = Constant.EXCEL2003L.equals(fileType) ? new HSSFWorkbook(is) : new XSSFWorkbook(is);

            data = new ArrayList<List<Object>>();

//            遍历每一页
            for (int index = 0; index < wb.getNumberOfSheets(); index++) {
                sheet = wb.getSheetAt(index);
                String sheetName = sheet.getSheetName();

//            遍历当前页的每一行
                for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum() ; i++) {
                    row = sheet.getRow(i);
//                当sheet没有数据时，row = null，报nullPointerException
//                row.getFirstRowNum() == i:去掉第一行的记录
                    if(row == null || i == sheet.getFirstRowNum()) {
                        continue;
                    }

                    record = new ArrayList<Object>();
                    for (int j = row.getFirstCellNum(); j <= row.getLastCellNum() - 1 ; j++) {
//                    得到单元格cell
                        cell = row.getCell(j);

                        record.add(getCellValue(cell));
//                    System.out.print(getCellValue(cell)+ ",");
                    }
//                System.out.println("");
                    data.add(record);

                }//            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum() ; i++) {
            }//        for (int index = 0; index < wb.getNumberOfSheets(); index++) {

            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * 添加一条user表记录，并返回最新记录的id
     * @param user
     * @return
     * @author sawei
     */
    @Override
    public Integer userRegister(User user) {
        int userId;
        UserExample userExample = new UserExample();
        userService.insert(user);
        //                找到刚刚插入的那条记录
        userExample.setOrderByClause("id DESC");
        userExample.setLimit(1);

        userId = userService.selectByExample(userExample).get(0).getId();

        return userId;
    }

    /**
     * 学生注册
     * @param userName
     * @param nickName
     * @param major
     * @param clss
     * @return
     * @author sawei
     */
    @Override
    public Integer studentRegister(String userName, String nickName, String major, String clss) {
        int status;
        User user = new User();
        Student student = new Student();

        user.setUserName(userName);
        user.setNickName(nickName);
        user.setPassword(Constant.DEFAULTPWD);
        user.setRoleId(Constant.STUDENT);

        student.setUserId(userRegister(user));
        student.setMajor(major);
        student.setClss(clss);

        status = studentService.insert(student);
        return status;
    }

    /**
     * 老师注册
     * @param userName
     * @param nickName
     * @param major
     * @return
     * @author sawei
     */
    @Override
    public Integer teacherRegister(String userName, String nickName, String major) {
        int status;
        User user = new User();
        Teacher teacher = new Teacher();

        user.setUserName(userName);
        user.setNickName(nickName);
        user.setPassword(Constant.DEFAULTPWD);
        user.setRoleId(Constant.STUDENT);

        teacher.setUserId(userRegister(user));
        teacher.setMajor(major);

        status = teacherService.insert(teacher);
        return status;
    }

//    public Integer companyRegister(Company company) {
//
//    }


    /**
     * 学生或者老师批量注册
     * @param request
     * @param roleId 角色id
     * @return
     * @author sawei
     */
    @Override
    public Map<String, Object> batchRegister(HttpServletRequest request, Integer roleId) {
        int status = 0;
        InputStream is = null;
        List<Object> record = null;
        List<List<Object>> data = null;
        Map<String, Object> result = new HashMap<String, Object>();

//        接收文件
        MultipartHttpServletRequest multipartRequest = multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("excelFile");

        try {
            is = file.getInputStream();
//            读取Excel文件
            data = readExcel(is, file.getOriginalFilename(), roleId);
            is.close();

            for (int i = 0; i < data.size(); i++) {

//                判断是学生还是教师
                if (roleId.equals(Constant.STUDENT)) {
//                获取一行记录
                    record = data.get(i);
//                    学生注册
                    status = studentRegister(String.valueOf(record.get(1)), String.valueOf(record.get(0)),
                            String.valueOf(record.get(2)), String.valueOf(record.get(3)));
                } else if (roleId.equals(Constant.TEACHER)) {
//                获取一行记录
                    record = data.get(i);
//                    老师注册
                    status = teacherRegister(String.valueOf(record.get(0)), String.valueOf(record.get(1)),
                            String.valueOf(record.get(2)));
                } else {
                    result.put("code", Constant.FAIL);
                    result.put("msg", "roleId错误");
                    return result;
                }
            }//            for (int i = 0; i < data.size(); i++) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (status > 0) {
            result.put("code", Constant.OK);
            result.put("msg", "批量注册成功！");
        } else {
            result.put("code", Constant.FAIL);
            result.put("msg", "批量注册失败！");
        }

        return result;
    }

}
