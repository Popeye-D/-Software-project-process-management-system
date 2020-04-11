package com.dsl.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: dsl
 * @description: 项目经理Service
 * @date: 2020/4/1
 */
public interface ManagerService {

    /**
     * 新增用户
     */
    JSONObject addMember(JSONObject jsonObject);

    /**
     * 用户列表
     */
    JSONObject listMember(JSONObject jsonObject);

    JSONObject getStuInfo(JSONObject jsonObject);


    /**
     * 更新用户信息
     */
    JSONObject updateMember(JSONObject jsonObject);

    /**
     * 删除用户
     */
    JSONObject deleteMember(JSONObject jsonObject);

    /**
     * 新增缺勤
     */
    JSONObject addAbsent(JSONObject jsonObject);

    /**
     * 用户缺勤列表
     */
    JSONObject listAbsent(JSONObject jsonObject);

    JSONObject getAbsentInfo(JSONObject jsonObject);

    /**
     * 更新缺勤信息
     */
    JSONObject updateAbsent(JSONObject jsonObject);

    /**
     * 删除缺勤
     */
    JSONObject deleteAbsent(JSONObject jsonObject);
}
