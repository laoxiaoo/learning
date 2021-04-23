package com.xiao.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ExceptionResolver.java
 * @Description
 * @createTime 2021年01月24日 12:39:00
 */
@Slf4j
@Component
@ControllerAdvice
@ConditionalOnWebApplication
public class ExceptionResolver {
    /**
     * 业务异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handleBusinessException(Exception e) {
        log.error(e.getMessage(), e);

        return e.getMessage();
    }

}
