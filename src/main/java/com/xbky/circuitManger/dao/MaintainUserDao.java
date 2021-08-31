package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.MaintainUser;
import com.xbky.circuitManger.utils.ObjectUtil;

import java.util.List;
import java.util.Map;

public class MaintainUserDao extends BaseDao{
    public int add(MaintainUser mu){
        String sql = String.format("insert into CM_MAINTAIN_USER(name,sex,department,job,phone,create_time,update_time)values('%s','%s','%s','%s','%s',now(),now())",mu.getName(),mu.getSex(),mu.getDepartment(),mu.getJob(),mu.getPhone());
        return super.insert(sql, null);
    }

    public int modify(MaintainUser mu){
        StringBuffer sql = new StringBuffer("update CM_MAINTAIN_USER set update_time = now() ");
        if(ObjectUtil.isNotNull(mu.getName())){
            sql.append(String.format(",name = '%s'",mu.getName()));
        }
        if (ObjectUtil.isNotNull(mu.getDepartment())) {
            sql.append(String.format(",department = '%s'",mu.getDepartment()));
        }
        if (ObjectUtil.isNotNull(mu.getJob())) {
            sql.append(String.format(",job = '%s'",mu.getJob()));
        }
        if (ObjectUtil.isNotNull(mu.getSex())) {
            sql.append(String.format(",sex = '%s'",mu.getSex()));
        }
        if (ObjectUtil.isNotNull(mu.getPhone())) {
            sql.append(String.format(",phone = '%s'",mu.getPhone()));
        }
        sql.append(String.format(" where id = '%s'",mu.getId()));
        return super.update(sql.toString(), null);
    }

    public List<Map<String,Object>> queryByExample(MaintainUser mu){
        StringBuffer sql = new StringBuffer("select id,name,sex,department,job,phone from CM_MAINTAIN_USER where 1=1 ");
        if(ObjectUtil.isNotNull(mu.getName())){
            sql.append(String.format(" and name like '%s'",mu.getName()+"%"));
        }
        if(ObjectUtil.isNotNull(mu.getDepartment())){
            sql.append(String.format(" and department like '%s'",mu.getDepartment()+"%"));
        }
        if(ObjectUtil.isNotNull(mu.getJob())){
            sql.append(String.format(" and job like '%s'",mu.getJob()+"%"));
        }
        if(ObjectUtil.isNotNull(mu.getSex())){
            sql.append(String.format(" and sex = '%s'",mu.getSex()));
        }
        if(ObjectUtil.isNotNull(mu.getPhone())){
            sql.append(String.format(" and phone like '%s'",mu.getPhone()+"%"));
        }
        sql.append(" order by update_time desc");
        return super.queryForList(sql.toString(), null);
    }
}
