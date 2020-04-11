package com.dsl.example.dao;


import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: dsl
 * @description: 用户Dao层
 * @date: 2020/4/1
 */
public interface MemberDao {
    JSONObject getStuInfo(String username);
    JSONObject getAbsentInfo(String username);
    /**
     * 统计用户信息总数
     */
    int countMember(String username);

    /**
     * 用户信息列表
     */
    List<JSONObject> listMember(String username);
}
