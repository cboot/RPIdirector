package com.cboot.rpidirector.dto.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

public class CreateUserRequestDTO {

	@Getter @Setter 
	@NotEmpty(message = "{validation.email.notempty}")
	@Email(message = "{validation.email.valid}")
	private String email;
	
	@Getter @Setter
	@NotEmpty(message = "{validation.password.notempty}")
	@Size(min = 8, max = 40, message = "{validation.password.size}")
	private String password;
	
	@Getter @Setter
	@NotEmpty(message = "{validation.user.notempty}")
	@Size(min = 2, max = 64, message = "{validation.username.size}")
	private String name;
}
