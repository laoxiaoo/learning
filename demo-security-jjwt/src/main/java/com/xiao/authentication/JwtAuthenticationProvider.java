package com.xiao.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName JwtAuthenticationProvider.java
 * @Description
 * @createTime 2021年01月21日 21:15:00
 */
@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        log.debug("==> 获取用户密码");
        //转换authentication
        JwtLoginToken jwtLoginToken = (JwtLoginToken) authentication;
        additionalAuthenticationChecks(userDetails, jwtLoginToken);
        //构造已认证的authentication
        JwtLoginToken authenticatedToken = new JwtLoginToken(userDetails, jwtLoginToken.getCredentials(), userDetails.getAuthorities());
        authenticatedToken.setDetails(jwtLoginToken.getDetails());
        return authenticatedToken;
    }

    /**
     * 查看当前authentication是否JwtLoginToken
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return JwtLoginToken.class.isAssignableFrom(authentication);
    }

    /**
     * 检查密码是否正确
     *
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     */
    private void additionalAuthenticationChecks(UserDetails userDetails, JwtLoginToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("Bad credentials");
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
    }
}
