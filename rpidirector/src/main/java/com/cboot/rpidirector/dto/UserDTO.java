package com.cboot.rpidirector.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class UserDTO {

	@Getter @Setter
	private String id;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private LocalDateTime registerDate;
	
	@Getter @Setter
	private String token;
	
	@Getter @Setter
	private List<OrganizationDTO> organizations = new ArrayList<>();;
}
