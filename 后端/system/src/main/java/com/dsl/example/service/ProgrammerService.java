package com.dsl.example.service;

import com.alibaba.fastjson.JSONObject;

public interface ProgrammerService {

    /**
     * 获取用户信息
     */
    JSONObject getProgrammerInfo(JSONObject jsonObject);
    JSONObject getAbsentInfo(JSONObject jsonObject);
}
