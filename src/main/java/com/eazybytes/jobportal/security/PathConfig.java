package com.eazybytes.jobportal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PathConfig {

    @Bean("publicPaths")
    public List<String> publicPaths(){
        return List.of("/api/companies/public",
            "/api/contact-us/public",
            "/api/swagger-ui.html",
            "/swagger-ui/**",
            "/api/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-html",
            "/webjars/**"
        );
    }

    @Bean("securePaths")
    public List<String> securePaths(){
        return List.of(
                "/api/**"
        );
    }
}
