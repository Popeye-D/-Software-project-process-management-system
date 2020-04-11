package com.dsl.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: dsl
 * @description: 文章Dao层
 * @date: 2020/4/1
 */
public interface ManagerDao {

    /**
     * 新增用户
     */
    int addMember(JSONObject jsonObject);

    /**
     * 统计用户总数
     */
    int countMember(JSONObject jsonObject);
    int countMemberInfo(String username);
    /**
     * 用户列表
     */
    List<JSONObject> listMember(JSONObject jsonObject);
    List<JSONObject> getStuInfo(String username,JSONObject jsonObject);

    /**
     * 更新用户信息
     */
    int updateMember(JSONObject jsonObject);

    /**
     * 删除用户信息
     */
    int deleteMember(JSONObject jsonObject);

    /**
     * 新增缺勤
     */
    int addAbsent(JSONObject jsonObject);

    /**
     * 统计缺勤总数
     */
    int countAbsent(JSONObject jsonObject);
    int countAbsentInfo(String username);
    /**
     * 缺勤列表
     */
    List<JSONObject> listAbsent(JSONObject jsonObject);
    List<JSONObject> getAbsentInfo(String username,JSONObject jsonObject);
    /**
     * 更新缺勤信息
     */
    int updateAbsent(JSONObject jsonObject);

    /**
     * 删除缺勤信息
     */
    int deleteAbsent(JSONObject jsonObject);

    /**
     * 查询某角色的全部数据
     * 在删除和修改角色时调用
     */
    JSONObject getMemberAllInfo(JSONObject jsonObject);
}
