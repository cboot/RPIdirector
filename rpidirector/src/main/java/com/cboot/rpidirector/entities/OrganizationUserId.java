package com.cboot.rpidirector.entities;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;


public class OrganizationUserId implements Serializable {

	private static final long serialVersionUID = -5087112966516939838L;

	@Getter @Setter 
	private String organization;
	
	@Getter @Setter 
	private String user;

	@Override
	public int hashCode() {
		return Objects.hash(organization, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} 
		if (obj == null) {
			return false;
		} 
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		OrganizationUserId other = (OrganizationUserId) obj;
		return Objects.equals(organization, other.organization) && Objects.equals(user, other.user);
	}
	
	
}
