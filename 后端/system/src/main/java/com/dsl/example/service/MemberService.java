package com.dsl.example.service;

import com.alibaba.fastjson.JSONObject;

public interface MemberService {
    /**
     * 获取用户信息
     */
    JSONObject getStuInfo(JSONObject jsonObject);
    JSONObject getAbsentInfo(JSONObject jsonObject);


}
