package com.cboot.rpidirector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cboot.rpidirector.controllers.utils.RestUtils;
import com.cboot.rpidirector.services.users.LogoutUseCase;

@CrossOrigin
@RestController
@RequestMapping("/private/users")
public class PrivateUserController {
	
	@Autowired
	private LogoutUseCase logoutService;

	@PostMapping("/logout")
	public ResponseEntity<Object> logout(@RequestHeader("Authorization") String authorizationHeader) {
		logoutService.logout(RestUtils.getUserIdFromBearerAuthorizationHeader(authorizationHeader));
		return ResponseEntity.ok().build();
	}
}
