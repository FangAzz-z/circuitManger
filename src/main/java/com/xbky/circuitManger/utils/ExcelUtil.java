package com.xbky.circuitManger.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ExcelUtil {

    public static <T> void readFromExcel(File file, T obj, ReadListener listener) {
        ExcelReader excelReader = EasyExcel.read(file, obj.getClass(), listener).head(obj.getClass()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 关闭流
        excelReader.finish();
    }

    public static <T> List<T> readSync(File file, Class<T> headClass) {
        return EasyExcel.read(file).head(headClass).sheet(0).doReadSync();
    }

    public static <T> void writeToExcel(String sheetName, File file, T obj, List<T> writeData) {
        ExcelWriter excelWriter = EasyExcel.write(file, DemoData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();
        excelWriter.write(writeData,writeSheet);
        /// 关闭流
        excelWriter.finish();
    }

    public static void main(String[] args) {
//        File file = new File("D:\\work\\test.xlsx");

//        DemoData data = new DemoData();
//        data.setName("hah");
//        data.setYear("2021");
//        data.setStatus("有效");
//
//        writeToExcel("测试", file, DemoData.class, Arrays.asList(data));

//        List<DemoData> result = readSync(file, DemoData.class);
//
//        System.out.println(result.get(0).getName());
    }

}
