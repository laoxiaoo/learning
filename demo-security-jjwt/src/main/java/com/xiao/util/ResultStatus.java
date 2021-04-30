package com.xiao.util;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ResultStatus.java
 * @Description
 * @createTime 2021年01月24日 00:19:00
 */
@ToString
@Getter
public enum ResultStatus {

    /**
     * 成功
     */
    SUCCESS(HttpStatus.OK, 200, "OK"),
    /**
     * 失败请求
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "Bad Request"),

    /**
     * 服务器异常请求
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Internal Server Error"),;  
  
    /** 返回的HTTP状态码,  符合http请求 */  
    private HttpStatus httpStatus;  
    /** 业务异常码 */  
    private Integer code;  
    /** 业务异常信息描述 */  
    private String message;  
  
    ResultStatus(HttpStatus httpStatus, Integer code, String message) {  
        this.httpStatus = httpStatus;  
        this.code = code;  
        this.message = message;  
    }
}