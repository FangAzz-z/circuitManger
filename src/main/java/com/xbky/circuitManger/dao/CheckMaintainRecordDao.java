package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.CheckMaintainRecord;
import com.xbky.circuitManger.utils.IdMaker;
import com.xbky.circuitManger.utils.ObjectUtil;

public class CheckMaintainRecordDao extends BaseDao {
    public int add(CheckMaintainRecord record){
        String sql = "insert into CM_CHECK_MAINTAIN_RECORD(maintain_id,receive_date,complete_date,maintain_card_no,maintain_card_model,maintain_card_category,maintain_card_brand,maintain_user,maintain_desc,maintain_fitting,create_time,update_time)values(?,?,?,?,?,?,?,?,?,?,now(),now())";
        Object[] obj = new Object[]{IdMaker.instance.getStringId("D"), ObjectUtil.dateFormatForStand(record.getReceiveDate()),
                ObjectUtil.dateFormatForStand(record.getCompleteDate()),record.getMaintainCardNo(),record.getMaintainCardModel(),
                record.getMaintainCardCategory(),record.getMaintainCardBrand(),record.getMaintainUser(),
                record.getMaintainDesc(),record.getMaintainFitting()};
        return super.insert(sql,obj);
    }
    public int modify(CheckMaintainRecord record){
        StringBuffer sql = new StringBuffer("update CM_CHECK_FITTING_RECORD set update_time = now() ");
        if (ObjectUtil.isNotNull(record.getMaintainId())) {
            sql.append(String.format(",maintain_id = '%s'",record.getMaintainId()));
        }
        if (ObjectUtil.isNotNull(record.getReceiveDate())) {
            sql.append(String.format(",receive_date = '%s'",ObjectUtil.dateFormatForStand(record.getReceiveDate())));
        }
        if (ObjectUtil.isNotNull(record.getCompleteDate())) {
            sql.append(String.format(",complete_date = '%s'",ObjectUtil.dateFormatForStand(record.getCompleteDate())));
        }
        if (ObjectUtil.isNotNull(record.getMaintainCardNo())) {
            sql.append(String.format(",maintain_card_no = '%s'",record.getMaintainCardNo()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainCardModel())) {
            sql.append(String.format(",maintain_card_model = '%s'",record.getMaintainCardModel()));
        }
        if(ObjectUtil.isNotNull(record.getMaintainCardCategory())){
            sql.append(String.format(",maintain_card_category = '%s'",record.getMaintainCardCategory()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainCardBrand())) {
            sql.append(String.format(",maintain_card_brand = '%s'",record.getMaintainCardBrand()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainUser())) {
            sql.append(String.format(",maintain_user = '%s'",record.getMaintainUser()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainDesc())) {
            sql.append(String.format(",maintain_desc = '%s'",record.getMaintainDesc()));
        }
        if (ObjectUtil.isNotNull(record.getWxStatus())) {
            sql.append(String.format(",wx_status = '%s'",record.getWxStatus()));
        }
        if (ObjectUtil.isNotNull(record.getWxShow())) {
            sql.append(String.format(",wx_show = '%s'",record.getWxShow()));
        }
        if (ObjectUtil.isNotNull(record.getWxMethod())) {
            sql.append(String.format(",wx_method = '%s'",record.getWxMethod()));
        }
        if (ObjectUtil.isNotNull(record.getWxResult())) {
            sql.append(String.format(",wx_result = '%s'",record.getWxResult()));
        }
        if (ObjectUtil.isNotNull(record.getMaintainFitting())) {
            sql.append(String.format(",maintain_fitting = '%s'",record.getMaintainFitting()));
        }
        sql.append(String.format(" where id = '%s'",record.getId()));
        return super.update(sql.toString(),null);
    }
}
