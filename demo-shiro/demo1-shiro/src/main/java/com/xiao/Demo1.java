package com.xiao;

import com.xiao.realm.MyRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName Demo1.java
 * @Description
 * @createTime 2021年01月12日 21:30:00
 */
@Slf4j
public class Demo1 {

    public static void main(String[] args) {
        //创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //设置配置文件地址
        //securityManager.setRealm(new IniRealm("classpath:shiro.ini"));

        //设置全局安全管理器
        MyRealm realm = new MyRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1024);
        realm.setCredentialsMatcher(matcher);
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        //创建令牌，相当于登录
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");

        try {
            //登录，认证失败会抛出异常
            subject.login(token);
            //判断是否有权限
            System.out.println(subject.hasRole("admin"));;
            System.out.println(subject.isPermitted("zhangsan:create:01"));
        } catch (UnknownAccountException e) {
            System.out.println("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        } catch (Exception e) {

        }
    }
}
