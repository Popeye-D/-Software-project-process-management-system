package com.dsl.example.dao;

import com.alibaba.fastjson.JSONObject;
import com.dsl.example.aspect.Log;

import java.util.List;

public interface LogDao {
    /**
     * 查询所有的系统日志
     */
    List<JSONObject> listLog(JSONObject jsonObject);

    /**
     * 统计日志总数
     */
    int countLog(JSONObject jsonObject);
    /**
     * 新增系统日志
     */
    int addLog(Log log);

}
