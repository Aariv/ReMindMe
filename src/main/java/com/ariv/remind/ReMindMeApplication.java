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
public class ReMindMeApplication implements WebMvcConfigurer, CommandLineRunner {

	@Autowired
	ProblemRepository problemRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReMindMeApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
	}

	private void constructProblems() {
		List<Problem> problemList = new ArrayList<Problem>();
		Problem problem1 = new Problem("Find and Replace Pattern", "512", ProblemType.EASY, "");
		Problem problem2 = new Problem("Find and Replace Pattern2", "513", ProblemType.MEDIUM, "");
		Problem problem3 = new Problem("Find and Replace Pattern3", "514", ProblemType.HARD, "");
		Problem problem4 = new Problem("Find and Replace Pattern4", "515", ProblemType.EASY, "");
		problemList.add(problem1);
		problemList.add(problem2);
		problemList.add(problem3);
		problemList.add(problem4);
		problemRepository.saveAll(problemList);
	}

	@Override
	public void run(String... args) throws Exception {
		constructProblems();
	}
}
