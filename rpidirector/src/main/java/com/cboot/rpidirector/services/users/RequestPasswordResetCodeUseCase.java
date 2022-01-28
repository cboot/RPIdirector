package com.cboot.rpidirector.services.users;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.dto.RequestedPasswordResetCode;
import com.cboot.rpidirector.entities.User;
import com.cboot.rpidirector.repositories.UserRepository;
import com.cboot.rpidirector.utils.LogUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RequestPasswordResetCodeUseCase {

	private static Map<String, RequestedPasswordResetCode> codes = new HashMap<>();
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${app.request.forgotten..expirity.extension.seconds}")
	private int CODE_VALIDITY_SECONDS;
	
	public void requestCode(String email) {
		log.info("Requested password reset code {}", LogUtils.toJsonString("email", email));
		
		Optional<User> user = userRepository.findByEmail(email);
		
		if (user.isPresent()) {
			codes.put(user.get().getEmail(), new RequestedPasswordResetCode(randomString(6), LocalDateTime.now().plusSeconds(CODE_VALIDITY_SECONDS)));
			log.debug("Generated code is {}", LogUtils.toJsonString("code", codes.get(user.get().getEmail()).getCode()));
		}
	}
	
	public Optional<RequestedPasswordResetCode> getCode(String email) {
		log.info("Retrieve password reset code from cache {}", LogUtils.toJsonString("email", email));
		return Optional.ofNullable(codes.get(email));
		
	}
	
	public void clearCode(String email) {
		codes.remove(email);
	}
	
	private String randomString(int targetStringLength) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return generatedString;
	}
}
