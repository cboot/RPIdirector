package com.cboot.rpidirector.services.organizations;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.Group;
import com.cboot.rpidirector.repositories.GroupRepository;
import com.cboot.rpidirector.utils.LogUtils;
import com.cboot.rpidirector.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetDefaultGroupForOrganizationUseCase {

	@Autowired
	private Messages messages;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private GetOrganizationUseCase getOrganizationService;

	public Group getDefaultGroupForOrganization(String userId, String organizationId) {
		log.info("Get default group for organization {}", LogUtils.toJsonString("userId", userId, "organizationId", organizationId));
				
		Optional<Group> existingGroup = groupRepository.findByDefaultGroupAndOrganization(true,
				getOrganizationService.getOrganizationForUser(organizationId, organizationId).getOrganization());
		
		if (existingGroup.isEmpty()) {
			throw new EntityNotFoundException(messages.get("exception.group.notfound"));
		}
		
		return existingGroup.get();
		
	}
}
