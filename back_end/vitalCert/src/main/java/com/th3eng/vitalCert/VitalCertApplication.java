package com.th3eng.vitalCert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class VitalCertApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitalCertApplication.class, args);
	}
}
