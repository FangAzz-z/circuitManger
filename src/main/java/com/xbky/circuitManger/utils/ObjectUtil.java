package com.xbky.circuitManger.utils;


public class ObjectUtil {
    public static boolean isNull(Object param)
    {
        return param == null || "".equals(param);
    }

    public static boolean isNotNull(Object param)
    {
        return isNull(param);
    }

}
