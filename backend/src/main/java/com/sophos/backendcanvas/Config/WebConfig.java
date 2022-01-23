package com.sophos.backendcanvas.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    
    @Value("${origen_aplicacion}")
    private String origenapp;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins(origenapp)
            .allowedHeaders("authorization", "Content-type")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowCredentials(true);
    }
 
}