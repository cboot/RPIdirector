package com.cboot.rpidirector.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cboot.rpidirector.entities.Computer;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, String> {

}
