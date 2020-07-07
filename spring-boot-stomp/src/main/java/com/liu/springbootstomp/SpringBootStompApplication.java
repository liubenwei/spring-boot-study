package com.liu.springbootstomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootStompApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStompApplication.class, args);
	}

}
