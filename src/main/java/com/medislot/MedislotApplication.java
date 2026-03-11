package com.medislot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedislotApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedislotApplication.class, args);
	}
}