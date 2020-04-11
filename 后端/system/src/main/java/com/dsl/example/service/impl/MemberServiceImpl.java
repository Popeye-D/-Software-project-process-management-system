package com.dsl.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dsl.example.dao.MemberDao;
import com.dsl.example.service.MemberService;
import com.dsl.example.util.CommonUtil;
import com.dsl.example.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDao memberDao;


    @Override
    public JSONObject getStuInfo(JSONObject jsonObject) {
//        CommonUtil.fillPageParam(jsonObject);
//        int count = memberDao.countMember(jsonObject);
//        List<JSONObject> list = memberDao.listMember(jsonObject);
//        return CommonUtil.successPage(jsonObject, list, count);
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        memberDao.getStuInfo(username);
        return CommonUtil.successJson();
    }
    @Override
    public JSONObject getAbsentInfo(JSONObject jsonObject) {
//        CommonUtil.fillPageParam(jsonObject);
//        int count = memberDao.countMember(jsonObject);
//        List<JSONObject> list = memberDao.listMember(jsonObject);
//        return CommonUtil.successPage(jsonObject, list, count);
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        memberDao.getAbsentInfo(username);
        return CommonUtil.successJson();
    }
}
