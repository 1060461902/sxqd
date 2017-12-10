package edu.zjgsu.ito.utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelStyle {
    private Workbook workbook;
    private CellStyle styleTitle;
    private CellStyle style;
    private CellStyle styleData;

    public ExcelStyle(Workbook workbook) {
        this.workbook = workbook;
    }


}

