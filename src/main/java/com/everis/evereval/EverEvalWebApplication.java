package com.everis.evereval;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableScheduling
@EnableWebSecurity
@Slf4j
public class EverEvalWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EverEvalWebApplication.class, args);
    }

}

