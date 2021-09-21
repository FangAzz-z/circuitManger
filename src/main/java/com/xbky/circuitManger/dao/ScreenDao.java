package com.xbky.circuitManger.dao;

import java.util.Map;

public class ScreenDao extends BaseDao {

    public int modify(int isSet,String imageUrl,String fontColor){
        String sql = String.format("update CM_SYSTEM_SET_SCREEN set is_set = '%s',image_url = '%s',font_color = '%s' where id = 1",isSet,imageUrl,fontColor);
        return super.update(sql,null);
    }

    public Map<String,Object> getScreenRecord(){
        String sql = "select * from CM_SYSTEM_SET_SCREEN where id = 1 ";
        return super.queryForMap(sql, null);
    }
}
