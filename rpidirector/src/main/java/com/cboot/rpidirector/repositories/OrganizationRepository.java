package com.cboot.rpidirector.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cboot.rpidirector.entities.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {

}
