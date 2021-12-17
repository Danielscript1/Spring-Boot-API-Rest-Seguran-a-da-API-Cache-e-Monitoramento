package com.testeweb.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class SpringBootApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApiRestApplication.class, args);
	}

}
