package com.cboot.rpidirector.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cboot.rpidirector.entities.Organization;
import com.cboot.rpidirector.entities.OrganizationUser;
import com.cboot.rpidirector.entities.OrganizationUserId;
import com.cboot.rpidirector.entities.User;

public interface OrganizationUserRepository extends JpaRepository<OrganizationUser, OrganizationUserId> {

	
	List<OrganizationUser> findByUser(User user);
	
	Optional<OrganizationUser> findByUserAndOrganization(User user, Organization organization);
}
