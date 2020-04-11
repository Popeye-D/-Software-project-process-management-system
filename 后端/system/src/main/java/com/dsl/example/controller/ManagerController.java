package com.dsl.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.dsl.example.service.ManagerService;
import com.dsl.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: dsl
 * @description: 项目管理员Controller
 * @date: 2020/4/1
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

  @Autowired
  private ManagerService managerService;

  /**
   * 新增用户
   */
  @RequiresPermissions("member:add")
  @PostMapping("/addMember")
  public JSONObject addMember(@RequestBody JSONObject jsonObject){
    CommonUtil.hasAllRequired(jsonObject, "dormID,dormClean");
    return managerService.addMember(jsonObject);
  }

  /**
   * 用户列表
   */
  @RequiresPermissions("member:list")
  @GetMapping("/listMember")
  public JSONObject listMember(HttpServletRequest request) {
      return managerService.listMember(CommonUtil.request2Json(request));
  }

  /**
   * 更新用户信息
   */
  @RequiresPermissions("member:update")
  @PostMapping("/updateMember")
  public JSONObject updateMember(@RequestBody JSONObject jsonObject){
    CommonUtil.hasAllRequired(jsonObject, "dormID,dormClean");
    return managerService.updateMember(jsonObject);
  }

  /**
   * 删除用户信息
   */
  @RequiresPermissions("member:delete")
  @PostMapping("/deleteMember")
  public JSONObject deleteMember(@RequestBody JSONObject jsonObject){
    CommonUtil.hasAllRequired(jsonObject, "id");
    return managerService.deleteMember(jsonObject);
  }

  @RequiresPermissions("absent:add")
  @PostMapping("/addAbsent")
  public JSONObject addAbsent(@RequestBody JSONObject jsonObject){
    CommonUtil.hasAllRequired(jsonObject, "userID,dormTime,message");
    return managerService.addAbsent(jsonObject);
  }

  /**
   * 用户列表
   */
  @RequiresPermissions("absent:list")
  @GetMapping("/listAbsent")
  public JSONObject listAbsent(HttpServletRequest request) {
    return managerService.listAbsent(CommonUtil.request2Json(request));
  }

  /**
   * 更新用户信息
   */
  @RequiresPermissions("absent:update")
  @PostMapping("/updateAbsent")
  public JSONObject updateAbsent(@RequestBody JSONObject jsonObject){
    CommonUtil.hasAllRequired(jsonObject, "userID,dormTime,message");
    return managerService.updateAbsent(jsonObject);
  }

  /**
   * 删除用户信息
   */
  @RequiresPermissions("absent:delete")
  @PostMapping("/deleteAbsent")
  public JSONObject deleteAbsent(@RequestBody JSONObject jsonObject){
    CommonUtil.hasAllRequired(jsonObject, "id");
    return managerService.deleteAbsent(jsonObject);
  }
}
