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
        if (ObjectUtil.isNotNull(record.getLowLimit())) {
            sql.append(String.format(",low_limit = '%s'",record.getLowLimit()));
        }
        sql.append(String.format(" where id = '%s'",record.getId()));
        sql.append(" order by update_time desc");
        return super.update(sql.toString(),null);
    }

    public Map<String, Object> queryByExample(CheckFittingRecord record, int pageNo, int pageSize) {
        StringBuffer sql = new StringBuffer("select c.*,f.packaging from CM_CHECK_FITTING_RECORD c left join CM_FITTING_INTO_INFO f on c.fitting_no = f.fitting_no  where 1=1");
        if(ObjectUtil.isNotNull(record.getFittingNo())){
            sql.append(" and LOWER(c.fitting_no) like '%").append(record.getFittingNo().toLowerCase()).append("%' ");
        }
        if(ObjectUtil.isNotNull(record.getFittingModel())){
            sql.append(" and LOWER(c.fitting_model) like '%").append(record.getFittingModel().toLowerCase()).append("%' ");
        }
        if(ObjectUtil.isNotNull(record.getFittingName())){
            sql.append(" and LOWER(c.fitting_name) like '%").append(record.getFittingName().toLowerCase()).append("%' ");
        }
        int count =  super.queryForList(sql.toString(), null).size();
        int total =  (count  +  pageSize  - 1) / pageSize;
        sql.append(String.format(" order by c.update_time desc  limit %s,%s ",pageNo*pageSize,pageSize));
        List<Map<String,Object>> list =  super.queryForList(sql.toString(), null);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", list);
        map.put("total", total);
        return map;
    }

    public List<Map<String,Object>> queryLimitData(){
        StringBuffer sql = new StringBuffer("select * from CM_CHECK_FITTING_RECORD  where 1=1 and fitting_num < low_limit");
        List<Map<String,Object>> list =  super.queryForList(sql.toString(), null);
        return list;
    }

    public Map<String, Object> getRecordByNo(String no) {
        String sql = String.format("select * from CM_CHECK_FITTING_RECORD where fitting_no = '%s'",no);
        return super.queryForMap(sql,null);
    }

    public int getNumByNo(String no) {
        Map map = getRecordByNo(no);
        if(map!=null) {
            return ObjectUtil.getInt(map.get("fitting_num"));
        }
        return 0;
    }

    public int updateFittingNum(int num, String no) {
        String sql = String.format("update CM_CHECK_FITTING_RECORD set fitting_num = fitting_num - %s where fitting_no = '%s'", num, no);
        return super.update(sql, null);
    }



}
