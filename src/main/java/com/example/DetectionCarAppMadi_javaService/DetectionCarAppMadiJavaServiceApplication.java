package com.example.DetectionCarAppMadi_javaService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
public class DetectionCarAppMadiJavaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DetectionCarAppMadiJavaServiceApplication.class, args);
	}

	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

}
