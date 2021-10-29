package com.cboot.rpidirector.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class GroupComputerId implements Serializable {

	private static final long serialVersionUID = -8989670282380577779L;

	@Getter @Setter
	private String group;
	
	@Getter @Setter
	private String computer;
	
}
