package com.ariv.remind.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariv.remind.resource.ResponseData;
import com.ariv.remind.security.SecurityUtils;
import com.ariv.remind.security.model.Authority;
import com.ariv.remind.security.model.User;
import com.ariv.remind.security.repository.AuthorityRepository;
import com.ariv.remind.security.repository.UserRepository;
import com.ariv.remind.security.rest.dto.RegisterDto;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

	private final UserRepository userRepository;

	private final AuthorityRepository authorityRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, AuthorityRepository authorityRepository) {
		this.userRepository = userRepository;
		this.authorityRepository = authorityRepository;
	}

	public ResponseData registerUser(RegisterDto registerDto) {
		User user = new User();
		if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
			new ResponseData(false, "", "Passwords do not match.");
		}
		user.setEmail(registerDto.getEmail());
		user.setUsername(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		Authority authority = authorityRepository.findByName("ROLE_USER");
		Set<Authority> authorities = new HashSet<>();
		authorities.add(authority);
		user.setAuthorities(authorities);
		user.setActivated(true);
		User userFromDb = userRepository.save(user);
		if (userFromDb != null) {
			return new ResponseData(true, userFromDb, "Success");
		}
		return new ResponseData(false, userFromDb, "Failed");

	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthorities() {
		return SecurityUtils.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
	}

}
