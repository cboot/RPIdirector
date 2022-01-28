package com.cboot.rpidirector.services.users;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.User;
import com.cboot.rpidirector.repositories.UserRepository;
import com.cboot.rpidirector.services.exceptions.EntityAlreadyExistsException;
import com.cboot.rpidirector.utils.LogUtils;
import com.cboot.rpidirector.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegisterUserUseCase {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoginUseCase login;
	
	@Autowired
	private Messages messages;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User register(String email, String password, String name) {
		log.info("Register {} ", LogUtils.toJsonString("email", email, "name", name));
		Optional<User> fetched = userRepository.findByEmail(email);
		
		if ( fetched.isPresent() ) {
			log.error("Won't register user because the email already exists" );
			throw new EntityAlreadyExistsException(messages.get("exception.email.exists"));
		}
		
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setName(name);
		user.setRegisterDate(LocalDateTime.now());
		userRepository.save(user);
		return login.login(user.getEmail(), password);
		
	}
}
