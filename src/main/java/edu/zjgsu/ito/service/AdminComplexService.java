package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.FinalExcelView;
import edu.zjgsu.ito.model.Student;
import edu.zjgsu.ito.model.Teacher;
import edu.zjgsu.ito.model.User;
import edu.zjgsu.ito.pojo.Message;
import org.apache.poi.ss.usermodel.Cell;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AdminComplexService {

    Message batchRegister(HttpServletRequest request, Integer roleId) throws Exception;

    Message writeToExcel(String sheetName, Integer[] studentIds, HttpServletResponse response) throws Exception;

    Message archive(Integer[] studentIds, HttpServletRequest request, HttpServletResponse response) throws Exception;

    Message confirmShow(String iid, Integer showStatus);

    Message showCompanies(String number);
}
