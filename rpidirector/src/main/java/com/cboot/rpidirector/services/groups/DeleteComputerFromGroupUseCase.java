package com.cboot.rpidirector.services.groups;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.Computer;
import com.cboot.rpidirector.entities.Group;
import com.cboot.rpidirector.entities.GroupComputer;
import com.cboot.rpidirector.repositories.GroupComputerRepository;
import com.cboot.rpidirector.services.computers.GetComputerUseCase;
import com.cboot.rpidirector.utils.LogUtils;
import com.cboot.rpidirector.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeleteComputerFromGroupUseCase {

	@Autowired
	private Messages messages;

	@Autowired
	private GroupComputerRepository groupComputerRepository; 
	
	@Autowired
	private GetComputerUseCase getComputerService;
	
	@Autowired
	private GetGroupUseCase getGroupService;

	public void deleteComputer(String userId, String organizationId, String groupId, String computerId) {
		log.info("Deleting computer {}", LogUtils.toJsonString("userId", userId, "organizationId", organizationId,
				"groupId", groupId, "computerId", computerId));

		Computer computer = getComputerService.getComputer(userId, organizationId, computerId);
		
		Group group = getGroupService.getGroup(userId, organizationId, groupId);
		
		Optional<GroupComputer> optional = groupComputerRepository.findByComputerAndGroup(computer, group);
		
		if (optional.isEmpty()) {
			throw new EntityNotFoundException(messages.get("exception.groupcomputer.notfound"));
		}
		
		GroupComputer real = optional.get();
		real.getGroup().getComputers().remove(real);
		real.getComputer().getGroups().remove(real);
		groupComputerRepository.delete(real);
	}
}
