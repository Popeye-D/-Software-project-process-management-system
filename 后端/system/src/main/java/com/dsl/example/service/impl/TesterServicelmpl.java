package com.dsl.example.service.impl;



import com.alibaba.fastjson.JSONObject;
import com.dsl.example.dao.TesterDao;
import com.dsl.example.service.TesterService;
import com.dsl.example.util.CommonUtil;
import com.dsl.example.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class TesterServicelmpl {
    @Autowired
    TesterDao testerDao;


    @Override
    public JSONObject getTesterInfo(JSONObject jsonObject) {
//        CommonUtil.fillPageParam(jsonObject);
//        int count = testerDao.countTester(jsonObject);
//        List<JSONObject> list = testerDao.listTester(jsonObject);
//        return CommonUtil.successPage(jsonObject, list, count);
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        testerDao.getTesterInfo(username);
        return CommonUtil.successJson();
    }
    @Override
    public JSONObject getAbsentInfo(JSONObject jsonObject) {
//        CommonUtil.fillPageParam(jsonObject);
//        int count = testerDao.countTester(jsonObject);
//        List<JSONObject> list = testerDao.listTester(jsonObject);
//        return CommonUtil.successPage(jsonObject, list, count);
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        testerDao.getAbsentInfo(username);
        return CommonUtil.successJson();
    }
}
