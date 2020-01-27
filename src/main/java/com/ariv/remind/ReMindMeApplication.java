package com.ariv.remind;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ariv.remind.security.model.Authority;
import com.ariv.remind.security.repository.AuthorityRepository;

@SpringBootApplication
public class ReMindMeApplication implements WebMvcConfigurer, CommandLineRunner {

	@Autowired
	AuthorityRepository authorityRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReMindMeApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*"); // TODO: lock down before deploying
		config.addAllowedHeader("*");
		config.addExposedHeader(HttpHeaders.AUTHORIZATION);
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
		// return new CorsFilter(configSource);
	}

	@Override
	public void run(String... args) throws Exception {
		Authority authorityAdmin = new Authority();
		authorityAdmin.setName("ROLE_ADMIN");
		Authority authorityUser = new Authority();
		authorityUser.setName("ROLE_USER");
		if (authorityRepository.findByName("ROLE_ADMIN") == null) {
			authorityRepository.save(authorityAdmin);
		}
		if (authorityRepository.findByName("ROLE_USER") == null) {
			authorityRepository.save(authorityUser);
		}
	}

}
