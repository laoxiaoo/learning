package com.xiao.gof.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author xiao ji hao
 * @create 2021年10月21日 23:41:00
 */
@Getter
@AllArgsConstructor
public enum FileEnum {

    ALI_OSS(new String[]{"avi", "mp4"}, "aliOssFileUpload"),

    FAST_FDS(new String[]{"png"}, "fastFileUpload"),

    ;

    private String[] suffix;

    private String beanName;

    public static FileEnum getInstance(String suffix) {
        for(FileEnum fileEnum : FileEnum.values()) {
            if(Arrays.stream(fileEnum.getSuffix()).anyMatch(var -> var.equals(suffix))) {
                return fileEnum;
            }
        }
        return null;
    }

}
