package com.xbky.circuitManger.utils;

import com.xbky.circuitManger.annotation.Column;
import com.xbky.circuitManger.annotation.DateFormat;
import com.xbky.circuitManger.view.common.StageManager;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class ExcelUtil {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class.getName()); // 日志打印类

    private static final String SUPPORT_EXTENSION = "xls";

    private static final String FILE_SUFFIX = ".xls";

    public static <T> void writeToExcelFromDataBase(String fileName, File directory, LinkedHashMap<String, String> headMap, List<Map<String, Object>> dataMapList, Class<T> cls) {

        try {
            List<T> dataList = covertDatabaseRecordToEntity(dataMapList, cls);
            writeToExcel(fileName, directory, headMap, dataList);
        } catch (Exception e) {
            logger.error("导出数据库记录到excel错误", e);
        }
    }

    public static <T> List<T> covertDatabaseRecordToEntity(List<Map<String, Object>> dataMapList, Class<T> cls) throws IllegalAccessException, InstantiationException {

        List<T> result = new ArrayList<>(dataMapList.size());
        for (Map<String, Object> data : dataMapList) {

            System.out.println(data.get("create_time"));

            Field[] fields = cls.getDeclaredFields();
            T t = cls.newInstance();
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);

                if (column != null) {
                    String name = column.name();

                    Object value = data.get(name);

                    if (value == null) {
                        continue;
                    }
                    field.setAccessible(true);

                    if (Object.class.equals(Timestamp.class)) {
                        setFieldValue(ObjectUtil.dateFormatForStand((Timestamp) value), field, t);
                    } else {
                        setFieldValue(value.toString(), field, t);
                    }
                }
            }

            result.add(t);
        }

        return result;
    }

    public static <T> void writeToExcel(String fileName, File directory, LinkedHashMap<String, String> headMap, List<T> dataList) {


        if (directory == null || !directory.exists() || !directory.isDirectory() || !directory.canWrite()) {
            StageManager.nullWarn("错误信息提示",String.format("选择的目录 %s 不可用 ", directory == null ? ObjectUtil.EMPTY_STRING : directory.getPath()));
            return;
        }

        String filePath = directory.getPath() + "/" + fileName + ObjectUtil.dateFormatEn(new Date(), "yyyyMMddHHmmss")+ FILE_SUFFIX;

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(fileName);
        // 起始行
        int rowIndex = 0;

        Row headRow = sheet.createRow(rowIndex);
        int headCellIndex = 0;

        Map<String, Integer> fieldIndexMap = new HashMap<>();

        for (Map.Entry<String, String> entry : headMap.entrySet()) {
            headRow.createCell(headCellIndex).setCellValue(entry.getValue());
            fieldIndexMap.put(entry.getKey(), headCellIndex);
            headCellIndex++;
        }

        rowIndex++;

        // IO操作
        FileOutputStream out = null;
        try {
            for (T t : dataList) {
                Field[] fields = t.getClass().getDeclaredFields();
                Row row = sheet.createRow(rowIndex);
                for (Field f : fields) {
                    String filedName = f.getName();

                    Integer index = fieldIndexMap.get(filedName);

                    if (index == null) {
                        continue;
                    }
                    f.setAccessible(true);
                    Cell cell = row.createCell(index);
                    setCellValue(cell, f, f.get(t));
                }
                rowIndex++;
            }

            out = new FileOutputStream(filePath);
            workbook.write(out);// 写文件

            out.flush();

           StageManager.nullWarn("错误信息提示",String.format("导出文件到 %s 成功", filePath));
        } catch (Exception e) {
            logger.error("导出excel文件错误", e);
            StageManager.nullWarn("错误信息提示",String.format("导出excel文件到 %s错误", filePath));
        } finally {
            closeOutStream(out);
            closeWorkbook(workbook);
        }
    }

    private static <T> void setCellValue(Cell cell, Field f, Object value) throws Exception {
        if (value == null) {
            return;
        }

        Type type = f.getType();

        if (type == byte.class || Byte.TYPE.equals(type)) {
            cell.setCellValue(Byte.valueOf(value.toString()));
        } else if (type == Short.TYPE || Short.class.equals(type)) {
            cell.setCellValue(Short.valueOf(value.toString()));
        } else if (type == Integer.TYPE || Integer.class.equals(type)) {
            cell.setCellValue(Integer.valueOf(value.toString()));
        } else if (type == Long.TYPE || Long.class.equals(type)) {
            cell.setCellValue(Long.valueOf(value.toString()));
        } else if (type == Float.TYPE || Float.class.equals(type)) {
            cell.setCellValue(Float.valueOf(value.toString()));
        } else if (type == Double.TYPE || Double.class.equals(type)) {
            cell.setCellValue(Double.valueOf(value.toString()));
        } else if (type == Boolean.TYPE || Boolean.class.equals(type)) {
            cell.setCellValue(Boolean.valueOf(value.toString()));
        } else if (type == Character.TYPE || Character.class.equals(type)) {
            cell.setCellValue(value.toString().charAt(0));
        } else if (String.class.equals(type)) {
            cell.setCellValue(value.toString());
        } else if (Date.class.equals(type)) {
            DateFormat annotation = f.getAnnotation(DateFormat.class);
            if (annotation != null) {
                cell.setCellValue(ObjectUtil.dateFormatEn((Date) value, annotation.format()));
            }
        } else {
            throw new RuntimeException("不支持的类型 " + type.getTypeName());
        }
    }


    public static <T> List<T> readFromExcel(File file, Map<String, String> headMap, Class<T> cls) {

        if (file == null || !file.exists() || !file.canRead()) {
            return Collections.emptyList();
        }

        Workbook workbook = null;
        FileInputStream inputStream = null;

        try {
            // 获取Excel后缀名
            String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());

            if (!SUPPORT_EXTENSION.equalsIgnoreCase(extension)) {
                throw new RuntimeException("不支持的文件类型  " + extension);
            }

            // 获取Excel工作簿
            inputStream = new FileInputStream(file);
            workbook = new HSSFWorkbook(inputStream);

            // 读取excel中的数据
            return readFromExcel(workbook, headMap, cls);
        } catch (Exception e) {
            logger.error("解析Excel {} 失败 ", file.getPath());
            logger.error("", e);
            StageManager.nullWarn("错误信息提示","解析excel文件失败");
            return Collections.emptyList();
        } finally {
            closeWorkbook(workbook);
            closeInputStream(inputStream);
        }
    }

    private static <T> Map<String, Integer> getFieldIndexMap(Row firstRow, Map<String, String> headMap) {
        Map<String, Integer> resultMap = new HashMap<>(headMap.size());
        int cellIndex = 0;
        while (cellIndex < firstRow.getPhysicalNumberOfCells()) {

            Cell cell = firstRow.getCell(cellIndex);
            if (cell == null) {
                continue;
            }
            String cellValue = cell.getStringCellValue();
            String fileName = headMap.get(cellValue);
            if (ObjectUtil.isBlank(fileName)) {
                continue;
            }
            resultMap.put(fileName, cellIndex);
            cellIndex++;
        }

        return resultMap;
    }

    private static <T> List<T> readFromExcel(Workbook workbook, Map<String, String> headMap, Class<T> cls) {
        List<T> resultDataList = new ArrayList<>();
        // 解析sheet
        Sheet sheet = workbook.getSheetAt(0);

        // 校验sheet是否合法
        if (sheet == null) {
            return Collections.emptyList();
        }

        // 获取第一行数
        int firstRowNum = sheet.getFirstRowNum();
        Row firstRow = sheet.getRow(firstRowNum);
        if (null == firstRow) {
            throw new RuntimeException("excel头文件不存在");
        }

        Map<String, Integer> fieldIndexMap = getFieldIndexMap(firstRow, headMap);

        // 解析每一行的数据，构造数据对象
        int rowStart = firstRowNum + 1;
        while (rowStart < sheet.getPhysicalNumberOfRows()) {
            Row row = sheet.getRow(rowStart);

            if (null == row) {
                continue;
            }

            T resultData = convertRowToData(row, fieldIndexMap, cls);
            if (null == resultData) {
                continue;
            }
            resultDataList.add(resultData);
            rowStart++;
        }
        return resultDataList;
    }

    private static <T> T convertRowToData(Row row, Map<String, Integer> fieldIndexMap, Class<T> cls) {
        try {
            T t = cls.newInstance();
            int cellTotalNum = row.getPhysicalNumberOfCells();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                Integer index = fieldIndexMap.get(field.getName());

                if (index == null || index > cellTotalNum) {
                    continue;
                }
                String value = convertCellValueToString(row.getCell(index));
                if (ObjectUtil.isBlank(value)) {
                    continue;
                }
                field.setAccessible(true);
                setFieldValue(value, field, t);
            }
            return t;
        } catch (Exception e) {
            logger.error("解析row失败 rowIndex = {}", row.getRowNum());
            logger.error("", e);
            return null;
        }
    }

    private static <T> void setFieldValue(String value, Field field, T t) throws IllegalAccessException {

        if (ObjectUtil.isBlank(value)) {
            return;
        }

        Type type = field.getType();

        if (type == byte.class || Byte.TYPE.equals(type)) {
            field.set(t, Byte.valueOf(value));
        } else if (type == Short.TYPE || Short.class.equals(type)) {
            field.set(t, Short.valueOf(value));
        } else if (type == Integer.TYPE || Integer.class.equals(type)) {
            field.set(t, Integer.valueOf(value));
        } else if (type == Long.TYPE || Long.class.equals(type)) {
            field.set(t, Long.valueOf(value));
        } else if (type == Float.TYPE || Float.class.equals(type)) {
            field.set(t, Float.valueOf(value));
        } else if (type == Double.TYPE || Double.class.equals(type)) {
            field.setDouble(t, Double.valueOf(value));
        } else if (type == Boolean.TYPE || Boolean.class.equals(type)) {
            field.setBoolean(t, Boolean.valueOf(value));
        } else if (type == Character.TYPE || Character.class.equals(type)) {
            field.setChar(t, value.charAt(0));
        } else if (String.class.equals(type)) {
            field.set(t, value);
        } else if (Date.class.equals(type)) {
            DateFormat annotation = field.getAnnotation(DateFormat.class);
            if (annotation != null) {
                field.set(t, ObjectUtil.parseFromDateStr(value, annotation.format()));
            }
        } else {
            throw new RuntimeException("不支持的类型 " + type.getTypeName());
        }
    }

    private static String convertCellValueToString(Cell cell) {
        if (cell == null) {
            return null;
        }
        CellType type = cell.getCellTypeEnum();

        if (CellType.NUMERIC.equals(type)) {
            Double doubleValue = cell.getNumericCellValue();
            // 格式化科学计数法，取一位整数
            DecimalFormat df = new DecimalFormat("0");
            return df.format(doubleValue);
        } else if (CellType.STRING.equals(type)) {
            return cell.getStringCellValue();
        } else if (CellType.BOOLEAN.equals(type)) {
            Boolean bol = cell.getBooleanCellValue();
            return bol == null ? "" : Boolean.toString(bol);
        } else if (CellType.FORMULA.equals(type)) {
            return cell.getCellFormula();
        }
        return null;
    }


    private static void closeWorkbook(Workbook workbook) {
        if (workbook != null) {
            try {
                workbook.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }

    public static void closeOutStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }

    public static void closeInputStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }

    public static void main(String[] args) throws Exception {


        File file = new File("D:\\work\\test.xls");

//        LinkedHashMap<String, String> headMap = new LinkedHashMap<>();
//        headMap.put("name", "名称");
//        headMap.put("year", "出生年份");
//        headMap.put("status", "状态");
//        headMap.put("delete", "删除状态");
//        headMap.put("createTime", "创建时间");
//
//
//        DemoData data = new DemoData();
//        data.setName("haha");
//        data.setYear("2021");
//        data.setStatus(1);
//        data.setCreateTime(new Date());
//        data.setDelete(0);
//
//        writeToExcel("test", file, headMap, Arrays.asList(data));
        Map<String, String> headMap = new HashMap<>();
        headMap.put("名称", "name");
        headMap.put("出生年份", "year");
        headMap.put("状态", "status");
        headMap.put("删除状态", "status");
        headMap.put("创建时间", "createTime");
        List<DemoData> rs = readFromExcel(file, headMap, DemoData.class);

        System.out.println(rs.get(0).getCreateTime());
    }
}

