package com.usuario.security;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("**/")
                .allowedOrigins("http://localhost:8083")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowCredentials(true);
    }
}