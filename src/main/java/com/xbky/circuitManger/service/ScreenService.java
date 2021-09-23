package com.xbky.circuitManger.service;

import com.xbky.circuitManger.dao.ScreenDao;
import com.xbky.circuitManger.utils.ObjectUtil;

import java.util.Map;

public class ScreenService {

    public ScreenDao dao = new ScreenDao();

    public Integer geIsSet(){
        Map<String,Object> map = dao.getScreenRecord();
        if(ObjectUtil.isNull(map.get("is_set"))){
            return 0;
        }
        return ObjectUtil.getInt(map.get("is_set"));
    }
    public String getImageUrl(){
        Map<String,Object> map = dao.getScreenRecord();
        if(ObjectUtil.isNull(map.get("image_url"))){
            return "";
        }
        return ObjectUtil.getString(map.get("image_url"));
    }

    public String getFontColor(){
        Map<String,Object> map = dao.getScreenRecord();
        if(ObjectUtil.isNull(map.get("font_color"))){
            return "";
        }
        return ObjectUtil.getString(map.get("font_color"));
    }
}

