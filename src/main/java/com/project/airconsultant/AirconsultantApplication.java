package com.project.airconsultant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.project.airconsultant.repository")
@EntityScan(basePackages = "com.project.airconsultant.model")
@EnableCaching
public class AirconsultantApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirconsultantApplication.class, args);
	}

}
