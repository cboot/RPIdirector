package com.cboot.rpidirector.services.users;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.User;
import com.cboot.rpidirector.repositories.UserRepository;
import com.cboot.rpidirector.services.exceptions.LoginException;
import com.cboot.rpidirector.utils.LogUtils;
import com.cboot.rpidirector.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginUseCase {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Messages messages;
	
	@Value( "${app.token.expirity.extension.minutes}")
	private int TOKEN_EXPIRITY_EXTENSION_MINUTES;
	
	public User login(String email, String password) {
		log.info("login {}", LogUtils.toJsonString("email", email));
		Optional<User> optUser = userRepository.findByEmailAndPassword(email, passwordEncoder.encode(password));
		
		if (! optUser.isPresent() ) {
			throw new LoginException(messages.get("exception.login"));
		}
		
		User user = optUser.get();
		user.setToken(UUID.randomUUID().toString());
		user.setTokenValidUntil(LocalDateTime.now().plusMinutes(TOKEN_EXPIRITY_EXTENSION_MINUTES));
		userRepository.save(user);
		return user;
		
	}
}
