package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.SystemUserRole;
import com.xbky.circuitManger.utils.ObjectUtil;

import java.util.List;
import java.util.Map;

public class SystemUserRoleDao extends BaseDao{
    public int add(SystemUserRole role){
        String sql = String.format("insert into CM_SYSTEM_USER_MODULE(user_name,module_name,create_time,update_time)values('%s','%s',now(),now())",role.getUserName(),role.getModuleName());
        return super.insert(sql, null);
    }

    public List<Map<String,Object>> getDataByUser(String name){
        String sql = String.format("select * from CM_SYSTEM_USER_MODULE where user_name = '%s'",name);
        return super.queryForList(sql, null);
    }
    public List<Map<String,Object>> getDataByModule(String module){
        String sql = String.format("select * from CM_SYSTEM_USER_MODULE where module_name = '%s'",module);
        return super.queryForList(sql, null);
    }

    public List<Map<String,Object>> queryByExample(SystemUserRole example) {
        StringBuffer sql = new StringBuffer("select * from CM_SYSTEM_USER_MODULE where 1=1 ");
        if(ObjectUtil.isNotNull(example.getUserName())){
            sql.append(String.format(" and user_name = '%s'",example.getUserName()));
        }
        if(ObjectUtil.isNotNull(example.getModuleName())){
            sql.append(String.format(" and model_name = '%s'",example.getModuleName()));
        }
        sql.append(" order by update_time desc");
        return super.queryForList(sql.toString(), null);
    }

}
