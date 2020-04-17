package com.dsl.example.dao;

//
//}

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: dsl
 * @date: 2020/4/1
 */
public interface TesterDao {
    JSONObject getTesterInfo(String username);
    JSONObject getAbsentInfo(String username);
    /**
     * 统计用户信息总数
     */
    int countTester(String username);

    /**
     * 用户信息列表
     */
    List<JSONObject> listTask(String username);
}
