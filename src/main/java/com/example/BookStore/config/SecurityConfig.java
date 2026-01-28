package com.example.BookStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for testing
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/users/register").permitAll() // Allow public access
//                        .anyRequest().authenticated() // Secure other endpoints
//                )
                // for testing allowing all the api endpoints
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login.disable()) // Disable form login
                .httpBasic(basic -> basic.disable()); // Disable basic auth

        return http.build();
    }
}
