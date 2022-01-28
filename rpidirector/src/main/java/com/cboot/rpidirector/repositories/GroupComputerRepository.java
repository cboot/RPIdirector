package com.cboot.rpidirector.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cboot.rpidirector.entities.Computer;
import com.cboot.rpidirector.entities.Group;
import com.cboot.rpidirector.entities.GroupComputer;
import com.cboot.rpidirector.entities.GroupComputerId;

public interface GroupComputerRepository extends JpaRepository<GroupComputer, GroupComputerId> {

	Optional<GroupComputer> findByComputerAndGroup(Computer computer, Group group);

	
}
