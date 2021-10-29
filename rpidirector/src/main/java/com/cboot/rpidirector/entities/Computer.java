package com.cboot.rpidirector.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Computer {
	
	@Getter @Setter @Id
	private String id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private String SerialNumber;
	
	@Getter @Setter @Column(name = "REGISTERED_ON", columnDefinition = "DATETIME")
	private LocalDateTime registeredOn;
	
	@Getter @Setter  @OneToMany(mappedBy = "computer") 
	private List<GroupComputer> groups = new ArrayList<>();
}
