package edu.zjgsu.ito.service.Impl;

import edu.zjgsu.ito.dao.FinalExcelViewMapper;
import edu.zjgsu.ito.dao.WeightMapper;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.service.*;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.utils.FileUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminOperateServiceImpl implements AdminOperateService {

    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    WeightMapper weightMapper;
    @Autowired
    FinalExcelViewMapper finalExcelViewMapper;
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


    @Override
    public List<String> getExcelTitles(String fileName) {
        List<String> excelTitles = new ArrayList<>();
        Calendar now = Calendar.getInstance();

        WeightExample weightExample = new WeightExample();
        weightExample.or().andIdIsNotNull();
        Weight weight = weightMapper.selectByExample(weightExample).get(0);

        excelTitles.add(fileName);
        excelTitles.add("导入时间：" + now.get(Calendar.YEAR) + "/" + now.get(Calendar.MONTH) + "/" + now.get(Calendar.DAY_OF_MONTH));
        excelTitles.add("学号");
        excelTitles.add("姓名");
        excelTitles.add("班级");
        excelTitles.add("指导老师");
        excelTitles.add("老师评分（" + String.valueOf(weight.getTeacherWeight()) + ")");
        excelTitles.add("周报(" + String.valueOf(weight.gettWeekReport()) + ")");
        excelTitles.add("实习小结(" + String.valueOf(weight.gettSummary()) + ")");
        excelTitles.add("实习报告(" + String.valueOf(weight.gettFinalReport()) + ")");
        excelTitles.add("附加分");
        excelTitles.add("实习成绩A");
        excelTitles.add("企业评分（" + String.valueOf(weight.getCompanyWeight()) + ")");
        excelTitles.add("周报（" + String.valueOf(weight.getcWeekReport()) + ")");
        excelTitles.add("考勤（" + String.valueOf(weight.getcAttendance()) + ")");
        excelTitles.add("实习成绩B");
        excelTitles.add("最终实习成绩");

        return excelTitles;
    }


    @Override
    public List<FinalExcelView> getRecords(String grade) {
//        从视图里查出Excel的所有记录
        FinalExcelViewExample example = new FinalExcelViewExample();
        example.or().andGradeEqualTo(grade);
        List<FinalExcelView> records = finalExcelViewMapper.selectByExample(example);
//        查询权重
        Weight weight = weightMapper.selectByPrimaryKey(1);

//        计算成绩
        for (FinalExcelView record:
             records) {
            float scoreA = record.gettWeekReport() * weight.gettWeekReport() + record.gettSummary() * weight.gettSummary()
                    + record.gettFinalReport() * weight.gettWeekReport();
//            加上附加分
            scoreA = scoreA + record.getAdditionalScore() > (float) 100 ? (float) 100 : scoreA + record.getAdditionalScore();
            float scoreB = record.getcWeekReport() * weight.getcWeekReport() + record.getcAttendance() * weight.getcAttendance();
            float finalScore = scoreA * weight.getTeacherWeight() + scoreB * weight.getCompanyWeight();
            record.setScoreA(scoreA);
            record.setScoreB(scoreB);
            record.setFinalScore(finalScore);
        }

        return records;
    }

    public Sheet setTitles(Workbook workbook, String sheetName, List<String> excelTitles) {
        //        宋体，11，加粗，居中,实线边框 ,字段的格式
        Font font = workbook.createFont();
        font.setFontName(Constant.fontName);
        font.setFontHeightInPoints((short)11);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(font);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);

        //        宋体，11，居中 日期的格式
        Font font1 = workbook.createFont();
        font1.setFontName(Constant.fontName);
        font1.setFontHeightInPoints((short)11);

        CellStyle style1 = workbook.createCellStyle();
        style1.setAlignment(CellStyle.ALIGN_CENTER);
        style1.setFont(font1);


        //        宋体，16，居中，标题的格式
        Font font2 = workbook.createFont();
        font2.setFontName(Constant.fontName);
        font2.setFontHeightInPoints((short)16);

        CellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(CellStyle.ALIGN_CENTER);
        style2.setFont(font2);

        //create sheet
        Sheet sheet = workbook.createSheet(sheetName);
//        列宽自适应
        sheet.autoSizeColumn(1, true);

//        生成行
        Row firstRow = sheet.createRow(1);
        Row secondRow = sheet.createRow(2);
        Row thirdRow = sheet.createRow(3);
        Row forthRow = sheet.createRow(4);


//        写标题
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 14));
        Cell cell1 = firstRow.createCell(1);
        cell1.setCellValue(excelTitles.get(0));
        cell1.setCellStyle(style2);

//            写导入日期
        sheet.addMergedRegion(new CellRangeAddress(2,2,1,2));
        Cell time = secondRow.createCell(1);
        time.setCellValue(excelTitles.get(1));
        time.setCellStyle(style1);

//            写学号
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 1, 1));
        Cell studentID = mainRow.createCell(1);
//            studentID.setCellValue(excelTitle.get(2));
        studentID.setCellValue("学号");
        studentID.setCellStyle(style);
        scoreRow.createCell(1).setCellStyle(style);
        rowIndex++;

//            姓名
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 2, 2));
        Cell name = mainRow.createCell(2);
        name.setCellValue(excelTitle.get(3));
        name.setCellStyle(style);
        scoreRow.createCell(2).setCellStyle(style);

//            班级
        sheet.addMergedRegion(new CellRangeAddress(3,4,3,3));
        Cell clss = mainRow.createCell(3);
        clss.setCellValue(excelTitle.get(4));
        clss.setCellStyle(style);
        scoreRow.createCell(3).setCellStyle(style);

//            指导老师
        sheet.addMergedRegion(new CellRangeAddress(3,4,4,4));
        Cell teacher = mainRow.createCell(4);
        teacher.setCellValue(excelTitle.get(5));
        teacher.setCellStyle(style);
        scoreRow.createCell(4).setCellStyle(style);

//            老师评分
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, 8));
        Cell teacherScore = mainRow.createCell(5);
        teacherScore.setCellValue(excelTitle.get(6));
        teacherScore.setCellStyle(style);
        mainRow.createCell(6).setCellStyle(style);
        mainRow.createCell(7).setCellStyle(style);
        mainRow.createCell(8).setCellStyle(style);

//            写老师的周报评分
        Cell teacherWeekReportScore = scoreRow.createCell(5);
        teacherWeekReportScore.setCellValue(excelTitle.get(7));
        teacherWeekReportScore.setCellStyle(style);

//            写老师的实习小结评分
        Cell teacherInternSummuryScore = scoreRow.createCell(6);
        teacherInternSummuryScore.setCellValue(excelTitle.get(8));
        teacherInternSummuryScore.setCellStyle(style);
//            老师的实习报告评分
        Cell teacherInternReportScore = scoreRow.createCell(7);
        teacherInternReportScore.setCellValue(excelTitle.get(9));
        teacherInternReportScore.setCellStyle(style);
//            附加分
        Cell additionalScores = scoreRow.createCell(8);
        additionalScores.setCellValue(excelTitle.get(10));
        additionalScores.setCellStyle(style);
//            实习成绩A
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 9, 9));
        Cell scoreA = mainRow.createCell(9);
        scoreA.setCellValue(excelTitle.get(11));
        scoreA.setCellStyle(style);
        scoreRow.createCell(9).setCellStyle(style);

//            企业评分
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 10, 11));
        Cell companyScore = mainRow.createCell(10);
        companyScore.setCellValue(excelTitle.get(12));
        companyScore.setCellStyle(style);
        mainRow.createCell(11).setCellStyle(style);

//            企业周报评分
        Cell companyWeekRoportScore = scoreRow.createCell(10);
        companyWeekRoportScore.setCellValue(excelTitle.get(13));
        companyWeekRoportScore.setCellStyle(style);
//            企业考勤评分
        Cell companyAttendanceScore = scoreRow.createCell(11);
        companyAttendanceScore.setCellValue(excelTitle.get(14));
        companyAttendanceScore.setCellStyle(style);
//            实习成绩B
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 12, 12));
        Cell scoreB = mainRow.createCell(12);
        scoreB.setCellValue(excelTitle.get(15));
        scoreB.setCellStyle(style);
        scoreRow.createCell(12).setCellStyle(style);

//            最终实习成绩
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 13, 14));
        Cell finalScore = mainRow.createCell(13);
        finalScore.setCellValue(excelTitle.get(16));
        finalScore.setCellStyle(style);
        mainRow.createCell(14).setCellStyle(style);
        scoreRow.createCell(13).setCellStyle(style);
        scoreRow.createCell(14).setCellStyle(style);


    }

    @Override
    public void writeToExcel(String grade, String sheetName){

        String fileName = FileUtil.getFileName(grade);
        List<String> excelTitles = getExcelTitles(fileName);
        List<FinalExcelView> records = getRecords(grade);

        // 判断目录是否存在,不存在则创建
        File file = new File(Constant.UPLOAD_DIR);
        if(!file.exists()) {
            file.mkdir();
        }

        System.out.println("开始写入文件>>>>>>>>>>>>");
        Workbook workbook = null;

        if (fileName.toLowerCase().endsWith("xls")) {//2003
            workbook = new HSSFWorkbook();
        }else if(fileName.toLowerCase().endsWith("xlsx")){//2007
            workbook = new XSSFWorkbook();
        }else{
//          logger.debug("invalid file name,should be xls or xlsx");
        }



//        宋体，11，居中,实线边框 ,数据的格式
        Font font3 = workbook.createFont();
        font3.setFontName(Constant.fontName);
        font3.setFontHeightInPoints((short)11);

        CellStyle style3 = workbook.createCellStyle();
        style3.setAlignment(CellStyle.ALIGN_CENTER);
        style3.setFont(font3);
        style3.setBorderBottom(CellStyle.BORDER_THIN);
        style3.setBorderLeft(CellStyle.BORDER_THIN);
        style3.setBorderRight(CellStyle.BORDER_THIN);
        style3.setBorderTop(CellStyle.BORDER_THIN);

//        宋体，11，靠右,实线边框 ,分数的格式
        Font font4 = workbook.createFont();
        font4.setFontName(Constant.fontName);
        font4.setFontHeightInPoints((short)11);

        CellStyle style4 = workbook.createCellStyle();
        style4.setAlignment(CellStyle.ALIGN_RIGHT);
        style4.setFont(font4);
        style4.setBorderBottom(CellStyle.BORDER_THIN);
        style4.setBorderLeft(CellStyle.BORDER_THIN);
        style4.setBorderRight(CellStyle.BORDER_THIN);
        style4.setBorderTop(CellStyle.BORDER_THIN);

//        int rowIndex = 1;//标识位，用于标识sheet的行号

        //遍历数据集，将其写入excel中
        try{


/*            for (int i = 0; i < excelTitle.length; i++) {

                //创建表头单元格,填值
                titleRow.createCell(i).setCellValue(excelTitle[i]);
            }
            System.out.println("表头写入完成>>>>>>>>");
            rowIndex++;

            //循环写入主表数据
            for (Iterator<User> userIterator = userList.iterator(); userIterator.hasNext();) {

                User user = userIterator.next();
                //create sheet row
                Row row = sheet.createRow(rowIndex);
                //create sheet coluum(单元格)
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(employee.getName());
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(employee.getGender());
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(employee.getAge());
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(employee.getDepartment());
                Cell cell4 = row.createCell(4);
                cell4.setCellValue(employee.getSalary());
                Cell cell5 = row.createCell(5);
                cell5.setCellValue(employee.getDate());
                rowIndex++;
            }*/
            System.out.println("主表数据写入完成>>>>>>>>");
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
            System.out.println(filePath + "写入文件成功>>>>>>>>>>>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*    @Override
    public void writeInternshipScoreToExcel(String filePath,List<String> excelTitle, List<User> userList, String sheetName){
        //新建excel报表
        HSSFWorkbook excel = new HSSFWorkbook();
        //添加一个sheet，名字叫"我的POI之旅"
        HSSFSheet hssfSheet = excel.createSheet("我的POI之旅");

        //单元格范围 参数（int firstRow, int lastRow, int firstCol, int lastCol)
        CellRangeAddress cellRangeAddress =new CellRangeAddress(3, 3, 0, 20);

        //在sheet里增加合并单元格
        hssfSheet.addMergedRegion(cellRangeAddress);
        //生成第一行
        Row row = hssfSheet.createRow(3);
        Cell cell = null;
        HSSFCellStyle  cellStyle = null;
        //为每个单元格设置边框，问题就解决了
        for(int i=0;i<=20;i++){
            cell = row.createCell(i);
            if(i == 0){
                cell.setCellValue("合并单元格");
            }
            cellStyle = excel.createCellStyle();
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            //下边框
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            //左边框
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            //上边框
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            //右边框
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cell.setCellStyle(cellStyle);
        }
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream("D:/students.xls");
            excel.write(fout);
            fout.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/


}
