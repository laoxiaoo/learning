package com.xiao.spi;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiao ji hao
 * @create 2021年07月25日 20:00:00
 */
@Slf4j
public class IQYUpload implements UploadService{
    @Override
    public void upload() {
        log.debug("==> 爱奇艺上传中....");
    }
}
