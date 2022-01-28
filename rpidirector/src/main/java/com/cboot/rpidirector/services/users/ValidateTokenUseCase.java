package com.cboot.rpidirector.services.users;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.User;
import com.cboot.rpidirector.repositories.UserRepository;
import com.cboot.rpidirector.services.exceptions.TokenException;
import com.cboot.rpidirector.utils.Messages;

@Service
public class ValidateTokenUseCase {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Messages messages;

	@Value("${app.token.expirity.extension.minutes}")
	private int TOKEN_EXPIRITY_EXTENSION_MINUTES;

	public boolean validate(String userId, String token) {
		Optional<User> user = userRepository.findById(userId);
		user.filter(u -> u.getTokenValidUntil().isAfter(LocalDateTime.now())).map(ValidateTokenUseCase::updateToken)
				.orElseThrow(() -> new TokenException(messages.get("exception.invalid.token")));
		return true;
	}

	private static User updateToken(User user) {
		user.setTokenValidUntil(LocalDateTime.now().plusMinutes(2));
		return user;
	}
}
