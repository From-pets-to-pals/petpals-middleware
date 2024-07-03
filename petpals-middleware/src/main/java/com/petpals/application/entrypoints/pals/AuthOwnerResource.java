package com.petpals.application.entrypoints.pals;

import com.petpals.application.dto.pals.AuthOwnerRequest;
import com.petpals.application.mappers.pals.AuthOwnerRequestMapper;
import com.petpals.domain.ports.in.AuthOwnerIn;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.jboss.logging.Logger;

@Path("/owners/auth")

@SecurityRequirement(name = "api_key")
public class AuthOwnerResource {
	private static final Logger LOGGER = Logger.getLogger(AuthOwnerResource.class.getName());
	private final AuthOwnerIn authOwnerIn;
	private final AuthOwnerRequestMapper authOwnerRequestMapper;
	
	public AuthOwnerResource(AuthOwnerIn authOwnerIn, AuthOwnerRequestMapper authOwnerRequestMapper) {
		this.authOwnerIn = authOwnerIn;
		this.authOwnerRequestMapper = authOwnerRequestMapper;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String authOwner(@Valid AuthOwnerRequest authOwnerRequest) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(String.format("Auth owners call with owner : %s", authOwnerRequest.toString()));
		}
		return authOwnerIn.authOwners(authOwnerRequestMapper.toCommand(authOwnerRequest));
	}
}
