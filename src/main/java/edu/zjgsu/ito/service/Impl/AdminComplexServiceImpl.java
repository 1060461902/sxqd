package edu.zjgsu.ito.service.Impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import edu.zjgsu.ito.dao.*;
import edu.zjgsu.ito.model.*;
import edu.zjgsu.ito.pojo.Message;
import edu.zjgsu.ito.service.AdminComplexService;
import edu.zjgsu.ito.service.SimpleService;
import edu.zjgsu.ito.utils.Constant;
import edu.zjgsu.ito.utils.FileUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminComplexServiceImpl implements AdminComplexService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    WeightMapper weightMapper;
    @Autowired
    FinalExcelViewMapper finalExcelViewMapper;
    @Autowired
    SummaryMapper summaryMapper;
    @Autowired
    ReportMapper reportMapper;
    @Autowired
    SimpleService simpleService;

    /**
     * 得到这个学生的周报，实习小结，实习报告
     *
     * @param studentId
     * @return
     * @author sawei
     */
    public List<Object> getPdfData(Integer studentId) {
        List<Object> pdfData = new ArrayList<>();
        SummaryExample summaryExample = new SummaryExample();
        summaryExample.or().andStudentIdEqualTo(studentId);
        ReportExample reportExample = new ReportExample();
        reportExample.or().andStudentIdEqualTo(studentId);

        pdfData.addAll(summaryMapper.selectByExample(summaryExample));
        pdfData.addAll(reportMapper.selectByExample(reportExample));

        return pdfData;
    }

    /**
     * 获得zip的文件名字
     * @param studentId
     * @return
     * @author sawei
     */
    public String getZipFileName(Integer studentId) {
        StudentExample studentExample = new StudentExample();
        studentExample.or().andIdEqualTo(studentId);
        UserExample userExample = new UserExample();
        userExample.or().andIdEqualTo(studentMapper.selectByExample(studentExample).get(0).getUserId());

        return userMapper.selectByExample(userExample).get(0).getUserName();
    }

    /**
     *归档
     * @param studentIds
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author sawei
     */
    @Override
    public Message archive(Integer[] studentIds, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = null;
        for (Integer studentId :
                studentIds) {
            Student student = studentMapper.selectByPrimaryKey(studentId);
            student.setDeleteTag(false);
            studentMapper.updateByPrimaryKey(student);
        }
//        每个学生
        Integer[] temp = new Integer[]{1};
        studentIds = temp;

        for (Integer studentId :
                studentIds) {
//            得到这个学生的数据
            List<Object> pdfData = getPdfData(studentId);
//          这个学生的PDF
            for (int j = 0; j < pdfData.size(); j++) {
                Object tempObj = pdfData.get(j);

//                前两个是小结或者报告，后面的是周报
                fileName = j <= 1 ? (((Summary) tempObj).getStatusId().equals(true) ? "实习报告" : "实习小结") : ((Report) tempObj).getWeek();

                File file = FileUtil.getFile(fileName.concat(".pdf"), Constant.PDF_TMP_DIR);

                Document document = new Document(PageSize.A2, 50, 50, 50, 50);
                //必须紧跟在document创建的时候创建PdfWriter,否则导出无数据
                PdfWriter pw = null;

//                写PDF
                pw = PdfWriter.getInstance(document, new FileOutputStream(file.getAbsolutePath()));
                document.open();

                BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
                com.itextpdf.text.Font font = new com.itextpdf.text.Font(baseFont, 10, com.itextpdf.text.Font.NORMAL);
                Paragraph p1 = new Paragraph("第一周周报\n", font);
                Paragraph p = new Paragraph("21世纪，杭州的经济、政治、文化生活都发生了巨大变迁。那么，在该背景下，杭州\n" +
                        "市民的价值观又是什么样的呢？杭州市民的价值观是否具有继承性，呈现着时代的印记，又或者具有相对独立性，呈现出不同于时代的特色呢？为了解杭州市民的价值观，我们着眼于一些社会热点问题，向各个年龄段的杭州市民提问，并从中提炼出对杭州市民的价值观的分析。经过本小组在暑假期间于杭州万象城、杭州市图书馆、杭州市民中心、天街等地的实地调研、问卷访谈和实地访谈，得出了杭州市民的价值观呈现出继承性、相对独立性和多样性等等特性的结论。并且杭州市民的价值观总体趋向于积极正面、乐观奋斗以及独立自主。这也是评价杭州市民总体素质、是否具有上进心、是否有孝心、看问题是否客观、是否文明等等的依据。", font);
                Paragraph p2 = new Paragraph("\n" +
                        "批阅日期：\n" +
                        "评分：\n" +
                        "评语：\n" +
                        "批阅人：\n" +
                        "2017-08-16\n" +
                        "80.0分\n" +
                        "陈泽潇\n" +
                        "调查对象的代表性稍显不足，大学生占调查对象的7成以上，大学生并不能作为杭州市民的代表；数据的表达不直观，最好能用图表的形式表现；数据分析比较粗糙，问卷中的一部分调查题目未出现在结果分析中；结论的得出有待商榷，因对象不具代表性，直接影响到调查结果的可靠性；建议和对策部分不具有针对性和可行性。\n" +
                        "邮箱：\n" +
                        "电话：\n" +
                        "XiaozC@163.com\n" +
                        "13958085656", font);
                p1.setAlignment(Element.ALIGN_CENTER);

                document.add(p1);
                document.add(p);
                document.add(p2);

                document.close();
                pw.flush();
                pw.close();
            }//            for (int j = 0; j < pdfData.size(); j++) {
//            压缩PDF目录
            FileUtil.fileToZip(Constant.PDF_TMP_DIR, Constant.ZIP_DIR, getZipFileName(studentId));
//            删除PDF目录
            FileUtil.deleteDir(new File(Constant.PDF_TMP_DIR));
        }//        for (Integer studentId:

        return Message.createSuc(null);
    }

    /**
     * 对表中数值格式化
     * @param cell
     * @return
     * @author sawei
     */
    public Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
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
     * 读取Excel
     * @param is       文件流
     * @param fileName 文件名
     * @return
     * @author sawei
     */
    public List<List<Object>> readExcel(InputStream is, String fileName, Integer roleId) throws IOException {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        List<Object> record = null;
        List<List<Object>> data = null;

//        取得文件后缀名
        String fileType = fileName.substring(fileName.lastIndexOf("."));
//            根据类型new
        wb = Constant.EXCEL2003L.equals(fileType) ? new HSSFWorkbook(is) : new XSSFWorkbook(is);

        data = new ArrayList<List<Object>>();

//            遍历每一页
        for (int index = 0; index < wb.getNumberOfSheets(); index++) {
            sheet = wb.getSheetAt(index);
//            String sheetName = sheet.getSheetName();

//            遍历当前页的每一行
            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
//                当sheet没有数据时，row = null，报nullPointerException
//                row.getthirdRowNum() == i:去掉第一行的记录
                if (row == null || i == sheet.getFirstRowNum()) {
                    continue;
                }

                record = new ArrayList<Object>();
                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum() - 1; j++) {
//                    得到单元格cell
                    cell = row.getCell(j);

                    record.add(getCellValue(cell));
                }
                data.add(record);
            }//            for (int i = sheet.getthirdRowNum(); i <= sheet.getLastRowNum() ; i++) {
        }//        for (int index = 0; index < wb.getNumberOfSheets(); index++) {

        wb.close();

        return data;
    }

    /**
     * 学生或者老师批量注册
     * @param request
     * @param roleId  角色id
     * @return
     * @author sawei
     */
    @Override
    public Message batchRegister(HttpServletRequest request, Integer roleId) throws Exception{
        InputStream is = null;
        List<Object> record = null;
        List<List<Object>> data = null;

//        接收文件
        MultipartHttpServletRequest multipartRequest = multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("excelFile");

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
                simpleService.studentRegister(String.valueOf(record.get(1)), String.valueOf(record.get(0)),
                        String.valueOf(record.get(2)), String.valueOf(record.get(3)), String.valueOf(record.get(4)));
            } else if (roleId.equals(Constant.TEACHER)) {
//                获取一行记录
                record = data.get(i);
//                    老师注册
                simpleService.teacherRegister(String.valueOf(record.get(0)), String.valueOf(record.get(1)),
                        String.valueOf(record.get(2)));
            }
        }//            for (int i = 0; i < data.size(); i++) {

        return Message.createSuc(null);
    }

    /**
     * 定义Excel表头名字
     * @param fileName
     * @return
     * @author sawei
     */
    public List<String> getExcelTitles(String fileName) {
        String temp = new String();
        List<String> excelTitles = new ArrayList<>();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//可以方便地修改日期格式
        String now = dateFormat.format(date);


        WeightExample weightExample = new WeightExample();
        weightExample.or().andIdIsNotNull();
        Weight weight = weightMapper.selectByExample(weightExample).get(0);

        excelTitles.add(fileName.substring(0, fileName.lastIndexOf(".")));
        excelTitles.add("导出时间：" + now);
        excelTitles.add("学号");
        excelTitles.add("姓名");
        excelTitles.add("班级");
        excelTitles.add("指导老师");
        temp = String.valueOf(100 * weight.getTeacherWeight());
        excelTitles.add("老师评分（" + temp.substring(0, temp.lastIndexOf(".")) + "%)");
        temp = String.valueOf(100 * weight.gettWeekReport());
        excelTitles.add("周报(" + temp.substring(0, temp.lastIndexOf(".")) + "%)");
        temp = String.valueOf(100 * weight.gettSummary());
        excelTitles.add("实习小结(" + temp.substring(0, temp.lastIndexOf(".")) + "%)");
        temp = String.valueOf(100 * weight.gettFinalReport());
        excelTitles.add("实习报告(" + temp.substring(0, temp.lastIndexOf(".")) + "%)");
        excelTitles.add("附加分");
        excelTitles.add("实习成绩A");
        temp = String.valueOf(100 * weight.getCompanyWeight());
        excelTitles.add("企业评分（" + temp.substring(0, temp.lastIndexOf(".")) + "%)");
        temp = String.valueOf(100 * weight.getcWeekReport());
        excelTitles.add("周报（" + temp.substring(0, temp.lastIndexOf(".")) + "%)");
        temp = String.valueOf(100 * weight.getcAttendance());
        excelTitles.add("考勤（" + temp.substring(0, temp.lastIndexOf(".")) + "%)");
        excelTitles.add("实习成绩B");
        excelTitles.add("最终实习成绩");

        return excelTitles;
    }

    /**
     * 得到学生的记录
     * @param studentIds
     * @return
     * @author sawei
     */
    public List<FinalExcelView> getRecords(Integer[] studentIds) {
//        从视图里查出Excel的所有记录
        FinalExcelViewExample example = new FinalExcelViewExample();
        for (Integer temp :
                studentIds) {
            example.or().andIdEqualTo(temp);
        }
        List<FinalExcelView> records = finalExcelViewMapper.selectByExample(example);
//        查询权重
        Weight weight = weightMapper.selectByPrimaryKey(1);

//        计算成绩
        for (FinalExcelView record :
                records) {
            float scoreA = record.gettWeekReport() * weight.gettWeekReport() + record.gettSummary() * weight.gettSummary()
                    + record.gettFinalReport() * weight.gettFinalReport();
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

    /**
     * 设置Excel的表头
     * @param workbook
     * @param sheet
     * @param excelTitles
     * @author sawei
     */
    public void setTitles(Workbook workbook, Sheet sheet, List<String> excelTitles) {
        sheet.setColumnWidth(1, 13 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 9 * 256);
        sheet.setColumnWidth(4, 9 * 256);
        sheet.setColumnWidth(5, 11 * 256);
        sheet.setColumnWidth(6, 11 * 256);
        sheet.setColumnWidth(7, 11 * 256);
        sheet.setColumnWidth(8, 11 * 256);
        sheet.setColumnWidth(9, 11 * 256);
        sheet.setColumnWidth(10, 11 * 256);
        sheet.setColumnWidth(11, 11 * 256);
        sheet.setColumnWidth(12, 11 * 256);
        sheet.setColumnWidth(13, 10 * 256);
        sheet.setColumnWidth(14, 10 * 256);

        CellStyle styleRight = workbook.createCellStyle();
        CellStyle styleLeft = workbook.createCellStyle();

        //        宋体，11，加粗，居中,实线边框 ,字段的格式
        Font font = workbook.createFont();
        font.setFontName(Constant.fontName);
        font.setFontHeightInPoints((short) 11);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(font);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        //设置自动换行
        style.setWrapText(true);

        //        宋体，11，居中 日期的格式
        Font font1 = workbook.createFont();
        font1.setFontName(Constant.fontName);
        font1.setFontHeightInPoints((short) 11);

        CellStyle style1 = workbook.createCellStyle();
        style1.setAlignment(CellStyle.ALIGN_CENTER);
        style1.setFont(font1);


        //        宋体，16，居中，标题的格式
        Font font2 = workbook.createFont();
        font2.setFontName(Constant.fontName);
        font2.setFontHeightInPoints((short) 16);

        CellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(CellStyle.ALIGN_CENTER);
        style2.setFont(font2);


//        生成行
        Row firstRow = sheet.createRow(1);
        Row secondRow = sheet.createRow(2);
        Row thirdRow = sheet.createRow(3);
        Row forthRow = sheet.createRow(4);


        //            设置右边框
        styleRight.setBorderBottom(CellStyle.BORDER_NONE);
        styleRight.setBorderLeft(CellStyle.BORDER_THICK);
        styleRight.setBorderRight(CellStyle.BORDER_NONE);
        styleRight.setBorderTop(CellStyle.BORDER_NONE);
        firstRow.createCell(15).setCellStyle(styleRight);
        secondRow.createCell(15).setCellStyle(styleRight);
        thirdRow.createCell(15).setCellStyle(styleRight);
        forthRow.createCell(15).setCellStyle(styleRight);

        //            设置左边框
        styleLeft.setBorderBottom(CellStyle.BORDER_NONE);
        styleLeft.setBorderLeft(CellStyle.BORDER_NONE);
        styleLeft.setBorderRight(CellStyle.BORDER_THICK);
        styleLeft.setBorderTop(CellStyle.BORDER_NONE);
        firstRow.createCell(0).setCellStyle(styleLeft);
        secondRow.createCell(0).setCellStyle(styleLeft);
        thirdRow.createCell(0).setCellStyle(styleLeft);
        forthRow.createCell(0).setCellStyle(styleLeft);

//        写标题
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 14));
        Cell cell1 = firstRow.createCell(1);
        cell1.setCellValue(excelTitles.get(0));
        cell1.setCellStyle(style2);


//            写导出日期
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 2));
        Cell cell2 = secondRow.createCell(1);
        cell2.setCellValue(excelTitles.get(1));
        cell2.setCellStyle(style1);

//            写学号
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 1, 1));
        Cell cell3 = thirdRow.createCell(1);
        cell3.setCellValue(excelTitles.get(2));
        cell3.setCellStyle(style);
        forthRow.createCell(1).setCellStyle(style);

//            姓名
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 2, 2));
        Cell cell4 = thirdRow.createCell(2);
        cell4.setCellValue(excelTitles.get(3));
        cell4.setCellStyle(style);
        forthRow.createCell(2).setCellStyle(style);

//            班级
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 3, 3));
        Cell cell5 = thirdRow.createCell(3);
        cell5.setCellValue(excelTitles.get(4));
        cell5.setCellStyle(style);
        forthRow.createCell(3).setCellStyle(style);

//            指导老师
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 4, 4));
        Cell cell6 = thirdRow.createCell(4);
        cell6.setCellValue(excelTitles.get(5));
        cell6.setCellStyle(style);
        forthRow.createCell(4).setCellStyle(style);

//            老师评分
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, 8));
        Cell cell7 = thirdRow.createCell(5);
        cell7.setCellValue(excelTitles.get(6));
        cell7.setCellStyle(style);
        thirdRow.createCell(6).setCellStyle(style);
        thirdRow.createCell(7).setCellStyle(style);
        thirdRow.createCell(8).setCellStyle(style);

//            写老师的周报评分
        Cell cell8 = forthRow.createCell(5);
        cell8.setCellValue(excelTitles.get(7));
        cell8.setCellStyle(style);

//            写老师的实习小结评分
        Cell cell9 = forthRow.createCell(6);
        cell9.setCellValue(excelTitles.get(8));
        cell9.setCellStyle(style);
//            老师的实习报告评分
        Cell cell10 = forthRow.createCell(7);
        cell10.setCellValue(excelTitles.get(9));
        cell10.setCellStyle(style);
//            附加分
        Cell cell11 = forthRow.createCell(8);
        cell11.setCellValue(excelTitles.get(10));
        cell11.setCellStyle(style);
//            实习成绩A
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 9, 9));
        Cell cell12 = thirdRow.createCell(9);
        cell12.setCellValue(excelTitles.get(11));
        cell12.setCellStyle(style);
        forthRow.createCell(9).setCellStyle(style);

//            企业评分
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 10, 11));
        Cell cell13 = thirdRow.createCell(10);
        cell13.setCellValue(excelTitles.get(12));
        cell13.setCellStyle(style);
        thirdRow.createCell(11).setCellStyle(style);

//            企业周报评分
        Cell cell14 = forthRow.createCell(10);
        cell14.setCellValue(excelTitles.get(13));
        cell14.setCellStyle(style);
//            企业考勤评分
        Cell cell15 = forthRow.createCell(11);
        cell15.setCellValue(excelTitles.get(14));
        cell15.setCellStyle(style);
//            实习成绩B
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 12, 12));
        Cell cell16 = thirdRow.createCell(12);
        cell16.setCellValue(excelTitles.get(15));
        cell16.setCellStyle(style);
        forthRow.createCell(12).setCellStyle(style);

//            最终实习成绩
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 13, 14));
        Cell cell17 = thirdRow.createCell(13);
        cell17.setCellValue(excelTitles.get(16));
        cell17.setCellStyle(style);
        thirdRow.createCell(14).setCellStyle(style);
        forthRow.createCell(13).setCellStyle(style);
        forthRow.createCell(14).setCellStyle(style);

    }

    /**
     * 向workbook写入数据
     * @param workbook
     * @param sheet
     * @param records
     * @author sawei
     */
    public void writeData(Workbook workbook, Sheet sheet, List<FinalExcelView> records) {
        int rowIndex = 5;
        CellStyle styleTop = workbook.createCellStyle();
        CellStyle styleBottom = workbook.createCellStyle();
        CellStyle styleLeft = workbook.createCellStyle();
        CellStyle styleRight = workbook.createCellStyle();

        //        宋体，11，居中,实线边框 ,数据的格式
        Font font1 = workbook.createFont();
        font1.setFontName(Constant.fontName);
        font1.setFontHeightInPoints((short) 11);

        CellStyle style1 = workbook.createCellStyle();
        style1.setAlignment(CellStyle.ALIGN_CENTER);
        style1.setFont(font1);
        style1.setBorderBottom(CellStyle.BORDER_THIN);
        style1.setBorderLeft(CellStyle.BORDER_THIN);
        style1.setBorderRight(CellStyle.BORDER_THIN);
        style1.setBorderTop(CellStyle.BORDER_THIN);


//        宋体，11，靠右,实线边框 ,分数的格式
        Font font2 = workbook.createFont();
        font2.setFontName(Constant.fontName);
        font2.setFontHeightInPoints((short) 11);

        CellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(CellStyle.ALIGN_RIGHT);
        style2.setFont(font2);
        style2.setBorderBottom(CellStyle.BORDER_THIN);
        style2.setBorderLeft(CellStyle.BORDER_THIN);
        style2.setBorderRight(CellStyle.BORDER_THIN);
        style2.setBorderTop(CellStyle.BORDER_THIN);

        DataFormat df = workbook.createDataFormat();

        for (FinalExcelView record :
                records) {
            Row row = sheet.createRow(rowIndex);

            //create sheet coluum(单元格)
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(record.getUserName());
            cell1.setCellStyle(style1);
//            设置左边框
            styleLeft.setBorderBottom(CellStyle.BORDER_NONE);
            styleLeft.setBorderLeft(CellStyle.BORDER_NONE);
            styleLeft.setBorderRight(CellStyle.BORDER_THICK);
            styleLeft.setBorderTop(CellStyle.BORDER_NONE);
            row.createCell(0).setCellStyle(styleLeft);

            Cell cell2 = row.createCell(2);
            cell2.setCellValue(record.getNickName());
            cell2.setCellStyle(style1);

            Cell cell3 = row.createCell(3);
            cell3.setCellValue(record.getClss());
            cell3.setCellStyle(style1);

            Cell cell4 = row.createCell(4);
            cell4.setCellValue(record.getTeacherName());
            cell4.setCellStyle(style1);
//                分数部分
            Cell cell5 = row.createCell(5);
//            style2.setDataFormat(df.getFormat("#,#0"));//数据格式只显示整数
            cell5.setCellStyle(style2);
            cell5.setCellValue(record.gettWeekReport());

            Cell cell6 = row.createCell(6);
            cell6.setCellStyle(style2);
            cell6.setCellValue(record.gettSummary());

            Cell cell7 = row.createCell(7);
            cell7.setCellStyle(style2);
            cell7.setCellValue(record.gettFinalReport());

            Cell cell8 = row.createCell(8);
            cell8.setCellStyle(style2);
            cell8.setCellValue(record.getAdditionalScore());

            Cell cell9 = row.createCell(9);
            cell9.setCellStyle(style2);
            cell9.setCellValue(Double.parseDouble(record.getScoreA().toString()));

            Cell cell10 = row.createCell(10);
            cell10.setCellStyle(style2);
            cell10.setCellValue(record.getcWeekReport());

            Cell cell11 = row.createCell(11);
            cell11.setCellStyle(style2);
            cell11.setCellValue(record.getcAttendance());

            Cell cell12 = row.createCell(12);
            cell12.setCellStyle(style2);
            cell12.setCellValue(Double.parseDouble(record.getScoreB().toString()));

            sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 13, 14));
            Cell cell13 = row.createCell(13);
            Cell cell14 = row.createCell(14);
            style2.setAlignment(CellStyle.ALIGN_RIGHT);
            style2.setDataFormat(df.getFormat("#,##0.0"));//数据格式只显示小数后一位
            cell13.setCellStyle(style2);
            cell14.setCellStyle(style2);
            cell13.setCellValue(Double.parseDouble(record.getFinalScore().toString()));
//            设置右边框
            styleRight.setBorderBottom(CellStyle.BORDER_NONE);
            styleRight.setBorderLeft(CellStyle.BORDER_THICK);
            styleRight.setBorderRight(CellStyle.BORDER_NONE);
            styleRight.setBorderTop(CellStyle.BORDER_NONE);
            row.createCell(15).setCellStyle(styleRight);

            rowIndex++;
        }

//        设置最外层边框

//        上边框
        Row rowZero = sheet.createRow(0);
        for (int i = 1; i <= 14; i++) {
            styleTop.setBorderBottom(CellStyle.BORDER_THICK);
            styleTop.setBorderLeft(CellStyle.BORDER_NONE);
            styleTop.setBorderRight(CellStyle.BORDER_NONE);
            styleTop.setBorderTop(CellStyle.BORDER_NONE);

            Cell cellTemp = rowZero.createCell(i);
            cellTemp.setCellStyle(styleTop);
        }


//        下边框
        Row rowFinal = sheet.createRow(rowIndex);
        for (int i = 1; i <= 14; i++) {
            styleBottom.setBorderBottom(CellStyle.BORDER_NONE);
            styleBottom.setBorderLeft(CellStyle.BORDER_NONE);
            styleBottom.setBorderRight(CellStyle.BORDER_NONE);
            styleBottom.setBorderTop(CellStyle.BORDER_THICK);

            Cell cellTemp = rowFinal.createCell(i);
            cellTemp.setCellStyle(styleBottom);
        }


    }

    /**
     *输出Excel到本地
     * @param sheetName
     * @param studentIds 要导出的学生id
     * @param response
     * @return
     * @author sawei
     */
    @Override
    public Message writeToExcel(String sheetName, Integer[] studentIds, HttpServletResponse response) throws Exception {

        String fileName = Constant.EXCEL_FILE_NAME;
        List<String> excelTitles = getExcelTitles(fileName);
        List<FinalExcelView> records = getRecords(studentIds);
        Map<String, Object> data = new HashMap<String, Object>();

//        得到文件
        File file = FileUtil.getFile(fileName, Constant.EXCEL_DIR);

        Workbook workbook = null;
        workbook = fileName.toLowerCase().endsWith("xls") ? new HSSFWorkbook() : new XSSFWorkbook();

//        create sheet
        Sheet sheet = workbook.createSheet(sheetName);
//        列宽自适应
        sheet.autoSizeColumn(1, true);
//        设置表头
        setTitles(workbook, sheet, excelTitles);
//        写数据
        writeData(workbook, sheet, records);

        FileOutputStream fos = null;

        fos = new FileOutputStream(file.getAbsolutePath());
        workbook.write(fos);
        fos.close();

        data.put("fileName", fileName);
        return Message.createSuc(data);
    }
}
