package com.dsl.example.aspect;

import com.alibaba.fastjson.JSONObject;
import com.dsl.example.service.LogService;
import com.dsl.example.util.constants.Constants;
import org.apache.ibatis.session.SqlSessionFactory;
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
public class OperateLogAspect {

    private Log syslog=new Log();
    @Autowired
    private LogService logService;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private Logger logger = LoggerFactory.getLogger(LoginLogAspect.class);

/*
*管理员，项目经理操作
*/
    /*用户列表显示*/
    @Pointcut("execution(public * com.dsl.example.controller.UserController.listUser(..))")
        public void UserListCell() {
    }
    @AfterReturning("UserListCell()")
    public void listUser(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.UserDao.listUser").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"用户列表点击",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*添加用户显示*/
    @Pointcut("execution(public * com.dsl.example.controller.UserController.addUser(..))")
    public void UserAddCell() {
    }
    @AfterReturning("UserAddCell()")
    public void addUser(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.UserDao.addUser").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"添加用户操作",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*更新用户*/
    @Pointcut("execution(public * com.dsl.example.controller.UserController.updateUser(..))")
    public void UserUpdateCell() {
    }
    @AfterReturning("UserUpdateCell()")
    public void updateUser(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.UserDao.updateUser").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"更新用户操作",InetAddress.getLocalHost().toString(),sql);
            logService.addLog(syslog);
        }
    }
    /*角色列表*/
    @Pointcut("execution(public * com.dsl.example.controller.UserController.listRole(..))")
    public void RoleListCell() {
    }
    @AfterReturning("RoleListCell()")
    public void listRole(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.UserDao.listRole").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"角色列表点击",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*角色添加*/
    @Pointcut("execution(public * com.dsl.example.controller.UserController.addRole(..))")
    public void RoleAddCell() {
    }
    @AfterReturning("RoleAddCell()")
    public void addRole(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.UserDao.addRole").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"角色添加操作",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*更新角色*/
    @Pointcut("execution(public * com.dsl.example.controller.UserController.updateRole(..))")
    public void RoleUpdateCell() {
    }
    @AfterReturning("RoleUpdateCell()")
    public void updateRole(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.UserDao.updateRoleName").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"更新角色操作",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*删除角色*/
    @Pointcut("execution(public * com.dsl.example.controller.UserController.deleteRole(..))")
    public void RoleDeleteCell() {
    }
    @AfterReturning("RoleDeleteCell()")
    public void deleteRole(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.UserDao.deleteRole").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"角色删除操作",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*角色列表显示*/
    @Pointcut("execution(public * com.dsl.example.controller.UserController.getAllRoles(..))")
    public void getAllRolesCell() {
    }
    @AfterReturning("getAllRolesCell()")
    public void getAllRoles(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.UserDao.getAllRoles").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"获取所有角色操作",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*角色与权限列表显示*/
    @Pointcut("execution(public * com.dsl.example.controller.UserController.listAllPermission(..))")
    public void listAllPermissionCell() {
    }
    @AfterReturning("listAllPermissionCell()")
    public void listAllPermission(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.UserDao.listAllPermission").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"角色权限表显示",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*日志列表操作*/
    @Pointcut("execution(public * com.dsl.example.controller.LogController.listLog(..))")
    public void listLog() {
    }
    @AfterReturning("listLog()")
    public void listLog(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.LogDao.listLog").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"日志查看点击",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }

    /*
    * 管理员操作
    */
    /*Member列表*/
    @Pointcut("execution(public * com.dsl.example.controller.ManagerController.listMember(..))")
    public void listMember() {
    }
    @AfterReturning("listMember()")
    public void listMember(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.ManagerDao.listMember").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"用户管理点击",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*Member添加*/
    @Pointcut("execution(public * com.dsl.example.controller.ManagerController.addMember(..))")
    public void addMember() {
    }
    @AfterReturning("addMember()")
    public void addMember(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.ManagerDao.addMember").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"添加用户操作",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*更新用户*/
    @Pointcut("execution(public * com.dsl.example.controller.ManagerController.updateMember(..))")
    public void updateMember() {
    }
    @AfterReturning("updateMember()")
    public void updateMember(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.ManagerDao.updateMember").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"更新用户信息操作",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*删除用户*/
    @Pointcut("execution(public * com.dsl.example.controller.ManagerController.deleteMember(..))")
    public void deleteMember() {
    }
    @AfterReturning("deleteMember()")
    public void deleteMember(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.ManagerDao.deleteMember").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"删除用户信息操作",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*缺勤列表*/
    @Pointcut("execution(public * com.dsl.example.controller.ManagerController.listAbsent(..))")
    public void listAbsent() {
    }
    @AfterReturning("listAbsent()")
    public void listAbsent(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.ManagerDao.listAbsent").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"缺勤列表点击",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*缺勤添加*/
    @Pointcut("execution(public * com.dsl.example.controller.ManagerController.addAbsent(..))")
    public void addAbsent() {
    }
    @AfterReturning("addAbsent()")
    public void addAbsent(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.ManagerDao.addAbsent").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"添加缺勤记录操作",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*用户列表*/
    @Pointcut("execution(public * com.dsl.example.controller.ManagerController.updateAbsent(..))")
    public void updateAbsent() {
    }
    @AfterReturning("updateAbsent()")
    public void updateAbsent(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.ManagerDao.updateAbsent").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"更新缺勤记录操作",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*用户列表*/
    @Pointcut("execution(public * com.dsl.example.controller.ManagerController.deleteAbsent(..))")
    public void deleteAbsent() {
    }
    @AfterReturning("deleteAbsent()")
    public void deleteAbsent(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.ManagerDao.deleteAbsent").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"删除缺勤记录操作",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /*
    * 用户操作
    */
    /*用户信息列表*/
    @Pointcut("execution(public * com.dsl.example.controller.MemberController.getStuInfo(..))")
    public void getStuInfoCell() {
    }
    @AfterReturning("getStuInfoCell()")
    public void getStuInfo(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.MemberDao.getStuInfo").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"获取用户个人信息点击",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }
    /**获取个人缺勤记录*/
    @Pointcut("execution(public * com.dsl.example.controller.MemberController.getAbsentInfo(..))")
    public void getAbsentInfo() {
    }
    @AfterReturning("getAbsentInfo()")
    public void getAbsentInfo(JoinPoint joinPoint) throws UnknownHostException{
//        logger.info(username+" "+methodName+" "+ new Timestamp(System.currentTimeMillis()).toString());
        if(getSessionContent() != null) {
            syslog.setContent(opContent(joinPoint));
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.dsl.example.dao.MemberDao.getAbsentInfo").getBoundSql(null).getSql();
//            System.out.println(sql);
            setSyslog(getSessionContent(),new Timestamp(System.currentTimeMillis()),"获取用户缺勤记录点击",InetAddress.getLocalHost().toString(),sql.trim());
            logService.addLog(syslog);
        }
    }



    /*设置日志内容**/
    public void setSyslog(String username,Timestamp timestamp,String operation,String ip,String sql) {
        syslog.setUser_id(username);
        syslog.setCreateDate(timestamp);
        syslog.setOperation(operation.replaceAll("\r|\n", ""));
        syslog.setIpAddress(ip);
        syslog.setSql(sql);
    }
    /*获取session中的用户名*/
    public String getSessionContent(){
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        return username;
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
