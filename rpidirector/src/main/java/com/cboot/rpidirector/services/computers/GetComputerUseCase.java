package com.cboot.rpidirector.services.computers;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.Computer;
import com.cboot.rpidirector.repositories.ComputerRepository;
import com.cboot.rpidirector.utils.LogUtils;
import com.cboot.rpidirector.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetComputerUseCase {

	@Autowired
	private Messages messages;

	@Autowired
	private ComputerRepository computerRepository;

	public Computer getComputer(String userId, String organizationId, String computerId) {
		log.info("Get computer {}", LogUtils.toJsonString("computerId", computerId));
		Optional<Computer> optional = computerRepository.findById(computerId);

		if (optional.isPresent()
				&& optional.get().getGroups().get(0).getGroup().getOrganization().getId().equals(organizationId)
				&& optional.get().getGroups().get(0).getGroup().getOrganization().getUsers().stream()
						.filter(org -> org.getUser().getId().equals(userId)).count() == 0) {
			return optional.get();
		}

		throw new EntityNotFoundException(messages.get("exception.computer.notfound"));

	}
}
