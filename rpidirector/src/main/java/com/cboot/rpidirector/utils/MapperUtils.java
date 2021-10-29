package com.cboot.rpidirector.utils;

import com.cboot.rpidirector.dto.OrganizationDTO;
import com.cboot.rpidirector.dto.UserDTO;
import com.cboot.rpidirector.entities.OrganizationUser;
import com.cboot.rpidirector.entities.User;

public final class MapperUtils {

	private MapperUtils() {}
	
	public static UserDTO toDTO(User user) {
		UserDTO output = new UserDTO();
		output.setEmail(user.getEmail());
		output.setId(user.getId());
		output.setRegisterDate(user.getRegisterDate());
		output.setToken(user.getToken());
		for (OrganizationUser anOrganization: user.getOrganizations()) {
			output.getOrganizations().add(toDTO(anOrganization));
		}
		return output;
	}
	
	public static OrganizationDTO toDTO(OrganizationUser organization) {
		OrganizationDTO output = new OrganizationDTO();
		output.setId(organization.getOrganization().getId());
		output.setName(organization.getOrganization().getName());
		output.setRole(organization.getRole().toString());
		output.setSince(organization.getSince());
		return output;
	}
	
}
