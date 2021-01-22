package com.xiao.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName JwtAccessDecisionManager.java
 * @Description
 * @createTime 2021年01月21日 23:57:00
 */
@Component
@Slf4j
public class JwtAccessDecisionManager implements AccessDecisionManager {
    /**
     *
     * @param authentication 当前用户凭证 -- > JwtTokenFilter中将通过验证的用户保存在Security上下文中, 即传入了这里
     * @param object 当前请求路径
     * @param configAttributes 当前请求路径所需要的角色列表 -- > 从 JwtFilterInvocationSecurityMetadataSource 返回
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        log.debug("==> 权限校验{}", configAttributes.toString());
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
