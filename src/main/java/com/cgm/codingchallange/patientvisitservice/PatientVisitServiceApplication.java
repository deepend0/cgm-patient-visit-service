package com.cgm.codingchallange.patientvisitservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PatientVisitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientVisitServiceApplication.class, args);
	}

}
