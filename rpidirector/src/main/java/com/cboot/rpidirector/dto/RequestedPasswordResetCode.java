package com.cboot.rpidirector.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class RequestedPasswordResetCode {

	@Getter @Setter
	private String code;
	
	@Getter @Setter
	private LocalDateTime validUntil;
}
