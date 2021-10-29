package com.cboot.rpidirector.dto.responses;

import com.cboot.rpidirector.dto.OrganizationDTO;

import lombok.Getter;
import lombok.Setter;

public class CreateOrganizationResponseDTO extends BaseResponseDTO {

	@Getter @Setter
	private OrganizationDTO organization;
	
}
