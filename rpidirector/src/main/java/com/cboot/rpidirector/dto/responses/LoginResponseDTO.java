package com.cboot.rpidirector.dto.responses;

import com.cboot.rpidirector.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

public class LoginResponseDTO extends BaseResponseDTO  {

	@Getter @Setter
	private UserDTO user;
	
}
