package com.xbky.circuitManger.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

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
    public static String getStringV2(Object obj)
    {
        return obj == null ? "" : obj.toString();
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


    public static Long getTodaySeconds() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 0);
        // 坑就在这里
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (System.currentTimeMillis()-cal.getTimeInMillis()) / 1000;
    }

    public static String getWxId(){
        return  "WX" + new SimpleDateFormat("YYMMdd").format(new Date())+"-"+ ObjectUtil.getTodaySeconds();
    }

    public static String getDay()
    {
        Date date = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static boolean isInteger(String str) {
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public static boolean isNotInteger(String str) {
        return !isInteger(str);
    }
}
