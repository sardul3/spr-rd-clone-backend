package com.sagar.sprfullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SprFullStackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprFullStackApplication.class, args);
	}

}
