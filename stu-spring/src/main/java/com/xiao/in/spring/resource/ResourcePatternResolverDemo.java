package com.xiao.in.spring.resource;

import cn.hutool.core.io.IoUtil;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ResourcePatternResolverDemo.java
 * @Description
 * @createTime 2021年02月18日 15:21:00
 */
public class ResourcePatternResolverDemo {

    public static void main(String[] args) throws IOException {
        String currentPath = "/"+ System.getProperty("user.dir")+"/stu-spring/src/main/java/com/xiao/in/spring/resource/";
        String localPath = currentPath+"*.java";

        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        Resource[] resources = patternResolver.getResources(localPath);
        Arrays.stream(resources).map(ResourcePatternResolverDemo::getContent).forEach(System.out::println);
    }

    public static String getContent(Resource resource) {
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        try (Reader reader = encodedResource.getReader()){
            return IoUtil.read(reader);
        } catch (Exception e) {
        }
        return null;
    }

}
