package com.cboot.rpidirector.services.organization;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.Organization;
import com.cboot.rpidirector.entities.OrganizationRole;
import com.cboot.rpidirector.entities.OrganizationUser;
import com.cboot.rpidirector.entities.User;
import com.cboot.rpidirector.repositories.OrganizationRepository;
import com.cboot.rpidirector.repositories.OrganizationUserRepository;
import com.cboot.rpidirector.services.user.GetUserUseCase;
import com.cboot.rpidirector.utils.LogUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateOrganizationUseCase {

	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired 
	private OrganizationUserRepository organizationUserReposistory;
	
	@Autowired
	private GetUserUseCase getUserService;
	
	public Organization create(String name, String owner) {
		log.info("Create organization {}", LogUtils.toJsonString("name", name, "owner", owner));
		User user = getUserService.getById(owner);
		Organization organization = new Organization();
		organization.setId(UUID.randomUUID().toString());
		organization.setName(name);
		organizationRepository.saveAndFlush(organization);
		OrganizationUser organizationOwner = new OrganizationUser();
		organizationOwner.setOrganization(organization);
		organizationOwner.setUser((User)Hibernate.unproxy(user));
		organizationOwner.setRole(OrganizationRole.OWNER);
		organizationOwner.setSince(LocalDateTime.now());
		organizationUserReposistory.save(organizationOwner);
		organization.getUsers().add(organizationOwner);
		organizationRepository.save(organization);
		return organization;
		
	}
}
