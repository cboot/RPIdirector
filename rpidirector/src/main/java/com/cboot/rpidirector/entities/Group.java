package com.cboot.rpidirector.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Group {

	@Getter @Setter @Id
	private String id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter @Column(name = "CREATED_ON", columnDefinition = "DATETIME")
	private LocalDateTime createdOn;
	
	@Getter @Setter @ManyToOne
	private Organization organization;
	
	@Getter @Setter @OneToMany (mappedBy = "group")
	private List<GroupComputer> computers = new ArrayList<>();
}
