package com.petpals.application.entrypoints.caregivers;

import com.petpals.application.dto.caregivers.AuthCaregiverRequest;
import com.petpals.application.mappers.caregivers.AuthCaregiverRequestMapper;
import com.petpals.domain.ports.in.AuthCaregiverIn;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.jboss.logging.Logger;

@Path("/caregivers/auth")

@SecurityRequirement(name = "api_key")
public class AuthCaregiverResource {
	private static final Logger LOGGER = Logger.getLogger(AuthCaregiverResource.class.getName());
	private final AuthCaregiverIn authCaregiverIn;
	private final AuthCaregiverRequestMapper authCaregiverRequestMapper;
	
	public AuthCaregiverResource(AuthCaregiverIn authCaregiverIn, AuthCaregiverRequestMapper authCaregiverRequestMapper) {
		this.authCaregiverIn = authCaregiverIn;
		this.authCaregiverRequestMapper = authCaregiverRequestMapper;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String authCaregiver(@Valid AuthCaregiverRequest authCaregiverRequest) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(String.format("Auth caregivers call with caregiver : %s", authCaregiverRequest.toString()));
		}
		return authCaregiverIn.authCaregivers(authCaregiverRequestMapper.toCommand(authCaregiverRequest));
	}
}
