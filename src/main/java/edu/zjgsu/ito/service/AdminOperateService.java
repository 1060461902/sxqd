package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.FinalExcelView;
import edu.zjgsu.ito.model.Student;
import edu.zjgsu.ito.model.Teacher;
import edu.zjgsu.ito.model.User;
import org.apache.poi.ss.usermodel.Cell;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AdminOperateService {

    Map<String, Object> batchRegister(HttpServletRequest request, Integer roleId);

    List<List<Object>> readExcel(InputStream is, String fileName, Integer roleId);

    Object getCellValue(Cell cell);

    Integer userRegister(User user);

    Integer teacherRegister(String userName, String nickName, String major);

    Integer studentRegister(String userName, String nickName, String major, String clss);

    void  writeToExcel(String grade, String sheetName);

    List<String> getExcelTitles(String fileName);

    List<FinalExcelView> getRecords(String grade);

}
