package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.CheckFittingRecord;
import com.xbky.circuitManger.utils.ObjectUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckFittingRecordDao extends BaseDao{
    public int add(CheckFittingRecord record){
        String sql0 = "insert into CM_CHECK_FITTING_RECORD(fitting_model,fitting_num,fitting_no,fitting_name,low_limit,create_time,update_time)values('%s','%s','%s','%s','%s',now(),now())";
        String sql = String.format(sql0,record.getFittingModel(),record.getFittingNum(),record.getFittingNo(),record.getFittingName(),record.getLowLimit());
        return super.insert(sql,null);
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

    public Map<String, Object> queryByExample(CheckFittingRecord record, int pageNo, int pageSize) {
        StringBuffer sql = new StringBuffer("select * from CM_CHECK_FITTING_RECORD where 1=1");
        if(ObjectUtil.isNotNull(record.getFittingNo())){
            sql.append(" and LOWER(fitting_no) like '%").append(record.getFittingNo().toLowerCase()).append("%' ");
        }
        if(ObjectUtil.isNotNull(record.getFittingNo())){
            sql.append(" and LOWER(fitting_model) like '%").append(record.getFittingModel().toLowerCase()).append("%' ");
        }
        if(ObjectUtil.isNotNull(record.getFittingNo())){
            sql.append(" and LOWER(fitting_name) like '%").append(record.getFittingName().toLowerCase()).append("%' ");
        }
        int count =  super.queryForList(sql.toString(), null).size();
        int total =  (count  +  pageSize  - 1) / pageSize;
        sql.append(String.format(" order by update_time desc  limit %s,%s ",pageNo*pageSize,pageSize));
        List<Map<String,Object>> list =  super.queryForList(sql.toString(), null);
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        map.put("total", total);
        return map;
    }
}
