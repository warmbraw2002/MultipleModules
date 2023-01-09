package com.tiktok.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private UploadConfig uploadConfig;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //映射上传资源的路径 如:/images/**  => file:D:/changlu/闲暇目录/upload/
        //注意：在指定的文件目录前一定要加file:表示使用文件访问协议
        registry.addResourceHandler( uploadConfig.visitPath+"/**").addResourceLocations("file:" + uploadConfig.FILE_UPLOAD_DIR);
    }
}