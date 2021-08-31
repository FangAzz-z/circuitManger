package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.CheckFittingRecord;
import com.xbky.circuitManger.utils.ObjectUtil;

public class CheckFittingRecordDao extends BaseDao{
    public int add(CheckFittingRecord record){
        String sql = "insert into CM_CHECK_FITTING_RECORD(fitting_model,fitting_num,fitting_no,fitting_name,create_time,update_time)values(?,?,?,?,now(),now())";
        Object[] obj = new Object[]{record.getFittingModel(),record.getFittingNum(),record.getFittingNo(),record.getFittingName()};
        return super.insert(sql,obj);
    }
    public int modify(CheckFittingRecord record){
        StringBuffer sql = new StringBuffer("update CM_CHECK_FITTING_RECORD set update_time = now() ");
        if (ObjectUtil.isNotNull(record.getFittingModel())) {
            sql.append(String.format(",fitting_model = '%s'",record.getFittingModel()));
        }
        if (ObjectUtil.isNotNull(record.getFittingNum())) {
            sql.append(String.format(",fitting_num = '%s'",record.getFittingNum()));
        }
        if (ObjectUtil.isNotNull(record.getFittingNo())) {
            sql.append(String.format(",fitting_no = '%s'",record.getFittingNo()));
        }
        if (ObjectUtil.isNotNull(record.getFittingName())) {
            sql.append(String.format(",fitting_name = '%s'",record.getFittingName()));
        }
        sql.append(String.format(" where id = '%s'",record.getId()));
        sql.append(" order by update_time desc");
        return super.update(sql.toString(),null);
    }
}
