package com.xiao.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName JwtLoginToken.java
 * @Description
 * @createTime 2021年01月21日 00:01:00
 */
public class JwtLoginToken extends AbstractAuthenticationToken {

    //用户主体
    private final Object principal;
    //用户密码
    private Object credentials;


    public JwtLoginToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     * @param principal
     * @param credentials
     */
    public JwtLoginToken(Object principal, Object credentials,
                         Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
