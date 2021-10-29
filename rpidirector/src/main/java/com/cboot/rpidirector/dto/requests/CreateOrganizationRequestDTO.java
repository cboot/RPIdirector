package com.cboot.rpidirector.dto.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

public class CreateOrganizationRequestDTO {

	@Getter @Setter
	@NotEmpty(message = "{validation.name.notempty}")
	@Size(min = 2, max = 64, message = "{validation.name.size}")
	private String name;
}
