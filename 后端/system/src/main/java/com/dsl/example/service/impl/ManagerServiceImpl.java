package com.dsl.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dsl.example.dao.ManagerDao;
import com.dsl.example.dao.UserDao;
import com.dsl.example.service.ManagerService;
import com.dsl.example.util.CommonUtil;
import com.dsl.example.util.constants.Constants;
import com.dsl.example.util.constants.ErrorEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: dsl
 * @description: 管理员ServiceImpl
 * @date: 2020/4/1
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private UserDao userDao;
    /**
     * 新增用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addMember(JSONObject jsonObject) {
        int exist = userDao.queryExistUserID(jsonObject);
        System.out.println(exist);
        if (exist == 0) {
            return CommonUtil.errorJson(ErrorEnum.E_90002);
        }
        managerDao.addMember(jsonObject);
        return CommonUtil.successJson();
    }
    /**
     * 用户列表
     */
    @Override
    public JSONObject listMember(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = managerDao.countMember(jsonObject);
        List<JSONObject> list = managerDao.listMember(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject getStuInfo(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        int count = managerDao.countMemberInfo(username);
        System.out.println(count);
        System.out.println(username);
        List<JSONObject> list = managerDao.getStuInfo(username,jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     * 用户列表
     */
    @Override
    public JSONObject listAbsent(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = managerDao.countAbsent(jsonObject);
        List<JSONObject> list = managerDao.listAbsent(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject getAbsentInfo(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        int count = managerDao.countAbsentInfo(username);
        System.out.println(count);
        System.out.println(username);
        List<JSONObject> list = managerDao.getAbsentInfo(username,jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject updateAbsent(JSONObject jsonObject) {
        managerDao.updateAbsent(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject deleteAbsent(JSONObject jsonObject) {
        managerDao.deleteAbsent(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 更新用户信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateMember(JSONObject jsonObject) {
        managerDao.updateMember(jsonObject);
        return CommonUtil.successJson();
    }
    /**
     * 删除用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject deleteMember(JSONObject jsonObject){
        managerDao.deleteMember(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject addAbsent(JSONObject jsonObject) {
        int exist = userDao.queryExistUserID(jsonObject);
        System.out.println(exist);
        if (exist == 0) {
            return CommonUtil.errorJson(ErrorEnum.E_90002);
        }
        managerDao.addAbsent(jsonObject);
        return CommonUtil.successJson();
    }
}
