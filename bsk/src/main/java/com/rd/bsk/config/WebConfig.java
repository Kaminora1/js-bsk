package com.rd.bsk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 拦截所有的请求
        registry.addMapping("/**")
                //// 可跨域的域名，可以为 *
                //.allowedOrigins("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                // 允许跨域的方法，可以单独配置
                .allowedMethods("*")
                // 允许跨域的请求头，可以单独配置
                .allowedHeaders("*");
    }
}