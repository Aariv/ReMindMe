package com.ariv.remind.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ariv.remind.security.model.User;
import com.ariv.remind.security.repository.UserRepository;

import java.util.Optional;

@Component
public class SecurityUtils {
	
	@Autowired
	private UserRepository userRepository;

	private static final Logger LOG = LoggerFactory.getLogger(SecurityUtils.class);

	private SecurityUtils() {
	}

	/**
	 * Get the login of the current user.
	 *
	 * @return the login of the current user.
	 */
	public static Optional<String> getCurrentUsername() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			LOG.debug("no authentication in security context found");
			return Optional.empty();
		}

		String username = null;
		if (authentication.getPrincipal() instanceof UserDetails) {
			UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
			username = springSecurityUser.getUsername();
		} else if (authentication.getPrincipal() instanceof String) {
			username = (String) authentication.getPrincipal();
		}

		LOG.debug("found username '{}' in security context", username);

		return Optional.ofNullable(username);
	}

	public Optional<Integer> getCurrentUserId() {
		Optional<User> user = userRepository.findOneWithAuthoritiesByEmailIgnoreCase(getCurrentUsername().get());
		return Optional.ofNullable(user.get().getId());
	}
	
	public Optional<User> getCurrentUser() {
		Optional<User> user = userRepository.findOneWithAuthoritiesByEmailIgnoreCase(getCurrentUsername().get());
		return Optional.ofNullable(user.get());
	}
}
