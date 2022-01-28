package com.cboot.rpidirector.services.groups;

import java.time.LocalDateTime;

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
public class AddComputerToGroupUseCase {

	@Autowired
	private Messages messages;

	@Autowired
	private GroupComputerRepository groupComputerRepository;

	@Autowired
	private GetComputerUseCase getComputerService;

	@Autowired
	private GetGroupUseCase getGroupService;

	public void addComputerToGroup(String userId, String organizationId, String computerId, String groupId) {
		log.info("Add computer to group {}", LogUtils.toJsonString("userId", userId, "organizationId", organizationId,
				"computerId", computerId, "groupId", groupId));
		Computer computer = getComputerService.getComputer(userId, organizationId, computerId);
		Group group = getGroupService.getGroup(userId, organizationId, groupId);
		if (computer.getGroups().stream().anyMatch(item -> item.getGroup().getId().equals(groupId))) {
			throw new IllegalStateException(messages.get("exception.computer.alreadyingroup"));
		}

		GroupComputer newRelation = new GroupComputer();
		newRelation.setComputer(computer);
		newRelation.setGroup(group);
		newRelation.setSince(LocalDateTime.now());
		groupComputerRepository.save(newRelation);
		computer.getGroups().add(newRelation);
		group.getComputers().add(newRelation);

	}
}
