package com.dsl.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface ErrorLogDao {
    /**
     * 查询所有的系统日志
     */
    List<JSONObject> listErrorLog(JSONObject jsonObject);

    /**
     * 统计日志总数
     */
    int countErrorLog(JSONObject jsonObject);

}
