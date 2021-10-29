package com.cboot.rpidirector.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "GROUP_COMPUTER")
@IdClass(GroupComputerId.class)
public class GroupComputer {

	@Getter @Setter @Id  @ManyToOne(cascade = CascadeType.PERSIST, optional = false) @JoinColumn(name = "GROUP" )
	private Group group;
	
	@Getter @Setter @Id @ManyToOne(cascade = CascadeType.PERSIST, optional = false) @JoinColumn(name = "COMPUTER" )
	private Computer computer;
	
	@Getter @Setter @Column(columnDefinition = "DATETIME")
	private LocalDateTime since;
}
