package com.nd_monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NdMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(NdMonitoringApplication.class, args);
	}

}
