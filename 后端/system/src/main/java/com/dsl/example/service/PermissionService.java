package com.dsl.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: dsl
 * @date: 2019/5/1
 */
public interface PermissionService {
	/**
	 * 查询某用户的 角色  菜单列表   权限列表
	 */
	JSONObject getUserPermission(String username);
}
