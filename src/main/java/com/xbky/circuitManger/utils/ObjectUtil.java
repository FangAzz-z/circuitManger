package com.xbky.circuitManger.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ObjectUtil {
    private static String standTimeFormat = "yyyy-MM-dd HH:mm:ss";
    public static boolean isNull(Object param)
    {
        return param == null || "".equals(param);
    }

    public static boolean isNotNull(Object param)
    {
        return isNull(param);
    }

    /**
     * 格式化日间  （英文环境）
     * @param time 日间
     * @param format 格式化样式
     * @return 字符串时间格式
     */
    public static String dateFormatEn(Date time, String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        return sdf.format(time);
    }

    public static String dateFormatForStand(Date time) {
        return dateFormatEn(time, standTimeFormat);
    }

}
