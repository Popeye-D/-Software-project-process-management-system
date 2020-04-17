package com.dsl.example.service;

import com.alibaba.fastjson.JSONObject;

public interface TesterService {
    /**
     * 获取用户信息
     */
    JSONObject getTesterInfo(JSONObject jsonObject);
    JSONObject getAbsentInfo(JSONObject jsonObject);
}
