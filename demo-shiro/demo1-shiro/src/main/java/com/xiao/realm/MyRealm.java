package com.xiao.realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.SetUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName MyRealm.java
 * @Description
 * @createTime 2021年01月12日 23:13:00
 */
public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //用户名
        System.out.println(principals.getPrimaryPrincipal());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //添加角色
        authorizationInfo.addRole("admin");
        //添加资源权限
        authorizationInfo.addStringPermission("zhangsan:create:*");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if(token.getPrincipal().equals("zhangsan")) {
            //模拟返回用户
            return new SimpleAccount("zhangsan", "646bea76fce01bfaec5b9a8bf36b3938", ByteSource.Util.bytes("lonely"), this.getName());
        }
        return null;
    }
}
