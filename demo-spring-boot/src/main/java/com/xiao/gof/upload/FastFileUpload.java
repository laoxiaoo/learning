package com.xiao.gof.upload;

import org.springframework.stereotype.Component;

/**
 * 模拟上传fast fds
 * @author xiao ji hao
 * @create 2021年10月21日 23:40:00
 */
@Component
public class FastFileUpload implements FileUpload {
    @Override
    public String upload(byte[] buf, String path) {
        return null;
    }
}
