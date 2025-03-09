package com.hbdev.nurseservice.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactive CSRF (pour éviter les erreurs en POST)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Autorise toutes les requêtes sans authentification
                );
        return http.build();
    }
}


