package com.cboot.rpidirector.services.computers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.Computer;
import com.cboot.rpidirector.entities.GroupComputer;
import com.cboot.rpidirector.repositories.ComputerRepository;
import com.cboot.rpidirector.repositories.GroupComputerRepository;
import com.cboot.rpidirector.services.organizations.GetDefaultGroupForOrganizationUseCase;
import com.cboot.rpidirector.utils.LogUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegisterComputerUseCase {

	@Autowired
	private ComputerRepository computerRepository;

	@Autowired
	private GroupComputerRepository groupComputerRepository;

	@Autowired
	private GetDefaultGroupForOrganizationUseCase getDefaultGroupForOrganizationUseCase;

	public Computer register(String userId, String organizationId, String computerName, String serialNumber) {
		log.info("Register computer {}",
				LogUtils.toJsonString("userId", userId, "computerName", computerName, "serialNumber", serialNumber));

		Computer computer = new Computer();
		computer.setId(UUID.randomUUID().toString());
		computer.setName(computerName);
		computer.setSerialNumber(serialNumber);
		computer.setRegisteredOn(LocalDateTime.now());
		computerRepository.save(computer);

		GroupComputer groupComputer = new GroupComputer();
		groupComputer.setComputer(computer);
		groupComputer
				.setGroup(getDefaultGroupForOrganizationUseCase.getDefaultGroupForOrganization(userId, organizationId));
		groupComputer.setSince(LocalDateTime.now());
		groupComputerRepository.save(groupComputer);

		computer.getGroups().add(groupComputer);

		return computer;

	}
}
