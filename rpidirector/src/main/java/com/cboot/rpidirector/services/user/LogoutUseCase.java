package com.cboot.rpidirector.services.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.User;
import com.cboot.rpidirector.repositories.UserRepository;
import com.cboot.rpidirector.services.exceptions.LoginException;
import com.cboot.rpidirector.utils.LogUtils;
import com.cboot.rpidirector.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LogoutUseCase {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Messages messages;

	public void logout(String userId) {
		log.info("logout {}", LogUtils.toJsonString("userId", userId));
		Optional<User> optUser = userRepository.findById(userId);
		
		if (optUser.isPresent()) {
			User user = optUser.get();
			user.setToken(null);
			user.setTokenValidUntil(null);
			userRepository.save(user);
		} else {
			throw new LoginException(messages.get("exception.login"));
		}
	}
	
}
