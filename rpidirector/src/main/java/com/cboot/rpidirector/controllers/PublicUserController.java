
package com.cboot.rpidirector.controllers;

import java.util.Base64;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cboot.rpidirector.dto.requests.CreateUserRequestDTO;
import com.cboot.rpidirector.dto.responses.CreateUserResponseDTO;
import com.cboot.rpidirector.dto.responses.LoginResponseDTO;
import com.cboot.rpidirector.dto.responses.SendRequestCodeResponseDTO;
import com.cboot.rpidirector.entities.User;
import com.cboot.rpidirector.services.exceptions.LoginException;
import com.cboot.rpidirector.services.user.LoginUseCase;
import com.cboot.rpidirector.services.user.PostPasswordResetCodeUseCase;
import com.cboot.rpidirector.services.user.RegisterUserUseCase;
import com.cboot.rpidirector.services.user.RequestPasswordResetCodeUseCase;
import com.cboot.rpidirector.utils.MapperUtils;
import com.cboot.rpidirector.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/public/users")
@CrossOrigin
@Slf4j
public class PublicUserController {

	@Autowired
	private Messages messages;

	@Autowired
	private RegisterUserUseCase registerService;

	@Autowired
	private LoginUseCase loginService;

	@Autowired
	private RequestPasswordResetCodeUseCase requestPasswordResetService;

	@Autowired
	private PostPasswordResetCodeUseCase postPasswordResetCodeService;

	@PostMapping("/register")
	public ResponseEntity<CreateUserResponseDTO> createUser(
			@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO) {
		User user = registerService.register(createUserRequestDTO.getEmail(), createUserRequestDTO.getPassword(),
				createUserRequestDTO.getName());
		CreateUserResponseDTO output = new CreateUserResponseDTO();
		output.setUser(MapperUtils.toDTO(user));
		return ResponseEntity.ok().body(output);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(
			@NotEmpty(message = "{authorization.header.notempty}") @RequestHeader("Authorization") String authHeader) {
		String[] split = null;
		try {
			split = new String(Base64.getDecoder().decode(authHeader.substring("Basic ".length()))).split(":");
		} catch (Exception e) {
			log.error("Failed login, unexpected error");
			throw new LoginException(messages.get("exception.login"));
		}
		if (split.length != 2) {
			log.error("Failed login, auth string doesnt have the proper format");
			throw new LoginException(messages.get("exception.login"));
		}

		User user = loginService.login(split[0], split[1]);
		LoginResponseDTO output = new LoginResponseDTO();
		output.setUser(MapperUtils.toDTO(user));
		return ResponseEntity.ok().body(output);

	}

	@PostMapping("/requestRecoverPasswordCode/{email}")
	public ResponseEntity<Object> requestRecoverCode(
			@Valid @PathVariable @Email(message = "{validation.email.valid}") String email) {
		requestPasswordResetService.requestCode(email);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/postRecoverPasswordCode/{email}/{code}")
	public ResponseEntity<SendRequestCodeResponseDTO> sendRequestCode(
			@Valid @PathVariable @Email(message = "{validation.email.valid}") String email,
			@Valid @PathVariable @Size(min = 10, max = 10, message = "{validation.requestcode.length") String code) {
		SendRequestCodeResponseDTO output = new SendRequestCodeResponseDTO();
		output.setUser(MapperUtils.toDTO(postPasswordResetCodeService.validate(email, code)));
		return ResponseEntity.ok().body(output);
	}

}
