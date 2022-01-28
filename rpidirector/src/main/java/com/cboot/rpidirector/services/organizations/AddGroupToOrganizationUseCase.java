package com.cboot.rpidirector.services.organizations;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.Group;
import com.cboot.rpidirector.entities.Organization;
import com.cboot.rpidirector.utils.LogUtils;
import com.cboot.rpidirector.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddGroupToOrganizationUseCase {

	@Autowired
	private Messages messages;

	@Autowired
	private GetOrganizationUseCase getOrganizationService;

	public Group addGroupToOrganization(String userId, String organizationId, String groupName) {
		log.info("Add group to organization {}",
				LogUtils.toJsonString("userId", userId, "organizationId", organizationId, "groupName", groupName));

		Organization organization = getOrganizationService.getOrganizationForUser(userId, organizationId)
				.getOrganization();

		for (Group aGroup : organization.getGroups()) {
			if (aGroup.getName().equals(groupName)) {
				throw new IllegalArgumentException(messages.get("exception.group.nameexists"));
			}
		}

		Group group = new Group();
		group.setId(UUID.randomUUID().toString());
		group.setCreatedOn(LocalDateTime.now());
		group.setDefaultGroup(false);
		group.setName(groupName);
		group.setOrganization(organization);

		organization.getGroups().add(group);

		return group;

	}
}
