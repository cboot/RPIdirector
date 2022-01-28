package com.cboot.rpidirector.services.computers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.Computer;
import com.cboot.rpidirector.repositories.ComputerRepository;
import com.cboot.rpidirector.services.groups.DeleteComputerFromGroupUseCase;
import com.cboot.rpidirector.utils.LogUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeleteComputerUseCase {

	@Autowired
	private ComputerRepository computerRepository;

	@Autowired
	private GetComputerUseCase getComputerService;

	@Autowired
	private DeleteComputerFromGroupUseCase deleteComputerFromGroupService;

	public void deleteComputer(String userId, String computerId, String organizationId) {
		log.info("Delete computer {}",
				LogUtils.toJsonString("computerId", computerId, "organizationId", organizationId));
		Computer computer = getComputerService.getComputer(userId, organizationId, computerId);

		computer.getGroups().forEach(each -> deleteComputerFromGroupService.deleteComputer(userId, organizationId,
				each.getGroup().getId(), computerId));
		
		computerRepository.delete(computer);
	}

}
