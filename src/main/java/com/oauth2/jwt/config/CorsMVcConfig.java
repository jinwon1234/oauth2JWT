package com.oauth2.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

@Configuration
public class CorsMVcConfig implements WebMvcConfigurer {

    @Override //컨트롤러가 반환하는 데이터도 프론트가 받을 수 있게 설정
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .exposedHeaders("Set-Cookie")
                .allowedOriginPatterns("*");
    }
}
