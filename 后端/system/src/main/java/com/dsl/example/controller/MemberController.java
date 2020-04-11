package com.dsl.example.controller;


import com.alibaba.fastjson.JSONObject;
import com.dsl.example.service.ManagerService;
import com.dsl.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private ManagerService managerService;
    /**
     * 查询个人信息列表
     */
    @RequiresPermissions("member:select")
    @GetMapping("/getStuInfo")
    public JSONObject getStuInfo(HttpServletRequest request) {
        return managerService.getStuInfo(CommonUtil.request2Json(request));
    }
    /*
    * 查询自己的缺勤记录
    */
    @RequiresPermissions("absent:select")
    @GetMapping("/getAbsentInfo")
    public JSONObject getAbsentInfo(HttpServletRequest request) {
        return managerService.getAbsentInfo(CommonUtil.request2Json(request));
    }
}
