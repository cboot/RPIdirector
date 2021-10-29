package com.cboot.rpidirector.services.organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cboot.rpidirector.entities.OrganizationUser;
import com.cboot.rpidirector.entities.User;
import com.cboot.rpidirector.repositories.OrganizationUserRepository;
import com.cboot.rpidirector.services.user.GetUserUseCase;
import com.cboot.rpidirector.utils.LogUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetOrganizationsListUseCase {

	@Autowired
	private OrganizationUserRepository organizationUserRepository;

	@Autowired
	private GetUserUseCase getUserService;
	
	public List<OrganizationUser> getOrganizationListForUser(String userId) {
		log.info("Retrieving organizations for user {}", LogUtils.toJsonString("userId", userId));
		User user = getUserService.getById(userId);
		return organizationUserRepository.findByUser(user);

	}
}
