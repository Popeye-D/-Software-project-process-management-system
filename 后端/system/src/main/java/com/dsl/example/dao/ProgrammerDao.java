package com.dsl.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface ProgrammerDao {
    JSONObject getProgrammerInfo(String username);
    JSONObject getAbsentInfo(String username);
    /**
     * 统计用户信息总数
     */
    int countProgrammer(String username);

    /**
     * 用户信息列表
     */
    List<JSONObject> listTask(String username);
}

