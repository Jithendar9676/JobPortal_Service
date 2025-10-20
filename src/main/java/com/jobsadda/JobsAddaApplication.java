package com.jobsadda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobsAddaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsAddaApplication.class, args);
	}

}
