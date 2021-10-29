package com.cboot.rpidirector.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cboot.rpidirector.controllers.utils.RestUtils;
import com.cboot.rpidirector.dto.OrganizationDTO;
import com.cboot.rpidirector.dto.requests.CreateOrganizationRequestDTO;
import com.cboot.rpidirector.dto.responses.CreateOrganizationResponseDTO;
import com.cboot.rpidirector.dto.responses.GetOrganizationResponseDTO;
import com.cboot.rpidirector.dto.responses.GetOrganizationsListResponseDTO;
import com.cboot.rpidirector.entities.Organization;
import com.cboot.rpidirector.entities.OrganizationUser;
import com.cboot.rpidirector.services.organization.CreateOrganizationUseCase;
import com.cboot.rpidirector.services.organization.DeleteOrganizationUseCase;
import com.cboot.rpidirector.services.organization.GetOrganizationUseCase;
import com.cboot.rpidirector.services.organization.GetOrganizationsListUseCase;
import com.cboot.rpidirector.utils.MapperUtils;

@CrossOrigin
@RestController
@RequestMapping("/private/organizations")
public class OrganizationController {

	@Autowired
	private CreateOrganizationUseCase createService;

	@Autowired
	private GetOrganizationsListUseCase organizationsListService;

	@Autowired
	private GetOrganizationUseCase getOrganizationService;

	@Autowired
	private DeleteOrganizationUseCase deleteOrganizationService;

	@PostMapping("/")
	public ResponseEntity<CreateOrganizationResponseDTO> createOrganization(
			@RequestHeader("Authorization") String authorizationHeader,
			@Valid @RequestBody CreateOrganizationRequestDTO createOrganizationRequest) {
		CreateOrganizationResponseDTO output = new CreateOrganizationResponseDTO();
		Organization newOrganization = createService.create(createOrganizationRequest.getName(),
				RestUtils.getUserIdFromBearerAuthorizationHeader(authorizationHeader));
		output.setOrganization(MapperUtils.toDTO(newOrganization.getUsers().get(0)));
		return ResponseEntity.ok().body(output);
	}

	@GetMapping("/")
	public ResponseEntity<GetOrganizationsListResponseDTO> getOrganizationsForUser(
			@RequestHeader("Authorization") String authorizationHeader) {
		GetOrganizationsListResponseDTO output = new GetOrganizationsListResponseDTO();
		List<OrganizationUser> serviceList = organizationsListService
				.getOrganizationListForUser(RestUtils.getUserIdFromBearerAuthorizationHeader(authorizationHeader));
		for (OrganizationUser anOrganization : serviceList) {
			output.getOrganizationsList().add(MapperUtils.toDTO(anOrganization));
		}
		return ResponseEntity.ok().body(output);
	}

	@GetMapping("/{organizationId}")
	public ResponseEntity<GetOrganizationResponseDTO> getOrganization(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable("organizationId") @NotEmpty String organizationId) {

		OrganizationDTO organization = MapperUtils.toDTO(getOrganizationService.getOrganizationForUser(
				RestUtils.getUserIdFromBearerAuthorizationHeader(authorizationHeader), organizationId));
		GetOrganizationResponseDTO output = new GetOrganizationResponseDTO();
		output.setOrganization(organization);
		return ResponseEntity.ok(output);
	}

	@DeleteMapping("/{organizationId}")
	public ResponseEntity<Void> deleteOrganization(@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable("organizationId") @NotEmpty String organizationId) {
		deleteOrganizationService.deleteOrganization(
				RestUtils.getUserIdFromBearerAuthorizationHeader(authorizationHeader), organizationId);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{organizationId}/leave")
	public ResponseEntity<Void> leaveOrganization(@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable("organizationId") @NotEmpty String organizationId) {
		deleteOrganizationService.deleteOrganization(
				RestUtils.getUserIdFromBearerAuthorizationHeader(authorizationHeader), organizationId);
		return ResponseEntity.ok().build();
	}
}
