package com.cboot.rpidirector.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cboot.rpidirector.entities.Group;
import com.cboot.rpidirector.entities.Organization;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {

	Optional<Group> findByDefaultGroupAndOrganization(boolean defaultGroup, Organization organization);
}
