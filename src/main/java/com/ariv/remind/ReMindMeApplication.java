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

	@Override
	public void run(String... args) throws Exception {
		constructProblems();
	}

	/**
	 * 
	 */
	private void constructProblems() {
		List<Problem> problemList = new ArrayList<Problem>();
		Problem problem1 = new Problem("Defanging an IP Address", "1108", ProblemType.EASY, "Good");
		Problem problem2 = new Problem("Convert Binary Number in a Linked List to Integer", "1290", ProblemType.EASY, "Good");
		Problem problem3 = new Problem("Odd Even Linked List", "328", ProblemType.MEDIUM, "Needs more revision, Failed in 3 testcases");
		Problem problem4 = new Problem("Maximum Product Subarray", "152", ProblemType.HARD,
				"Very Hard Problem. Needs in-depth understanding of Dynamic Programming");
		problemList.add(problem1);
		problemList.add(problem2);
		problemList.add(problem3);
		problemList.add(problem4);
		problemRepository.saveAll(problemList);
	}
}
