package com.xbky.circuitManger.utils;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

public class DemoData {

    @ExcelProperty(value="姓名")
    @ColumnWidth(50)
    private String name;

    @ExcelProperty(value="出生年份")
    @ColumnWidth(20)
    private String year;

    @ExcelProperty(value="状态")
    @ColumnWidth(10)
    private String status;


    public DemoData() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
