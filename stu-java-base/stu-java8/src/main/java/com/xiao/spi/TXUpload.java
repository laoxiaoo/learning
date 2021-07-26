package com.xiao.spi;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiao ji hao
 * @create 2021年07月25日 20:01:00
 */
@Slf4j
public class TXUpload implements UploadService {
    @Override
    public void upload() {
        log.debug("==> 腾讯上传中...");
    }
}
