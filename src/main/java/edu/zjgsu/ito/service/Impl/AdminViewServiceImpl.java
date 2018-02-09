package edu.zjgsu.ito.service.Impl;


import edu.zjgsu.ito.dao.CompanyMapper;
import edu.zjgsu.ito.dao.RecruitmentMapper;
import edu.zjgsu.ito.dao.TableDataMapper;
import edu.zjgsu.ito.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class AdminViewServiceImpl extends TimerTask {
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    RecruitmentMapper recruitmentMapper;
    @Autowired
    TableDataMapper tableDataMapper;

    public List<TableData> getTableData() {
        List<TableData> tableDataList = tableDataMapper.selectByExample(new TableDataExample());
        return tableDataList;
    }
    /**
     * 定时一个月更新首页报表数据
     */
    @Override
    public void run() {
        Long companyNum;
        Long currentRecruitNum;
        Long currentStudentNum = 0L;

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");//可以方便地修改日期格式
        String now = dateFormat.format(date);

        companyNum = companyMapper.countByExample(new CompanyExample());
        currentRecruitNum = recruitmentMapper.countByExample(new RecruitmentExample());
        
        List<Recruitment> recruitmentList = recruitmentMapper.selectByExample(new RecruitmentExample());

        for (Recruitment recruitment:
             recruitmentList) {
            currentStudentNum += recruitment.getTotalNumber();
        }

        TableData tableData = new TableData(now, companyNum,currentRecruitNum,currentStudentNum);
        tableDataMapper.insert(tableData);
    }
}
