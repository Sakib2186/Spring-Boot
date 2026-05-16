package com.eazybytes.jobportal.security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.security.autoconfigure.web.servlet.SecurityFilterProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity // optional
@RequiredArgsConstructor
public class JobPortalSecurityConfig {

    @Qualifier("publicPaths")
    private final List<String> publicPaths;

    @Qualifier("securePaths")
    private final List<String> securePaths;

    // This method is copied from SecurityFilterChain.
    @Bean
    //@Order(SecurityFilterProperties.BASIC_AUTH_ORDER) can define order if you use multiple security service with order
    SecurityFilterChain customSecurityFilterChain(HttpSecurity http) {
        return http
                .cors(corsConfig->corsConfig.configurationSource(corsConfigurationSource())) // custom CORS Configuration
                .csrf(csrf-> csrf.disable()) // disables csrf protection for
                .authorizeHttpRequests(requests-> {
                            publicPaths.forEach(path->requests.requestMatchers(path).permitAll());
                            securePaths.forEach(path->requests.requestMatchers(path).authenticated());
                            requests.anyRequest().denyAll();
                        })
                // So any endpoint with public would be not restricted and also allowing Swagger endpoints for loading css and others
                //mapping which requests to authenticate and which to not use authentication
                //.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated()) // or can use .permitAll() to allow all request // Also .denyAll()
                //.formLogin(withDefaults())
                //if you dont want to use HTTP login form everytime then
                .formLogin(flc->flc.disable()) // flc = form login control. Now wont take you to login page, but would give 401 Unauthorized
                .httpBasic(withDefaults()) // hbc -> hbc.disable()
                .build();
                // for pure REST configuration best to disable form login and keep httpBasic. Standard practise
    }

    // So we can create this same bean but with permitAll configuration for a certain environment and for production can use .authenticated()

    //CHOICES:
    // .authenticated()
    // .permitAll()
    // .denyAll() // throws 403 Forbidden

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        // returns user details
        // telling spring security whenever i want to fetch or create user details use this bean

        // creating a user
        var user1 = User.builder().username("sami").password(passwordEncoder().encode("sami@123")).roles("USER").build();
        var user2 = User.builder().username("admin").password(passwordEncoder().encode("admin@123")).roles("ADMIN").build();
        // This way create any number of users

        return new InMemoryUserDetailsManager(user1,user2); // any number of user details
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // so whenever someone is registering or logging in use BCryptPassword encoder
        // SCryptPasswordEncoder(); // another one
    }
}
