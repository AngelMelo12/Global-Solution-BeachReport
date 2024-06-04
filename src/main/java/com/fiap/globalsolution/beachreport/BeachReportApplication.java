package com.fiap.globalsolution.beachreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BeachReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeachReportApplication.class, args);
	}

}
