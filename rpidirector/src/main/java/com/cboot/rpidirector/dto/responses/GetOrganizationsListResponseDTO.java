package com.cboot.rpidirector.dto.responses;

import java.util.ArrayList;
import java.util.List;

import com.cboot.rpidirector.dto.OrganizationDTO;

import lombok.Getter;
import lombok.Setter;

public class GetOrganizationsListResponseDTO extends BaseResponseDTO  {

	@Getter @Setter
	private List<OrganizationDTO> organizationsList = new ArrayList<>();
	
}
