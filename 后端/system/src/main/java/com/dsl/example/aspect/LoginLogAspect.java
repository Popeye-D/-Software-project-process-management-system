package com.dsl.example.aspect;

import com.alibaba.fastjson.JSONObject;
import com.dsl.example.service.LogService;
import com.dsl.example.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;

@Component
@Aspect
public class LoginLogAspect {


     Log syslog=new Log();
     String name;
    @Autowired
    private LogService logService;


    private Logger logger = LoggerFactory.getLogger(LoginLogAspect.class);

    @Pointcut("execution(public * com.dsl.example.controller.LoginController.authLogin(..))")
    public void login(){}
    @Pointcut("execution(public * com.dsl.example.controller.LoginController.logout(..))")
    public void logout(){}

    @AfterReturning("login()")
    public void afterLogin(JoinPoint joinPoint) throws UnknownHostException {
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        name = username;
        if(username != null) {
            logger.info(username+" successful login at "+ new Timestamp(System.currentTimeMillis()).toString());

            syslog.setContent(opContent(joinPoint));
            syslog.setUser_id(username);
            syslog.setCreateDate(new Timestamp(System.currentTimeMillis()));
            syslog.setSql(null);
            syslog.setOperation("登录");
            syslog.setIpAddress(InetAddress.getLocalHost().toString());
            //PC-20170215CNRU/192.168.1.104截取为192.168.1.104
            logService.addLog(syslog);
        } else {
            logger.info("tourist failed login at "+ new Timestamp(System.currentTimeMillis()).toString());

            syslog.setContent(opContent(joinPoint));
            syslog.setUser_id(username);
            syslog.setCreateDate(new Timestamp(System.currentTimeMillis()));
            syslog.setSql(null);
            syslog.setOperation("登录失败");
            syslog.setIpAddress(InetAddress.getLocalHost().toString());
            logService.addLog(syslog);
        }
    }

    @AfterReturning("logout()")
    public void afterLogout(JoinPoint joinPoint) throws UnknownHostException {
        Session session = SecurityUtils.getSubject().getSession();
        if(session != null) {
            logger.info(name+" logout at "+ new Timestamp(System.currentTimeMillis()).toString());
            syslog.setContent(opContent(joinPoint));
            syslog.setUser_id(name);
            syslog.setCreateDate(new Timestamp(System.currentTimeMillis()));
            syslog.setSql(null);
            syslog.setOperation("退出成功");
            syslog.setIpAddress(InetAddress.getLocalHost().toString());
            logService.addLog(syslog);
        }
    }
    public String opContent(JoinPoint joinPoint){
        //获取类名
        String className=joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        String str="执行了"+className+"类的"+methodName+"方法";
        System.out.println(str);
        return str;
    }


}
