package com.cboot.rpidirector.utils;

import com.cboot.rpidirector.entities.Computer;
import com.cboot.rpidirector.entities.Group;
import com.cboot.rpidirector.entities.Organization;
import com.cboot.rpidirector.entities.User;

public final class RelationsUtils {

	public static final boolean areRelated(Group group, Organization organization) {
		return group.getOrganization().equals(organization);
	}
	
	public static final boolean areRelated(Computer computer, Organization organization) {
		return computer.getGroups().get(0).getGroup().getOrganization().equals(organization);
	}
	
	public static final boolean areRelated(User user, Organization organization) {
		return !user.getOrganizations().isEmpty() && user.getOrganizations().get(0).getOrganization().equals(organization);
	}
}
