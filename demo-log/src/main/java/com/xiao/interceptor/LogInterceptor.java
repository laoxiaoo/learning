package com.xiao.interceptor;

import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author xiao jie
 * @create 2022年01月06日 18:59:00
 */
public class LogInterceptor implements HandlerInterceptor {

    public static final String MDC_CONSTANT = "traceId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put(MDC_CONSTANT, UUID.randomUUID().toString().toUpperCase());
        return Boolean.TRUE;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(MDC_CONSTANT);
    }
}
