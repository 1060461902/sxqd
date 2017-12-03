package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.model.Student;
import edu.zjgsu.ito.model.User;
import edu.zjgsu.ito.model.UserExample;
import edu.zjgsu.ito.service.AdminOperateService;
import edu.zjgsu.ito.service.StudentService;
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

/*    *
     * 描述：对表格中数值进行格式化
     * @param cell
     * @return*/

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

    @Override
    public List<List<Object>> readExcel(InputStream is, String fileName) {
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
            wb = Constant.excel2003L.equals(fileType) ? new HSSFWorkbook(is) : new XSSFWorkbook(is);

            data = new ArrayList<List<Object>>();
//            遍历每一页
            for (int index = 0; index < wb.getNumberOfSheets(); index++) {
                sheet = wb.getSheetAt(index);

//            遍历当前页的每一行
                for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum() ; i++) {
                    row = sheet.getRow(i);

//                当sheet没有数据时，row = null，报nullPointerException
//                row.getFirstCellNum() == i:去掉第一行的记录
                    if(row == null || row.getFirstCellNum() == i) {
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

    @Override
    public Map<String, Object> studentBatchRegister(HttpServletRequest request) {
        int status = 0;
        User user = null;
        InputStream is = null;
        Student student = null;
        List<Object> record = null;
        List<List<Object>> data = null;
        UserExample userExample = new UserExample();
        Map<String, Object> result = new HashMap<String, Object>();

//        接收文件
        MultipartHttpServletRequest multipartRequest = multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("upFile");


        try {
            is = file.getInputStream();
//            读取Excel文件
            data = readExcel(is, file.getOriginalFilename());
            is.close();

            for (int i = 0; i < data.size(); i++) {

                user = new User();
                student = new Student();

//                获取一行记录
                record = data.get(i);

                user.setNickName(String.valueOf(record.get(0)));
                user.setUserName(String.valueOf(record.get(1)));
                user.setRoleId(Constant.STUDENT);

                userService.insert(user);

//                找到刚刚插入的那条记录
                userExample.setOrderByClause("id DESC");
                userExample.setLimit(1);
                student.setUserId(userService.selectByExample(userExample).get(0).getId());
                student.setMajor(String.valueOf(record.get(2)));
                student.setClss(String.valueOf(record.get(3)));
                status = studentService.insert(student);


            }

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


    @Override
    public Map<String, Object> teacherBatchRegister(String filePath) {
        Map<String, Object> result = new HashMap<String, Object>();

        return result;
    }
}
