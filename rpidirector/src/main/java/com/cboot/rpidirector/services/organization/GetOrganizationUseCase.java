package com.cboot.rpidirector.services.organization;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.Organization;
import com.cboot.rpidirector.entities.OrganizationUser;
import com.cboot.rpidirector.entities.User;
import com.cboot.rpidirector.repositories.OrganizationRepository;
import com.cboot.rpidirector.repositories.OrganizationUserRepository;
import com.cboot.rpidirector.services.user.GetUserUseCase;
import com.cboot.rpidirector.utils.LogUtils;
import com.cboot.rpidirector.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetOrganizationUseCase {

	@Autowired
	private Messages messages;

	@Autowired
	private OrganizationUserRepository organizationUserRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private GetUserUseCase getUserService;

	public OrganizationUser getOrganizationForUser(String userId, String organizationId) {
		log.info("Retrieving organizationuser {}",
				LogUtils.toJsonString("userId", userId, "organizationId", organizationId));
		User user = getUserService.getById(userId);

		Optional<Organization> organization = organizationRepository.findById(organizationId);
		if (organization.isEmpty()) {
			throw new EntityNotFoundException(messages.get("exception.organization.notfound"));
		}
		Optional<OrganizationUser> result = organizationUserRepository.findByUserAndOrganization(user,
				organization.get());
		if (result.isEmpty()) {
			throw new EntityNotFoundException(messages.get("exception.organization.notfound"));
		}
		return result.get();
	}
}
