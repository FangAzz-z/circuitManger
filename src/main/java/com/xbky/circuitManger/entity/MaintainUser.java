package com.xbky.circuitManger.entity;

import java.util.Date;

public class
MaintainUser {

    private Long id;

    private String name;

    private String sex;

    private String department;

    private String job;

    private String phone;

    private Date createTime;

    private Date updateTime;
    public MaintainUser(){

    }
    public MaintainUser(Long id,String name,String sex,String department,String job,String phone){
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.department = department;
        this.job = job;
        this.phone = phone;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
