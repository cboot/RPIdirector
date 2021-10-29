package com.cboot.rpidirector.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Organization {

	@Id @Getter @Setter
	private String id;
	
	@Getter @Setter @Column(nullable = false)
	private String name;
		
	@Getter @Setter @OneToMany (mappedBy = "organization", cascade = CascadeType.PERSIST)
	private List<OrganizationUser> users = new ArrayList<>();
}
