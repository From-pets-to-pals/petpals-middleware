package com.petpals.application.entrypoints.pals;

import com.petpals.application.dto.CreateOwnerRequest;
import com.petpals.application.mappers.pals.CreateOwnerRequestMapper;
import com.petpals.domain.ports.in.CreateOwnerIn;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.jboss.logging.Logger;

@Path("/owners")
@SecurityRequirement(name = "api_key")
public class CreateOwnerResource {
	private static final Logger LOGGER = Logger.getLogger(CreateOwnerResource.class.getName());
	private final CreateOwnerIn createOwnerIn;
	private final CreateOwnerRequestMapper createOwnerRequestMapper;
	
	public CreateOwnerResource(CreateOwnerIn createOwnerIn, CreateOwnerRequestMapper createOwnerRequestMapper) {
		this.createOwnerIn = createOwnerIn;
		this.createOwnerRequestMapper = createOwnerRequestMapper;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void createOwner(@Valid CreateOwnerRequest createOwnerRequest) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(String.format("Create owners call with owner : %s", createOwnerRequest.toString()));
		}
		createOwnerIn.createOwners(createOwnerRequestMapper.toCommand(createOwnerRequest));
	}
}
