package com.dsl.example.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.dsl.example.aspect.Log;
import com.dsl.example.dao.LogDao;
import com.dsl.example.service.LogService;
import com.dsl.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: dsl
 * @date: 2020/4/1
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogDao logDao;
    /**
     * 新增日志
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addLog(Log log) {
        logDao.addLog(log);
        return CommonUtil.successJson();
    }

    /**
     * 文章列表
     */
    @Override
    public JSONObject listLog(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = logDao.countLog(jsonObject);
        List<JSONObject> list = logDao.listLog(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }
}
