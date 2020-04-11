package com.dsl.example.controller;


import com.alibaba.fastjson.JSONObject;
import com.dsl.example.service.LogService;
import com.dsl.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    LogService logService;

    @RequiresPermissions("log:list")
    @GetMapping("/listLog")
    public JSONObject listLog(HttpServletRequest request) {
        return logService.listLog(CommonUtil.request2Json(request));
    }

//    @RequiresPermissions("log:add")
//    @PostMapping("/addLog")
//    public JSONObject addLog(Log log) {
////        CommonUtil.hasAllRequired(requestJson, "content");**
//        return logService.addLog(log);
//    }
}
