package me.hj.zhibo.config;

import lombok.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class MyWebAppConfigurer implements WebMvcConfigurer {
    @org.springframework.beans.factory.annotation.Value("${web.upload-path}")
    private String uploadPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+uploadPath);
    }
}

