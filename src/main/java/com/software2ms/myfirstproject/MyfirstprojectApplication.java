package com.software2ms.myfirstproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(securedEnabled = false)
@SpringBootApplication
public class MyfirstprojectApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyfirstprojectApplication.class, args);
	}
}
