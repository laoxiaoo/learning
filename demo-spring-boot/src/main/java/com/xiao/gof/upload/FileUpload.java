package com.xiao.gof.upload;

/**
 * @author xiao ji hao
 * @create 2021年10月21日 23:34:00
 */
public interface FileUpload {

    /**
     * 上传方法
     * @param buf
     * @param path
     * @return
     */
    String upload(byte[] buf, String path);

}
