package com.cboot.rpidirector.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.User;
import com.cboot.rpidirector.repositories.UserRepository;
import com.cboot.rpidirector.utils.LogUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetUserUseCase {

	@Autowired
	private UserRepository userRepository;

	public User getById(String id) {
		log.info("getById {}", LogUtils.toJsonString("id", id));
		return userRepository.getById(id);
	}
}
