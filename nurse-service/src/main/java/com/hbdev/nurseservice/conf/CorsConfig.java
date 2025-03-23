package com.hbdev.nurseservice.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Autoriser les routes sous /api
                        .allowedOrigins("http://localhost:5173") // URL du frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes autorisées
                        .allowedHeaders("*") // Autoriser tous les en-têtes
                        .allowCredentials(true); // Autoriser les cookies si nécessaire
            }
        };
    }
}
