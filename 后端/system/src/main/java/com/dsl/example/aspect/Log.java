package com.dsl.example.aspect;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Log {
    private int log_id;//id
    private String username;//用户id
    private Timestamp create_time;//新建时间
    private String operation;//操作
    private String content;//内容
    private String sql;//sql语句
    private String ip;//ip

    private String name;//用户名
    private String role_name;//角色

    public int getId() {
        return log_id;
    }
    public void setId(int id) {
        this.log_id = id;
    }
    public String getUser_id() {
        return username;
    }
    public void setUser_id(String user_id) {
        this.username = user_id;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Timestamp getCreateDate() {
        return create_time;
    }
    public void setCreateDate(Timestamp createDate) {
        this.create_time = createDate;
    }
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRole_name() {
        return role_name;
    }
    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
    public String getIpAddress() {
        return ip;
    }
    public void setIpAddress(String ipAddress) {
        this.ip = ipAddress;
    }
}
