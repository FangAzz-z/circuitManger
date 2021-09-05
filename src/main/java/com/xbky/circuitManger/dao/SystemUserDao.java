package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.MaintainUser;
import com.xbky.circuitManger.entity.SystemUser;
import com.xbky.circuitManger.utils.ObjectUtil;

import java.util.List;
import java.util.Map;

public class SystemUserDao extends BaseDao{

    public int add(SystemUser su){
        String sql = String.format("insert into CM_SYSTEM_USER(user_name,user_password,create_time,update_time)values('%s','%s',now(),now())",su.getUserName(),su.getUserPassword());
        return super.insert(sql, null);
    }

    public List<Map<String,Object>> getUserByName(String name){
        String sql = String.format("select * from CM_SYSTEM_USER where user_name = '%s'",name);
        return super.queryForList(sql, null);
    }

    /**
     * 修改密码
     * @param id
     * @param password
     * @return
     */
    public int modify(String id,String password) {
        StringBuffer sql = new StringBuffer("update CM_SYSTEM_USER set update_time = now() ");
        if (ObjectUtil.isNotNull(password)) {
            sql.append(String.format(",user_password = '%s'",password));
        }
        sql.append(String.format(" where id = '%s'",id));
        return super.update(sql.toString(),null);
    }
}
