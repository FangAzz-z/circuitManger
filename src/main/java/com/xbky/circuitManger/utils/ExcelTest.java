package com.xbky.circuitManger.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.ExcelReader;
//import com.alibaba.excel.ExcelWriter;
//import com.alibaba.excel.read.listener.ReadListener;
//import com.alibaba.excel.read.metadata.ReadSheet;
//import com.alibaba.excel.write.metadata.WriteSheet;
//
//import java.io.File;
//import java.util.Arrays;
//import java.util.List;
//
public class ExcelTest {
//
//    public static <T> void readFromExcel(File file, T obj, ReadListener listener) {
//        ExcelReader excelReader = EasyExcel.read(file, obj.getClass(), listener).head(obj.getClass()).build();
//        ReadSheet readSheet = EasyExcel.readSheet(0).build();
//        excelReader.read(readSheet);
//        // 关闭流
//        excelReader.finish();
//    }
//
//    public static <T> List<T> readSync(File file, Class<T> headClass) {
//        return EasyExcel.read(file).head(headClass).sheet(0).doReadSync();
//    }
//
//    public static <T> void writeToExcel(String sheetName, File file, T obj, List<T> writeData) {
//        ExcelWriter excelWriter = EasyExcel.write(file, DemoData.class).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();
//        excelWriter.write(writeData,writeSheet);
//        /// 关闭流
//        excelWriter.finish();
//    }
//
//    public static void main(String[] args) {
////        File file = new File("D:\\work\\test.xlsx");
//
////        DemoData data = new DemoData();
////        data.setName("hah");
////        data.setYear("2021");
////        data.setStatus("有效");
////
////        writeToExcel("测试", file, DemoData.class, Arrays.asList(data));
//
////        List<DemoData> result = readSync(file, DemoData.class);
////
////        System.out.println(result.get(0).getName());
 //   }

    public static void sheet() throws IOException {
        //定义一个工作蒲
        Workbook wb = new HSSFWorkbook();
        //创建sheet页面
        Sheet sheet = wb.createSheet("学生信息sheet页");
        //创建一行
        Row row = sheet.createRow(0);
        //创建一个单元格
        Cell cell =null;
        for(int i = 0 ;i<5;i++){
            row.createCell(i).setCellValue("写入信息：单元格内容"+i);
        }

        //定义一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("D:/workspace/test2.xls");
        //写入在输出流
        wb.write(fileOutputStream);
        //关闭输出流
        fileOutputStream.close();
    }

}
