package com.success;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@Validated
public class SuccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuccessApplication.class, args);
	}

}
