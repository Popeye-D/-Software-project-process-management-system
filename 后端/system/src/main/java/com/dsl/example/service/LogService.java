package com.dsl.example.service;

import com.alibaba.fastjson.JSONObject;
import com.dsl.example.aspect.Log;

public interface LogService {

    /**
     * 查询所有的系统日志
     *
     */

    JSONObject listLog(JSONObject jsonObject);
    /**
     * 新增系统日志
     *
     *
     */

    JSONObject addLog(Log log);
}
