package com.cboot.rpidirector.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "groups")
public class Group {

	@Getter @Setter @Id
	private String id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter @Column(name = "DEFAULT_GROUP")
	private boolean defaultGroup;
	
	@Getter @Setter @Column(name = "CREATED_ON", columnDefinition = "DATETIME")
	private LocalDateTime createdOn;
	
	@Getter @Setter @ManyToOne @JoinColumn(name="organization")
	private Organization organization;
	
	@Getter @Setter @OneToMany (mappedBy = "group", cascade = CascadeType.REMOVE)
	private List<GroupComputer> computers = new ArrayList<>();
}
