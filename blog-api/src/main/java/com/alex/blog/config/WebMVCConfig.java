package com.alex.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Alexandermucc
 * @date 2022/3/12 - 17:37 - 周六
 **/


@Configuration
public class WebMVCConfig implements WebMvcConfigurer {


    /**
     * 跨域配置
     * 使得8080端口能够访问8888端口
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }
}
