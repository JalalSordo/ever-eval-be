package com.everis.evereval;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EverEvalWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(EverEvalWebApplication.class, args);
	}
	
}

