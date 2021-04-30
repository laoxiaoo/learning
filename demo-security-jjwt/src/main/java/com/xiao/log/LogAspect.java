package com.xiao.log;

/**
 * @Author:xiao
 * @Date:Created in 2019/10/24 0024 17:49
 */

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 日志切面类
 */
@Aspect
@Component
public class LogAspect {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private ThreadLocal<Long> interfaceTime = new ThreadLocal<>();


    @Pointcut("execution(public * com.xiao.controller..*.*(..))")
    public void controllerMethod() {

    }

    @Before("controllerMethod()")
    public void LogRequestInfo(JoinPoint joinPoint) {
        try {
            interfaceTime.set(System.currentTimeMillis());
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            //获取ip
            //String ipAddr = CommonUtil.getRequestIpAddr(request);
            //请求路径
            String url = request.getRequestURI();
            String userAgent = request.getHeader("User-Agent");
            UserAgent ua = UserAgentUtil.parse(userAgent);

            SysLog sysLog = new SysLog();
            sysLog.setIp("ipAddr");
            sysLog.setWebUrl(url);
            //sysLog.setTermianlType(ua.getOs().toString());
            sysLog.setBrowerType(ua.getBrowser().toString());

            //获取类上注解
            Class targetClass = Class.forName(joinPoint.getSignature().getDeclaringTypeName());

            sysLog.setParame(getOperationContent(joinPoint.getArgs()));


            StringBuffer requestLog = new StringBuffer();
            requestLog.append("==> 请求信息：\n")
                    //.append("==> USER = {" + SysUserUtil.getUserDTO().getName() + "},\n")
                    .append("==> URL = {" + request.getRequestURI() + "},\n")
                    .append("==> HTTP_METHOD = {" + request.getMethod() + "},\n")
                    .append("==> IP = {" + sysLog.getIp() + "},\n")
                    .append("==> CLASS_METHOD = " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "},\n");

            if (joinPoint.getArgs().length == 0) {
                requestLog.append("==> ARGS = {} \n");
            } else {
                requestLog.append("==> ARGS = " + sysLog.getParame() + "\n");
            }
            log.debug("\n==> [{}] interface start  \n{} ",  joinPoint.getSignature().getName(), requestLog.toString());

        } catch (Exception e) {
            interfaceTime.remove();
            log.error("日志报错了:", e);
        }
    }

    @After("controllerMethod()")
    public void logEnd(JoinPoint joinPoint){
        long time = System.currentTimeMillis() - interfaceTime.get();
        log.debug("\n<== [{}] interface end,time consume [{} ms]", joinPoint.getSignature().getName(), String.valueOf(time));
        interfaceTime.remove();
    }



    private String getOperationContent(Object[] arguments) {
        String operationContent = null;
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i] instanceof HttpSession) {
                continue;
            }
            if (arguments[i] instanceof ServletRequest) {
                continue;
            }
            if(arguments[i] instanceof HttpServletResponse){
                continue;
            }
            if (arguments[i] instanceof MultipartFile) {
                operationContent = "上传文件";
                break;
            }
            if (arguments[i] instanceof MultipartFile[]) {
                operationContent = "上传多个文件";
                break;
            }
            operationContent = JSONUtil.toJsonStr(arguments[i]);
        }
        return operationContent;
    }
}
