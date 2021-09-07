package com.xbky.circuitManger.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

public class ObjectUtil {
    public static String standTimeFormat = "yyyy-MM-dd HH:mm:ss";
    public static String EMPTY_STRING = "";

    public static boolean isNull(Object param)
    {
        return param == null || "".equals(param);
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


    public static Date parseFromDateStr(String dateStr) {
        return parseFromDateStr(dateStr, standTimeFormat);
    }

    public static Date parseFromDateStr(String dateStr, String dateFormat)  {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isNotNull(Object param)
    {
        return !isNull(param);
    }

    /**
     * 参数集是否有参数为null
     * @param param 参数集
     * @return true，有；false，没有
     */
    public static boolean hasNull(Object... param)
    {
        for (Object str : param)
        {
            if (isNull(str))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 参数集是否全为null
     * @param param 参数集
     * @return true，有；false，没有
     */
    public static boolean isAllNull(Object... param)
    {
        for (Object str : param)
        {
            if (isNotNull(str))
            {
                return false;
            }
        }
        return true;
    }

    public static long getLong(Object obj)
    {
        return isNull(obj) ? null : Long.parseLong(obj.toString());
    }
    public static int getInt(Object obj)
    {
        return isNull(obj) ? null : Integer.parseInt(obj.toString());
    }
    public static String getString(Object obj)
    {
        return obj == null ? null : obj.toString();
    }

    public  static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static <E> boolean isNotEmpty(Collection<E> coll)
    {
        return coll != null && !coll.isEmpty();
    }

    public static <E> boolean isEmpty(Collection<E> coll)
    {
        return !isNotEmpty(coll);
    }

}
