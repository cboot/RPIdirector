package com.cboot.rpidirector.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

public class OrganizationDTO {

	@Getter @Setter
	private String id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private String role;
	
	@Getter @Setter
	private LocalDateTime since;
	
	@Getter @Setter
	private int groups;
	
	@Getter @Setter
	private int computers;
	
}
