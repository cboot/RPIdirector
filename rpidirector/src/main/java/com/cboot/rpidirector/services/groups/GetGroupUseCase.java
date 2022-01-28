package com.cboot.rpidirector.services.groups;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.Group;
import com.cboot.rpidirector.entities.Organization;
import com.cboot.rpidirector.repositories.GroupRepository;
import com.cboot.rpidirector.services.organizations.GetOrganizationUseCase;
import com.cboot.rpidirector.utils.LogUtils;
import com.cboot.rpidirector.utils.Messages;
import com.cboot.rpidirector.utils.RelationsUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetGroupUseCase {

	@Autowired
	private Messages messages;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private GetOrganizationUseCase getOrganization;

	public Group getGroup(String userId, String organizationId, String groupId) {
		log.info("Get group {}",
				LogUtils.toJsonString("userId", userId, "organizationId", organizationId, "groupId", groupId));
		Optional<Group> optional = groupRepository.findById(groupId);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException(messages.get("exception.group.notfound"));
		}

		Organization organization = getOrganization.getOrganizationForUser(userId, organizationId).getOrganization();

		if (RelationsUtils.areRelated(optional.get(), organization)) {
			return optional.get();
		} else {
			throw new EntityNotFoundException(messages.get("exception.group.notfound"));
		}
	}
}
