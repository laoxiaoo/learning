package com.xiao.gof.upload;

import org.springframework.stereotype.Component;

/**
 * 对于大文件，模拟上传阿里oss
 * @author xiao ji hao
 * @create 2021年10月21日 23:35:00
 */
@Component
public class AliOssFileUpload implements FileUpload {
    @Override
    public String upload(byte[] buf, String path) {
        return null;
    }
}
