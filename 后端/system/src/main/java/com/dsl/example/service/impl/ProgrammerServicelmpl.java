package com.dsl.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dsl.example.dao.ProgrammerDao;
import com.dsl.example.service.ProgrammerService;
import com.dsl.example.util.CommonUtil;
import com.dsl.example.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class ProgrammerServicelmpl {
    @Autowired
    ProgrammerDao programmerDao;


    @Override
    public JSONObject getProgrammerInfo(JSONObject jsonObject) {
//        CommonUtil.fillPageParam(jsonObject);
//        int count = programmerDao.countProgrammer(jsonObject);
//        List<JSONObject> list = programmerDao.listProgrammer(jsonObject);
//        return CommonUtil.successPage(jsonObject, list, count);
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        programmerDao.getProgrammerInfo(username);
        return CommonUtil.successJson();
    }
    @Override
    public JSONObject getAbsentInfo(JSONObject jsonObject) {
//        CommonUtil.fillPageParam(jsonObject);
//        int count = programmerDao.countProgrammer(jsonObject);
//        List<JSONObject> list = programmerDao.listProgrammer(jsonObject);
//        return CommonUtil.successPage(jsonObject, list, count);
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        programmerDao.getAbsentInfo(username);
        return CommonUtil.successJson();
    }
}
