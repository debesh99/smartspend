package com.debesh.smartspend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SmartspendApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmartspendApplication.class, args);
	}

}
