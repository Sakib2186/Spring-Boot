package com.eazybytes.jobportal.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.useRequestHeader("X-API-VERSION").addSupportedVersions("1.0","2.0").setDefaultVersion("1.0");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api",_->true);
    }


    // If spring security is added as a dependency then this won't work anymore,  need to configure from spring security
    // webconfig for CORS in added in security
    //    @Override
    //    public void addCorsMappings(CorsRegistry registry) {
    //        registry.addMapping("/api/**")
    //                .allowCredentials(true)
    //                .maxAge(3600)
    //                .allowedOrigins("https://localhost:3000")
    //                .allowedMethods("*")
    //                .allowedHeaders("*")
    //                .exposedHeaders("*");
    //    }
}
