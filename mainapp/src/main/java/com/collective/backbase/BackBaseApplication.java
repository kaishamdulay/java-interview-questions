package com.collective.backbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Configuration
@ComponentScan("com.collective.backbase")
public class BackBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackBaseApplication.class, args);
	}

}
