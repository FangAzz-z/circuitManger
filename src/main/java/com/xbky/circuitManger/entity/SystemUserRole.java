package com.xbky.circuitManger.entity;

public class SystemUserRole {
    /**
     *  唯一主键
     */
    private Long id;
    private String userName;
    private String moduleName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
