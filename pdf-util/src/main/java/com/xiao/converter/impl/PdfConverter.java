package com.xiao.converter.impl;

import com.xiao.converter.Converter;
import com.xiao.converter.Parameter;
import com.xiao.converter.Response;
import org.springframework.stereotype.Component;

/**
 * @author 肖杰
 * @ClassName PdfConverter.java
 * @Description
 * @createTime 2021年04月07日 10:07:00
 */
@Component
public class PdfConverter implements Converter {
    @Override
    public <T extends Response> T convert(Parameter parameter) {

        return null;
    }
}
