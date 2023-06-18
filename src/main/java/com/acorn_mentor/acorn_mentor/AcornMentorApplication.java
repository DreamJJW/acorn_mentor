package com.acorn_mentor.acorn_mentor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@ConfigurationPropertiesScan
@SpringBootApplication
public class AcornMentorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcornMentorApplication.class, args);
	}

}
