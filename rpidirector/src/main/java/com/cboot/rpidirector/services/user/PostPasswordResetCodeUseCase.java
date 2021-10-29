package com.cboot.rpidirector.services.user;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.dto.RequestedPasswordResetCode;
import com.cboot.rpidirector.entities.User;
import com.cboot.rpidirector.repositories.UserRepository;
import com.cboot.rpidirector.utils.LogUtils;
import com.cboot.rpidirector.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostPasswordResetCodeUseCase {

	@Autowired
	private RequestPasswordResetCodeUseCase requestPasswordService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Messages messages;
	
	public User validate(String email, String codeString) {
		log.info("Requested password reset code {}", LogUtils.toJsonString("email", email, "codeString", codeString));

		Optional<RequestedPasswordResetCode> requestCode = requestPasswordService.getCode(email);

		if (requestCode.isPresent()) {
		
			if (requestCode.get().getValidUntil().isAfter(LocalDateTime.now())
					&& requestCode.get().getCode().equals(codeString)) {
				log.debug("Resetting user password");
				//TODO
				return userRepository.findByEmail(email).get();
			}
		}
		throw new IllegalArgumentException(messages.get("exception.recoverycode.notvalid"));
	}

}
