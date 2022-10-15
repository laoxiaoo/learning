package com.xiao.bootstrap;


import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import com.xiao.annotation.EnableServer;
import com.xiao.server.Server;
import com.xiao.server.impl.FtpServerImpl;
import com.xiao.server.impl.HttpServerImpl;

import java.util.Map;

/**
 * @author lao xiao
 * @version 1.
 * @ClassName ServerSelector.java
 * @Description TODO
 * @createTime 2021年03月22日 09:19:00
 */
public class ServerSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableServer.class.getName());
        Server.Type type = (Server.Type) annotationAttributes.get("type");
        if(Server.Type.FTP.equals(type)) {
            return new String[] {FtpServerImpl.class.getName()};
        } else {
            return new String[] {HttpServerImpl.class.getName()};
        }
    }
}
