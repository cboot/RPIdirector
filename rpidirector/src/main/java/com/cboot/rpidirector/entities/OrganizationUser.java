package com.cboot.rpidirector.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORGANIZATION_USER")
@IdClass(OrganizationUserId.class)
public class OrganizationUser {

	@Getter @Setter @Id @ManyToOne(cascade = CascadeType.PERSIST, optional = false) @JoinColumn(name = "ORGANIZATION" )
	private Organization organization;
	
	@Getter @Setter @Id @ManyToOne(cascade = CascadeType.PERSIST, optional = false) @JoinColumn(name = "USER")
	private User user;
	
	@Getter @Setter @Enumerated(EnumType.ORDINAL)
	private OrganizationRole role;
	
	@Getter @Setter @Column(columnDefinition = "datetime")
	private LocalDateTime since;
	

}
