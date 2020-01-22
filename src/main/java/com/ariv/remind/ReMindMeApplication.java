package com.ariv.remind;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ariv.remind.model.Problem;
import com.ariv.remind.model.ProblemType;
import com.ariv.remind.repository.ProblemRepository;

@SpringBootApplication
public class ReMindMeApplication implements WebMvcConfigurer {

	@Autowired
	ProblemRepository problemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ReMindMeApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
	}
}
