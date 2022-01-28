package com.cboot.rpidirector.services.groups;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.Group;
import com.cboot.rpidirector.entities.OrganizationUser;
import com.cboot.rpidirector.repositories.GroupRepository;
import com.cboot.rpidirector.services.organizations.GetOrganizationUseCase;
import com.cboot.rpidirector.utils.LogUtils;
import com.cboot.rpidirector.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateDefaultGroupForOrganizationUseCase {

	@Autowired
	private Messages messages;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private GetOrganizationUseCase getOrganizationService;

	public Group createDefaultGroupForOrganization(String userId, String organizationId) {
		log.info("Create default group for organization {}", LogUtils.toJsonString("userId", userId, "organizationId", organizationId));
		OrganizationUser organization = getOrganizationService.getOrganizationForUser(userId, organizationId);
		Optional<Group> existingGroup = groupRepository.findByDefaultGroupAndOrganization(true,
				organization.getOrganization());

		if (existingGroup.isPresent()) {
			throw new IllegalStateException(messages.get("exception.defaultgroup.alreadyexists"));
		}

		Group group = new Group();
		group.setCreatedOn(LocalDateTime.now());
		group.setDefaultGroup(true);
		group.setId(UUID.randomUUID().toString());
		group.setName("Default group");
		group.setOrganization(organization.getOrganization());
		groupRepository.save(group);
		return group;

	}
}
