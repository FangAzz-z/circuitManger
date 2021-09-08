package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.CheckMaintainRecord;
import com.xbky.circuitManger.utils.IdMaker;
import com.xbky.circuitManger.utils.ObjectUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckMaintainRecordDao extends BaseDao {
    public int add(CheckMaintainRecord record) {
        String sql = String.format("insert into CM_CHECK_MAINTAIN_RECORD(maintain_id,receive_date,complete_date,maintain_card_no,maintain_card_model," +
                "maintain_card_category,maintain_card_brand,maintain_user,maintain_desc,maintain_fitting,wx_status,wx_show,wx_method," +
                "wx_result,create_time,update_time)values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s',now(),now())",
                ObjectUtil.getWxId(), record.getReceiveDate(),
                record.getCompleteDate(), record.getMaintainCardNo(), record.getMaintainCardModel(),
                record.getMaintainCardCategory(), record.getMaintainCardBrand(), record.getMaintainUser(),
                record.getMaintainDesc(), record.getMaintainFitting(),record.getWxStatus(),record.getWxShow(),record.getWxMethod(),record.getWxResult());
        return super.insert(sql, null);
    }

    public List<Map<String,Object>> queryAll(){
        StringBuffer sql = new StringBuffer("select id,maintain_id as maintainId,receive_date receiveDate,complete_date completeDate," +
                "maintain_card_no maintainCardNo,maintain_card_model maintainCardModel,maintain_card_category maintainCardCategory," +
                "maintain_card_brand maintainCardBrand,maintain_user maintainUser,maintain_desc " +
                "maintainDesc,wx_status wxStatus,wx_show wxShow,wx_method wxMethod,wx_result wxResult,maintain_fitting maintainFitting " +
                "from CM_CHECK_MAINTAIN_RECORD where 1=1 order by update_time desc");
        return super.queryForList(sql.toString(), null);
    }

    public int modify(CheckMaintainRecord record) {
        StringBuffer sql = new StringBuffer("update CM_CHECK_MAINTAIN_RECORD set update_time = now() ");
        if (ObjectUtil.isNotNull(record.getMaintainId())) {
            sql.append(String.format(",maintain_id = '%s'", record.getMaintainId()));
        }
        if (ObjectUtil.isNotNull(record.getReceiveDate())) {
            sql.append(String.format(",receive_date = '%s'", record.getReceiveDate()));
        }
        if (ObjectUtil.isNotNull(record.getCompleteDate())) {
            sql.append(String.format(",complete_date = '%s'", record.getCompleteDate()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainCardNo())) {
            sql.append(String.format(",maintain_card_no = '%s'", record.getMaintainCardNo()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainCardModel())) {
            sql.append(String.format(",maintain_card_model = '%s'", record.getMaintainCardModel()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainCardCategory())) {
            sql.append(String.format(",maintain_card_category = '%s'", record.getMaintainCardCategory()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainCardBrand())) {
            sql.append(String.format(",maintain_card_brand = '%s'", record.getMaintainCardBrand()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainUser())) {
            sql.append(String.format(",maintain_user = '%s'", record.getMaintainUser()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainDesc())) {
            sql.append(String.format(",maintain_desc = '%s'", record.getMaintainDesc()));
        }
        if (ObjectUtil.isNotNull(record.getWxStatus())) {
            sql.append(String.format(",wx_status = '%s'", record.getWxStatus()));
        }
        if (ObjectUtil.isNotNull(record.getWxShow())) {
            sql.append(String.format(",wx_show = '%s'", record.getWxShow()));
        }
        if (ObjectUtil.isNotNull(record.getWxMethod())) {
            sql.append(String.format(",wx_method = '%s'", record.getWxMethod()));
        }
        if (ObjectUtil.isNotNull(record.getWxResult())) {
            sql.append(String.format(",wx_result = '%s'", record.getWxResult()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainFitting())) {
            sql.append(String.format(",maintain_fitting = '%s'", record.getMaintainFitting()));
        }
        sql.append(String.format(" where id = '%s'", record.getId()));
        sql.append(" order by update_time desc");
        return super.update(sql.toString(), null);
    }

    public List<Map<String, Object>> queryByExample(CheckMaintainRecord record){
        StringBuffer sql = new StringBuffer("select id, maintain_id maintainId,receive_date receiveDate,complete_date completeDate," +
                "maintain_card_no maintainCardNo,maintain_card_model maintainCardModel,maintain_card_category maintainCardCategory," +
                "maintain_card_brand maintainCardBrand,maintain_user maintainUser,maintain_desc " +
                "maintainDesc,wx_status wxStatus,wx_show wxShow,wx_method wxMethod,wx_result wxResult,maintain_fitting maintainFitting " +
                "from CM_CHECK_MAINTAIN_RECORD where 1=1 ");
        if(ObjectUtil.isNotNull(record.getMaintainId())){
            sql.append(String.format(" and maintain_id = '%s'",record.getMaintainId()));
        }
        if(ObjectUtil.isNotNull(record.getReceiveStartTime())&&ObjectUtil.isNotNull(record.getReceiveEndTime())){
            sql.append(String.format(" and receive_date between '%s' and '%s' ",record.getReceiveStartTime(),record.getReceiveEndTime()));
        }
        if (ObjectUtil.isNotNull(record.getCompleteStartTime()) && ObjectUtil.isNotNull(record.getCompleteEndTime())) {
            sql.append(String.format(" and complete_date between '%s' and '%s' ",record.getCompleteStartTime(),record.getCompleteEndTime()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainCardNo())) {
            sql.append(String.format(" and maintain_card_no like '%s'",record.getMaintainCardNo()+"%"));
        }
        if(ObjectUtil.isNotNull(record.getMaintainCardModel())){
            sql.append(String.format(" and maintain_card_model = '%s'",record.getMaintainCardModel()));
        }
        if(ObjectUtil.isNotNull(record.getMaintainCardCategory())){
            sql.append(String.format(" and maintain_card_category = '%s'",record.getMaintainCardCategory()));
        }
        if(ObjectUtil.isNotNull(record.getMaintainCardBrand())){
            sql.append(String.format(" and maintain_card_brand = '%s'",record.getMaintainCardBrand()));
        }
        if(ObjectUtil.isNotNull(record.getWxStatus())){
            sql.append(String.format(" and wxStatus = '%s'",record.getWxStatus()));
        }
        sql.append(" order by update_time desc");
        return super.queryForList(sql.toString(), null);
    }

    public Map<String, Object> queryByExample(CheckMaintainRecord record,int pageNo,int pageSize){
        StringBuffer sql = new StringBuffer("select id, maintain_id maintainId,receive_date receiveDate,complete_date completeDate," +
                "maintain_card_no maintainCardNo,maintain_card_model maintainCardModel,maintain_card_category maintainCardCategory," +
                "maintain_card_brand maintainCardBrand,maintain_user maintainUser,maintain_desc " +
                "maintainDesc,wx_status wxStatus,wx_show wxShow,wx_method wxMethod,wx_result wxResult,maintain_fitting maintainFitting " +
                "from CM_CHECK_MAINTAIN_RECORD where 1=1 ");
        if(ObjectUtil.isNotNull(record.getMaintainId())){
            sql.append(String.format(" and maintain_id = '%s'",record.getMaintainId()));
        }
        if(ObjectUtil.isNotNull(record.getReceiveStartTime())&&ObjectUtil.isNotNull(record.getReceiveEndTime())){
            sql.append(String.format(" and receive_date between '%s' and '%s' ",record.getReceiveStartTime(),record.getReceiveEndTime()));
        }
        if (ObjectUtil.isNotNull(record.getCompleteStartTime()) && ObjectUtil.isNotNull(record.getCompleteEndTime())) {
            sql.append(String.format(" and complete_date between '%s' and '%s' ",record.getCompleteStartTime(),record.getCompleteEndTime()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainCardNo())) {
            sql.append(String.format(" and maintain_card_no like '%s'",record.getMaintainCardNo()+"%"));
        }
        if(ObjectUtil.isNotNull(record.getMaintainCardModel())){
            sql.append(String.format(" and maintain_card_model = '%s'",record.getMaintainCardModel()));
        }
        if(ObjectUtil.isNotNull(record.getMaintainCardCategory())){
            sql.append(String.format(" and maintain_card_category = '%s'",record.getMaintainCardCategory()));
        }
        if(ObjectUtil.isNotNull(record.getMaintainCardBrand())){
            sql.append(String.format(" and maintain_card_brand = '%s'",record.getMaintainCardBrand()));
        }
        if(ObjectUtil.isNotNull(record.getWxStatus())){
            sql.append(String.format(" and wxStatus = '%s'",record.getWxStatus()));
        }
        int count =  super.queryForList(sql.toString(), null).size();
        int total =  (count  +  pageSize  - 1) / pageSize;
        sql.append(String.format(" order by update_time desc  limit %s,%s ",pageNo*pageSize,pageSize));
        List<Map<String,Object>> list =  super.queryForList(sql.toString(), null);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", list);
        map.put("total", total);
        return map;
    }
}
