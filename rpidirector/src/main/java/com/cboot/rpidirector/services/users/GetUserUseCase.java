package com.cboot.rpidirector.services.users;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.User;
import com.cboot.rpidirector.repositories.UserRepository;
import com.cboot.rpidirector.utils.LogUtils;
import com.cboot.rpidirector.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetUserUseCase {

	@Autowired
	private Messages messages;
	
	@Autowired
	private UserRepository userRepository;

	public User getById(String id) {
		log.info("getById {}", LogUtils.toJsonString("id", id));
		Optional<User> existingUser = userRepository.findById(id);
		
		if (existingUser.isEmpty()) {
			throw new EntityNotFoundException(messages.get("exception.user.notfound"));
		}
		
		return userRepository.findById(id).get();
	}
}
