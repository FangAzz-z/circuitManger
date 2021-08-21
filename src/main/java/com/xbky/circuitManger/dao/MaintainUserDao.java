package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.MaintainUser;
import com.xbky.circuitManger.utils.ObjectUtil;

public class MaintainUserDao extends BaseDao{
    public int add(MaintainUser mu){
        String sql = "insert into CM_MAINTAIN_USER(name,department,job,phone,create_time,update_time)values(?,?,?,?,now(),now());";
        Object [] obj = new Object[]{mu.getName(),mu.getDepartment(),mu.getJob(),mu.getPhone()};
        return super.insert(sql, obj);
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
        if (ObjectUtil.isNotNull(mu.getPhone())) {
            sql.append(String.format(",phone = '%s'",mu.getPhone()));
        }
        sql.append(String.format(" where id = '%s'"));
        return super.update(sql.toString(), null);
    }
}
