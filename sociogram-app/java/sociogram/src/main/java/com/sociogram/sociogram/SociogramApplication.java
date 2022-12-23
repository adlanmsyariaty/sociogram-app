package com.sociogram.sociogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableMongoRepositories
@EnableJpaRepositories
@EnableWebSecurity
public class SociogramApplication {

	public static void main(String[] args) {
		SpringApplication.run(SociogramApplication.class, args);
	}
}
