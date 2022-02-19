package com.prospace.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication
public class ProspaceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProspaceProjectApplication.class, args);
	}

}
