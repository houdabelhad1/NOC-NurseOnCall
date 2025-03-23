package com.hbdev.nurseservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NurseServiceApplication {
    public static void main(String[] args) {
        // Charger les variables depuis .env
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DATABASE_USER", dotenv.get("DATABASE_USER"));
        System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));
        System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
        System.setProperty("SECURITY_USER", dotenv.get("SECURITY_USER"));
        System.setProperty("SECURITY_PASSWORD", dotenv.get("SECURITY_PASSWORD"));

        SpringApplication.run(NurseServiceApplication.class, args);
    }
}

