package com.xiao.gof.upload;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 *
 * 上传的代理类
 *
 * </br>
 * 本类是一个代理类，通过枚举的方式，找到对应的目标类，进行执行
 *
 * </br>
 *
 * 寻找目标类的方式，也可以使用策略模式，也可以使用
 * @author xiao ji hao
 * @create 2021年10月21日 23:45:00
 */
@Service
public class UploadProxy  implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public String upload(byte[] buf, String filename) {
        String suffix = filename.substring(filename.lastIndexOf("."));
        FileEnum instance = FileEnum.getInstance(suffix);
        FileUpload fileUpload = (FileUpload)applicationContext.getBean(instance.getBeanName());
        return fileUpload.upload(buf, filename);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
