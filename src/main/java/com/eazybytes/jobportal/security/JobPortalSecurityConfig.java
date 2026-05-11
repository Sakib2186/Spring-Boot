package com.eazybytes.jobportal.security;


import org.springframework.boot.security.autoconfigure.web.servlet.SecurityFilterProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity // optional
public class JobPortalSecurityConfig {

    // This method is copied from SecurityFilterChain.
    @Bean
    //@Order(SecurityFilterProperties.BASIC_AUTH_ORDER) can define order if you you use multiple security service with order
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
        return http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
                //.formLogin(withDefaults())
                //if you dont want to use HTTP login form everytime then
                .formLogin(flc->flc.disable()) // flc = form login control. Now wont take you to login page, but would give 401 Unauthorized
                .httpBasic(withDefaults()) // hbc -> hbc.disable()
                .build();

                // for pure REST configuration best to disable form login and keep httpBasic. Standard practise
    }
}
