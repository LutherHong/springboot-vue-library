package com.how2java.springboot.config;

import com.how2java.springboot.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
    // 后台拦截
    @Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getLoginIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html")
                .excludePathPatterns(Arrays.asList("/index.html","/api/login","/api/logout"));
    }

    // 跨域请求，可以在controller层里添加@CrossOrigin解决，也可以在这里加
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //所有请求都允许跨域
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    // 设置的图片资源文件夹，即 C:\Users\zhaozhihong\Pictures\Camera Roll 对应起来
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "C:\\Users\\zhaozhihong\\Pictures\\Camera Roll/");
    }

}
