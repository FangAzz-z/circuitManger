package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.FittingIntoInfo;
import com.xbky.circuitManger.utils.ObjectUtil;

public class FittingIntoInfoDao extends BaseDao{

    public int add(FittingIntoInfo info){
        String sql = "insert into CM_FITTING_INTO_INFO(fitting_no,fitting_name,fitting_model,unit,create_time,update_time)values(?,?,?,?,now(),now())";
        Object [] obj = new Object[]{info.getFittingNo(),info.getFittingName(),info.getFittingModel(),info.getUnit()};
        return super.insert(sql, obj);
    }

    public int modify(FittingIntoInfo info) {
        StringBuffer sql = new StringBuffer("update CM_FITTING_INTO_INFO set update_time = now() ");
        if (ObjectUtil.isNotNull(info.getFittingNo())) {
            sql.append(String.format(",fitting_no = '%s'", info.getFittingNo()));
        }
        if (ObjectUtil.isNotNull(info.getFittingModel())) {
            sql.append(String.format(",fitting_model = '%s'",info.getFittingModel()));
        }
        if (ObjectUtil.isNotNull(info.getFittingName())) {
            sql.append(String.format(",fitting_name = '%s'", info.getFittingName()));
        }
        if (ObjectUtil.isNotNull(info.getUnit())) {
            sql.append(String.format(",unit = '%s'", info.getUnit()));
        }
        sql.append(String.format(" where id = '%s'",info.getId()));
        return super.update(sql.toString(),null);
    }

}
