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
public class User {

	@Id @Getter @Setter
	private String  id;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String password;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter @Column(name = "REGISTER_DATE", columnDefinition = "DATETIME")
	private LocalDateTime registerDate;
	
	@Getter @Setter
	private String token;
	
	@Getter @Setter @Column(name = "TOKEN_VALID_UNTIL", columnDefinition = "DATETIME")
	private LocalDateTime tokenValidUntil;

	@Getter @Setter @OneToMany(mappedBy = "user") 
	private List<OrganizationUser> organizations = new ArrayList<>();
}