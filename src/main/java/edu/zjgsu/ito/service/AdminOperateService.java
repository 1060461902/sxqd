package edu.zjgsu.ito.service;

import org.apache.poi.ss.usermodel.Cell;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface AdminOperateService {

    Map<String, Object> studentBatchRegister(HttpServletRequest request);
    Map<String, Object> teacherBatchRegister(String filePath);

    List<List<Object>> readExcel(InputStream is, String fileName);

    Object getCellValue(Cell cell);

}
