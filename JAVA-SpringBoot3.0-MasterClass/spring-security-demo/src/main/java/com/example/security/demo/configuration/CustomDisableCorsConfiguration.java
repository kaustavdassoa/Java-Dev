package com.example.security.demo.configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CuustomDisableCorsConfiguration {

    public WebMvcConfigurer disableCors()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                WebMvcConfigurer.super.addCorsMappings(registry);

                registry.addMapping("/**")
                        .allowedMethods()

            }
        };
    }
}
