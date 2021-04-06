package com.xiao.exception;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ExceptionControllerAdvice.java
 * @Description
 * @createTime 2021年04月06日 21:42:00
 */
@ControllerAdvice
@RestController
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String handleValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        StringBuilder builder = new StringBuilder();
        result.getFieldErrors().forEach(item -> builder.append(item.getDefaultMessage()));
        return builder.toString();
    }

    @ExceptionHandler(value = Throwable.class)
    public String exception(Throwable exception) {
        return exception.getMessage();
    }



}
