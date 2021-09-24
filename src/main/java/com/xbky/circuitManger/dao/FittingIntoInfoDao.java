package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.FittingIntoInfo;
import com.xbky.circuitManger.importexcel.BaseFaultShowImportObj;
import com.xbky.circuitManger.importexcel.FittingIntoInfoImportObj;
import com.xbky.circuitManger.utils.ObjectUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FittingIntoInfoDao extends BaseDao{

    public int add(FittingIntoInfo info){
        String sql = String.format("insert into CM_FITTING_INTO_INFO(fitting_no,fitting_name,fitting_model,packaging,factory,unit,create_time,update_time)values('%s','%s','%s','%s','%s','%s',now(),now())",info.getFittingNo(),info.getFittingName(),info.getFittingModel(),info.getPackaging(),info.getFactory(),info.getUnit());
        return super.insert(sql, null);
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
        if (ObjectUtil.isNotNull(info.getPackaging())) {
            sql.append(String.format(",packaging = '%s'", info.getPackaging()));
        }
        if (ObjectUtil.isNotNull(info.getFactory())) {
            sql.append(String.format(",factory = '%s'", info.getFactory()));
        }
        if (ObjectUtil.isNotNull(info.getUnit())) {
            sql.append(String.format(",unit = '%s'", info.getUnit()));
        }
        sql.append(String.format(" where id = '%s'",info.getId()));
        return super.update(sql.toString(),null);
    }

    public List<Map<String,Object>> queryByExample(FittingIntoInfo fii){
        StringBuffer sql = new StringBuffer("select id,fitting_no,fitting_model,fitting_name,factory,unit,create_time,update_time from CM_FITTING_INTO_INFO where 1=1 ");
        if(ObjectUtil.isNotNull(fii.getFittingNo())){
            sql.append(String.format(" and fitting_no like '%s'",fii.getFittingNo()+"%"));
        }
        if(ObjectUtil.isNotNull(fii.getFittingModel())){
            sql.append(String.format(" and fitting_model like '%s'",fii.getFittingModel()+"%"));
        }
        if(ObjectUtil.isNotNull(fii.getFittingName())){
            sql.append(String.format(" and fitting_name like '%s'",fii.getFittingName()+"%"));
        }
        if (ObjectUtil.isNotNull(fii.getPackaging())) {
            sql.append(String.format(" and packaging like '%s'",fii.getPackaging()+"%"));
        }
        if(ObjectUtil.isNotNull(fii.getFactory())){
            sql.append(String.format(" and factory like '%s'",fii.getFactory()+"%"));
        }
        if(ObjectUtil.isNotNull(fii.getUnit())){
            sql.append(String.format(" and unit = '%s'",fii.getUnit()));
        }
        sql.append(" order by update_time desc");
        return super.queryForList(sql.toString(), null);
    }

    public boolean isExistSome(FittingIntoInfo fii){
        StringBuffer sql = new StringBuffer("select id,fitting_no,fitting_model,fitting_name,factory,unit,create_time,update_time from CM_FITTING_INTO_INFO where 1=1 ");
        if(ObjectUtil.isNotNull(fii.getFittingNo())){
            sql.append(String.format(" and fitting_no = '%s'",fii.getFittingNo()));
        }
        if(ObjectUtil.isNotNull(fii.getFittingModel())){
            sql.append(String.format(" and fitting_model = '%s'",fii.getFittingModel()));
        }
        if(ObjectUtil.isNotNull(fii.getFittingName())){
            sql.append(String.format(" and fitting_name = '%s'",fii.getFittingName()));
        }
        sql.append(" order by update_time desc");
        Boolean result = ObjectUtil.isNotNull(super.queryForList(sql.toString(), null))?true:false;
        return result;
    }

    public Map<String,Object> queryByExample(FittingIntoInfo fii,int pageNo,int pageSize){
        StringBuffer sql = new StringBuffer("select id,fitting_no,fitting_model,fitting_name,factory,unit from CM_FITTING_INTO_INFO where 1=1 ");
        if(ObjectUtil.isNotNull(fii.getFittingNo())){
            sql.append(String.format(" and fitting_no like '%s'",fii.getFittingNo()+"%"));
        }
        if(ObjectUtil.isNotNull(fii.getFittingModel())){
            sql.append(String.format(" and fitting_model like '%s'",fii.getFittingModel()+"%"));
        }
        if(ObjectUtil.isNotNull(fii.getFittingName())){
            sql.append(String.format(" and fitting_name like '%s'",fii.getFittingName()+"%"));
        }
        if (ObjectUtil.isNotNull(fii.getPackaging())) {
            sql.append(String.format(" and packaging like '%s'",fii.getPackaging()+"%"));
        }
        if(ObjectUtil.isNotNull(fii.getFactory())){
            sql.append(String.format(" and factory like '%s'",fii.getFactory()+"%"));
        }
        if(ObjectUtil.isNotNull(fii.getUnit())){
            sql.append(String.format(" and unit = '%s'",fii.getUnit()));
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

    public int  batchInsert(List<FittingIntoInfoImportObj> data){
        if (CollectionUtils.isEmpty(data)) {
            return 0;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("insert into ").append("CM_FITTING_INTO_INFO").append(" (fitting_no,fitting_name,fitting_model,packaging,factory,unit,create_time,update_time) values ");

        for (FittingIntoInfoImportObj obj : data) {
            sb.append(String.format("('%s','%s','%s','%s','%s','%s',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP()),",obj.getFittingNo(),obj.getFittingName(),obj.getFittingModel(),obj.getPackaging(),obj.getFactory(),obj.getUnit()));
        }
        return super.update(sb.substring(0, sb.length() - 1).toString(), null);
    }

}
