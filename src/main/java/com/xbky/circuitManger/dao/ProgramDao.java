package com.xbky.circuitManger.dao;

public class ProgramDao extends BaseDao  {

    public int modifyByName(String programName,String programLocation){
        String sql = String.format("update CM_PROGRAM_LOCATION set program_location = '?' where program_name = '%s'",programLocation,programName);
        return super.update(sql, null);
    }

    public String getLocationByName(String programName) {
        String sql = String.format("select program_location from CM_PROGRAM_LOCATION where program_name = '%s'",programName);
        return (String)super.queryForMap(sql, null).get("program_location");
    }
}
